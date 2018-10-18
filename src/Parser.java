/**
 * Created by Derek on 3/24/2017.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class Parser {
    public Parser() {

    }

    public String getBody(Document doc) throws ParseException {
        if (doc == null) {
            throw new ParseException("getBody() failed. Document parameter is null.");
        }
        Element body = doc.body();
        String content = "";
        if (body != null) {
            content = body.text();
        }
        return content;
    }

    public Document getDocument(String url) throws ParseException {
        if (url == null) {
            throw new ParseException("getDocument() failed. String url is null.");
        } else if (url.equals("")) {
            throw new ParseException("getDocument() failed. String url is empty.");
        }
        Document d = null;
        try {
            d = Jsoup.connect(url).ignoreContentType(true).get();
            if (d == null) {
                throw new ParseException("getDocument() failed. Document is null.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new ParseException("getDocument() failed. Connection failed.");
        }
        return d;
    }
    public Elements getLinks(Document doc) throws ParseException {
        if (doc == null) {
            throw new ParseException("getLinks() failed. Document parameter is null.");
        }
        return doc.select("a[href]");
    }

}