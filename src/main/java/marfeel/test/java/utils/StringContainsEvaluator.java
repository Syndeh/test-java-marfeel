package marfeel.test.java.utils;

import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringContainsEvaluator {
	
    private static final String STRING_EMPTY = "";
	private static final Log logger = LogFactory.getLog(StringContainsEvaluator.class);

	public static boolean stringContainsItemFromList(String inputStr, String[] items) {
		logger.info(String.format("Evaluating: %s, with arguments: %s", inputStr, items));
		
		String name = Optional.ofNullable(inputStr).orElse(STRING_EMPTY);
		
	    return Arrays.stream(items).parallel().anyMatch(name.toLowerCase()::contains);
	}
}
