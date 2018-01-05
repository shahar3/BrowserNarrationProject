package Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class help us to interact with a website.
 * it takes a url and extract the relevant tags
 */
public class WebUtil {
    private static Document doc = null; //holds the webpage url

    /**
     * Takes the webpage url and connect to it
     *
     * @param url
     */
    public static void connectToWebsite(String url) {
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getItems() {
        if (doc == null) {
            return null;
        }

        ArrayList<String> items = new ArrayList<>();
        Elements links = doc.getElementsByTag("a");
        for (Element link : links) {
            String linkText = link.text();
            items.add(linkText);
        }
        return items;
    }
}
