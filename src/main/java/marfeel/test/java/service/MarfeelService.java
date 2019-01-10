package marfeel.test.java.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import marfeel.test.java.model.Site;
import marfeel.test.java.repository.SiteRepository;
import marfeel.test.java.resources.SiteRequest;
import marfeel.test.java.resources.SiteResponse;
import marfeel.test.java.utils.HTMLParser;

@Service
public class MarfeelService {

	private static final Log logger = LogFactory.getLog(MarfeelService.class);

	private final String[] keys = { "news","noticias","servicios"};

	@Autowired
	private SiteRepository repository;

	public SiteResponse analyze(List<SiteRequest> siteResourceList) {
		this.evalueateContent(siteResourceList);
		return new SiteResponse("Processing", siteResourceList.size());
	}

	@Async
	public void evalueateContent(List<SiteRequest> siteResourceList) {
		logger.info("Analizando");
		List<Site> entities = siteResourceList.parallelStream().map(site -> {
			return new Site(site.getUrl(), HTMLParser.evaluate(keys, site.getUrl()));
		}).collect(Collectors.toList());

		logger.info(entities.stream().filter(Site::getMarfeelizable).collect(Collectors.toList()).size());
		this.repository.saveAll(entities);
	}

	public SiteResponse analyze2(List<SiteRequest> siteResourceList) {
		siteResourceList.parallelStream().forEach(site -> {
			this.evalueateContent2(site);
		});
		return new SiteResponse("Processing", siteResourceList.size());
	}

	@Async
	public void evalueateContent2(SiteRequest site) {
		logger.info("Analizando");
		Site site2Save = new Site(site.getUrl(), HTMLParser.evaluate(keys, site.getUrl()));

		logger.info(String.format("Saving url checked: %s", site.getUrl()));
		this.repository.save(site2Save);
	}

}
