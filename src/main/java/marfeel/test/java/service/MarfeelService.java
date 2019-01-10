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

	private static final String NOTICIAS = "noticias";

	private static final String NEWS = "news";

	private static final String PROCESSING = "Processing";

	private static final Log logger = LogFactory.getLog(MarfeelService.class);

	private final String[] keys = { NEWS,NOTICIAS};

	@Autowired
	private SiteRepository repository;

	public SiteResponse analyze(List<SiteRequest> siteRequests) {
		this.evalueateContent(siteRequests);
		return new SiteResponse(PROCESSING, siteRequests.size());
	}

	@Async
	private void evalueateContent(List<SiteRequest> siteRequests) {
		logger.info("Analyzing ...");
		List<Site> entities = siteRequests.parallelStream().map(site -> {
			return new Site(site.getUrl(), HTMLParser.evaluate(keys, site.getUrl()));
		}).collect(Collectors.toList());

		this.repository.saveAll(entities);
	}
}
