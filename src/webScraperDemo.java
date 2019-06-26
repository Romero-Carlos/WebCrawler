import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.util.Map;

import javax.print.Doc;
import java.io.IOException;

public class webScraperDemo {
    public static void main(String[] args) throws IOException {

        /*
        * if input is a string use
        * String html = "<some html>"
        * Document doc = Jsoup.parse(html);
        *
        * if input is a file, use
        * try {
        *   File file = new File("index.html");
        *   Document doc = Jsoup.parse(file, "utf-8");
        *  }
        * catch ( IOException ioEX) {
        *   ioEX.printStackTrace();
        * }
        *
        * if input is url, use
        * String url = "http://wwww.example.com";
        * Document doc = Jsoup.connect(url).get();
        *
        * */



        String url = "https://www.apple.com/shop/buy-mac/imac/21.5-inch-2.3ghz-dual-core-processor-with-turbo-boost-up-to-3.6ghz-1tb#";//works
        String url2 = "https://www.amazon.com/CAPTAIN-MARVEL-Blu-ray-Brie-Larson/dp/B07QLB785M/ref=sr_1_3?keywords=captain+marvel&qid=1561442427&s=gateway&sr=8-3";//nope
        String url3= "https://www.apple.com/shop/buy-ipad/ipad-pro/11-inch-display-64gb-space-gray-wifi";//works
        String url4="https://www.walmart.com/ip/Captain-Marvel-4K-Ultra-HD-Blu-ray-Digital/280684893";//works
        String url5="https://www.blu-ray.com/movies/Captain-Marvel-4K-Blu-ray/218576/#Review";//nope
        String url6 ="https://ultrahd.highdefdigest.com/70707/captainmarvel4kultrahdbluray.html";//works, that's how we get amazon price
        String url7="https://referencehometheater.com/review/captain-marvel-4k-blu-ray-review/";//nope
        String url8="https://www.frys.com/product/9884305?site=sr:SEARCH:MAIN_RSLT_PG";//works
        String url9="https://www.target.com/p/captain-marvel-4k-uhd-blu-ray-digital/-/A-54502083";
        Document doc = getDoc(url9);

        //Element spanElement= doc.selectFirst("div.jPUKyu");
        //String price = nextElement.val();
        System.out.println("done");


        //Element spanElement= doc.selectFirst("span.as-price");
        //String priceStr= spanElement.text();

        //System.out.println(priceStr);

//
//        String priceStr= spanElement.text();
//

//
//        System.out.println(price.compareTo(price2));


        /*
        * gets all the pages we can reach from given url
        * */
//        Elements links = doc.select("a[href]");
//
//        for(Element link: links)
//        {
//            System.out.println("\nlink: "+link.attr("href"));
//            System.out.println("text: "+link.text());
//        }

    }

    public static Document getDoc(String url) throws IOException
    {
        Document doc = Jsoup.connect(url)
                .timeout(0)
                .get();

        return  doc;
    }

    public static Map<String, String> getCookies(String url) throws IOException
    {
        Connection.Base res = Jsoup.connect(url)
                .method(Connection.Method.GET)
                .execute();

        Map<String, String> cookies= res.cookies();

        return cookies;
    }

    public static Map<String, String> getHeaders(String url) throws IOException
    {
        Connection.Base res = Jsoup.connect(url)
                .method(Connection.Method.GET)
                .execute();

        Map<String, String> headers= res.headers();

        return headers;
    }

    public static BigDecimal stringToPrice(String input){
        StringBuilder validPrice = new StringBuilder();

        for(int i=0;i<input.length();i++)
        {
            //ensures only numbers get converted, 46 is decimal point
            char currentChar = input.charAt(i);
            if((currentChar >= 48 && currentChar <= 58) || currentChar == 46)
            {
                validPrice.append(currentChar);
            }
        }

        BigDecimal price = new BigDecimal(validPrice.toString());

        return price;
    }

    public static boolean isCheaper(BigDecimal originalItem, BigDecimal compareItem)
    {
        int val = originalItem.compareTo(compareItem);

        return (val == -1)? true:false;
    }




}
