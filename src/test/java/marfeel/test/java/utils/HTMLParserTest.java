package marfeel.test.java.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Jsoup.class,StringContainsEvaluator.class})
public class HTMLParserTest {

	private static final String[] STRINGS = { "news","noticias"};
	private static final String A_URL = "aUrl";
	private static final String A_TITLE = "aTitle";
	private static final String HTTP_WWW = "http://www.";

	@Test
	public void evaluateTrueResult() throws IOException {
		// given
		Connection mockConnection = mock(Connection.class);
		PowerMockito.mockStatic(Jsoup.class);
		when(Jsoup.connect(HTTP_WWW + A_URL)).thenReturn(mockConnection);
		Document mockDocument = mock(Document.class);
		when(mockConnection.get()).thenReturn(mockDocument);
		when(mockDocument.title()).thenReturn(A_TITLE);

		PowerMockito.mockStatic(StringContainsEvaluator.class);
		when(StringContainsEvaluator.stringContainsItemFromList(A_TITLE, STRINGS)).thenReturn(true);

		// when
		Boolean result = HTMLParser.evaluate(STRINGS, A_URL);

		// then
		assertNotNull(result);
		assertTrue(result);
		PowerMockito.verifyStatic(Jsoup.class, Mockito.atLeast(1));
		Jsoup.connect(HTTP_WWW + A_URL);
		PowerMockito.verifyStatic(StringContainsEvaluator.class, Mockito.atLeast(1));
		StringContainsEvaluator.stringContainsItemFromList(A_TITLE, STRINGS);
		Mockito.verify(mockConnection).get();
		Mockito.verify(mockDocument).title();
	}

	@Test
	public void evaluateFalseResult() throws IOException {
		// given
		Connection mockConnection = mock(Connection.class);
		PowerMockito.mockStatic(Jsoup.class);
		when(Jsoup.connect(HTTP_WWW + A_URL)).thenReturn(mockConnection);
		Document mockDocument = mock(Document.class);
		when(mockConnection.get()).thenReturn(mockDocument);
		when(mockDocument.title()).thenReturn(A_TITLE);

		PowerMockito.mockStatic(StringContainsEvaluator.class);
		when(StringContainsEvaluator.stringContainsItemFromList(A_TITLE, STRINGS)).thenReturn(false);

		// when
		Boolean result = HTMLParser.evaluate(STRINGS, A_URL);

		// then
		assertNotNull(result);
		assertFalse(result);
		PowerMockito.verifyStatic(Jsoup.class, Mockito.atLeast(1));
		Jsoup.connect(HTTP_WWW + A_URL);
		PowerMockito.verifyStatic(StringContainsEvaluator.class, Mockito.atLeast(1));
		StringContainsEvaluator.stringContainsItemFromList(A_TITLE, STRINGS);
		Mockito.verify(mockConnection).get();
		Mockito.verify(mockDocument).title();
	}

	@Test
	public void evaluateWithIOException() throws IOException {
		// given
		PowerMockito.mockStatic(Jsoup.class);
		when(Jsoup.connect(HTTP_WWW + A_URL)).thenThrow(IOException.class);

		// when
		Boolean result = HTMLParser.evaluate(STRINGS, A_URL);

		// then
		assertNull(result);
		PowerMockito.verifyStatic(Jsoup.class, Mockito.atLeast(1));
		Jsoup.connect(HTTP_WWW + A_URL);
	}
}
