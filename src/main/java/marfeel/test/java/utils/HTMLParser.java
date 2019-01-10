package marfeel.test.java.utils;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLParser {
	
    private static final Log logger = LogFactory.getLog(HTMLParser.class);

	public static Boolean evaluate(String[] keys,String url) {
        Document doc;
        try {
        	logger.info(String.format("Connecting with %s",url));
            doc = Jsoup.connect("http://www."+url).get();

            logger.info("Get Title");
            String title = doc.title();
            logger.info(String.format("Title: %s",title));
            
            return StringContainsEvaluator.stringContainsItemFromList(title, keys);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return false;
	}
}
