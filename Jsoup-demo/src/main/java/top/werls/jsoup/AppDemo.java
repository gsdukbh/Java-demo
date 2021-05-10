package top.werls.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * @author leejiawei
 */
public class AppDemo {

    public static void main(String[] args) throws Exception{
        Document doc=  Jsoup.connect("https://www.bing.com/search?")
                .userAgent("Mozilla")
                .data("q","圣墟")
                .get();
        System.out.println(doc.title());

    }
}
