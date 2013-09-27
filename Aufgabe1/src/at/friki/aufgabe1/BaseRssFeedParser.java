package at.friki.aufgabe1;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mathias on 26.09.13.
 */
public abstract class BaseRssFeedParser implements iRssFeedParser {
    // names of the XML tags
    static final String PUB_DATE = "pubDate";
    static final  String DESCRIPTION = "description";
    static final  String LINK = "link";
    static final  String TITLE = "title";
    static final  String ITEM = "item";

    final URL feedUrl;

    protected BaseRssFeedParser(String feedUrl){
        try {
            this.feedUrl = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    protected InputStream getInputStream() {
        try {
            return feedUrl.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
