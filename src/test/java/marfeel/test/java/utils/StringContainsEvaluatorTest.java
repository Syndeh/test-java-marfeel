package marfeel.test.java.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StringContainsEvaluatorTest {

	@Test
	public void stringContainsItemFromListWithMatch() {
		// given
		String[] strings = { "news","noticias"};
		String toEvaluate = "News Title";

		// when
		Boolean result = StringContainsEvaluator.stringContainsItemFromList(toEvaluate, strings);

		// then
		assertNotNull(result);
		assertTrue(result);
	}
	
	@Test
	public void stringContainsItemFromListWithoutMatch() {
		// given
		String[] strings = { "news","noticias"};
		String toEvaluate = "No match Title";

		// when
		Boolean result = StringContainsEvaluator.stringContainsItemFromList(toEvaluate, strings);

		// then
		assertNotNull(result);
		assertFalse(result);
	}
	
	@Test
	public void stringContainsItemFromListWithInputNull() {
		// given
		String[] strings = { "news","noticias"};
		String toEvaluate = null;

		// when
		Boolean result = StringContainsEvaluator.stringContainsItemFromList(toEvaluate, strings);

		// then
		assertNotNull(result);
		assertFalse(result);
	}
	
}
