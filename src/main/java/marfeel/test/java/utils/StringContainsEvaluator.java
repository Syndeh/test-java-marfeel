package marfeel.test.java.utils;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringContainsEvaluator {
	
    private static final Log logger = LogFactory.getLog(StringContainsEvaluator.class);

	public static boolean stringContainsItemFromList(String inputStr, String[] items) {
		logger.info(String.format("Evaluating: %s, with arguments: %s", inputStr, items));
	    return Arrays.stream(items).parallel().anyMatch(inputStr::contains);
	}
}
