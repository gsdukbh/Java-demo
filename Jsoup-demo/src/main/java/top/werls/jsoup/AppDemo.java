package top.werls.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * @author leejiawei
 */
public class AppDemo {

    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.bing.com/search")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36")
                .data("q", "圣墟")
                .get();
//        System.out.println(doc.body().getElementById("b_content"));
        Element content = doc.body().getElementById("b_content");

//        System.out.println(content.html());
        System.out.println(doc.getElementsByClass("b_algo"));

    }
}
