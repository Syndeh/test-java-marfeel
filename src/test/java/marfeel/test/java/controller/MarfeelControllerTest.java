package marfeel.test.java.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import marfeel.test.java.config.ApplicationConfiguration;
import marfeel.test.java.resources.SiteRequest;
import marfeel.test.java.resources.SiteResponse;
import marfeel.test.java.service.MarfeelService;


@RunWith(PowerMockRunner.class)
@ContextConfiguration(classes = {TestContext.class, ApplicationConfiguration.class})
@WebAppConfiguration
public class MarfeelControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	MarfeelController MarfeelController;

	@Mock
	MarfeelService service;

	private static final String PROCESSING = "Processing";
	private static final Integer SIZE_3 = 3;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(MarfeelController)
				.build();
	}

	@Test
	public void analyze() throws Exception {

		// given
		SiteRequest siteRequest = new SiteRequest();
		List<SiteRequest> siteRequests = Arrays.asList(siteRequest, siteRequest, siteRequest);
		SiteResponse response = new SiteResponse(PROCESSING, SIZE_3);
		PowerMockito.when(service.analyze(Mockito.any())).thenReturn(response);

		// when
		mockMvc.perform(
				post("/marfeelizable")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(siteRequests)))
		.andExpect(status().isCreated());

		// then
		Mockito.verify(service).analyze(Mockito.any());
	}

	/*
	 * converts a Java object into JSON representation
	 */
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
