package marfeel.test.java.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import marfeel.test.java.model.Site;
import marfeel.test.java.repository.SiteRepository;
import marfeel.test.java.resources.SiteRequest;
import marfeel.test.java.resources.SiteResponse;
import marfeel.test.java.utils.HTMLParser;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HTMLParser.class)
public class MarfeelServiceTest {
	
	private static final String PROCESSING = "Processing";
	private static final String A_URL = "aUrl";
	private static final Integer SIZE_3 = 3;
	private static final Integer SIZE_0 = 0;

	@InjectMocks
	MarfeelService service;
	
	@Mock
	SiteRepository repository;
	
	@Test
	public void analyzeEmptyList() throws Exception {
		// given
		List<SiteRequest> siteRequests = new ArrayList<>();

		List<Site> sites = new ArrayList<>();
		Mockito.when(repository.saveAll(Mockito.any())).thenReturn(sites);

		// when
		SiteResponse result = service.analyze(siteRequests);

		// then
		assertNotNull(result);
		assertEquals(result.getStatus(), PROCESSING);
		assertEquals(result.getSiteToEvaluate(), SIZE_0);
		Mockito.verify(repository).saveAll(Mockito.any());
	}
	
	@Test
	public void analyzeList() throws Exception {
		// given
		SiteRequest siteRequest = new SiteRequest();
		siteRequest.setUrl(A_URL);
		List<SiteRequest> siteRequests = Arrays.asList(siteRequest, siteRequest, siteRequest);

		PowerMockito.mockStatic(HTMLParser.class);
		PowerMockito.when(HTMLParser.evaluate(Mockito.any(),Mockito.eq(A_URL))).thenReturn(true);

		List<Site> sites = new ArrayList<>();
		Mockito.when(repository.saveAll(Mockito.any())).thenReturn(sites);

		// when
		SiteResponse result = service.analyze(siteRequests);

		// then
		assertNotNull(result);
		assertEquals(result.getStatus(), PROCESSING);
		assertEquals(result.getSiteToEvaluate(), SIZE_3);
		Mockito.verify(repository).saveAll(Mockito.any());
		PowerMockito.verifyStatic(HTMLParser.class, Mockito.atLeast(3));
		HTMLParser.evaluate(Mockito.any(), Mockito.eq(A_URL));
	}

}
