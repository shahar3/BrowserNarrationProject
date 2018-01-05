package audio;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class WebUtils {
    private static Document doc;

    public static void connectToWebsite(String url){
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getItems(){
        ArrayList<String> items = new ArrayList<>();
        Elements links = doc.getElementsByTag("a");
        for(Element link:links){
            String linkText = link.text();
            items.add(linkText);
        }
        return items;
    }
}
