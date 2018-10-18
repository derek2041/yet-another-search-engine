/**
 * Created by Derek on 4/2/2017.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Crawler {
    public static int currentID;
    public static String domain;
    public static int limit;
    public static List<Page> parsed;
    public static Parser parser;
    public MyQueue toParse;
    public static int totalURLs;
    public static List<String> visited;
    public static List<Word> words;

    Crawler(String seed, String domain, int limit) {
        parser = new Parser();
        parsed = new ArrayList<>();
        toParse = new MyQueue();
        words = new ArrayList<>();
        totalURLs = 0;
        this.toParse.add(seed);
        this.domain = domain;
        this.limit = limit;
    }

    void addPageToList(Page p) {
        parsed.add(p);
    }
    void addToQueue(String url) {
        boolean notDuplicated = true;
        for (int i = 0; i < parsed.size(); i++) {
            if (url.equals(parsed.get(i).getURL())) {
                notDuplicated = false;
            }
        }
        if (notDuplicated && (totalURLs < limit || limit == 0)) {
            toParse.add(url);
            totalURLs++;
        }
    }
    void addWordToList(String word, int id) {
        Word newWord = new Word(word, id);
        words.add(newWord);
    }
    void crawl() {
        currentID = 0;
        while (!toParse.isEmpty()) {
            Object urlObj = toParse.remove().getData();
            String url = urlObj.toString();
            try {
                Document doc = parser.getDocument(url);
                if (doc == null) {
                    continue;
                } else if (parse(doc, currentID)) {
                    //addPageToList(new Page(url, currentID));

                    currentID++;
                }
            } catch (ParseException e) {

            }
        }
    }
    boolean isInDomain(String url) {
        if (url.contains(domain)) {
            return true;
        } else {
            return false;
        }
    }
    boolean isValidURL(String url) {
        if (url.substring(0,7).equals("http://") || url.substring(0,8).equals("https://")) {
            return true;
        } else {
            return false;
        }
    }
    boolean parse(Document doc, int id) {
        parseLinks(doc);
        parseText(doc, id);
        return true;
    }
    void parseLinks(Document doc) {
        try {
            Elements links = parser.getLinks(doc);
            for(Element link: links) {
                String url = link.attr("abs:href");
                if (isInDomain(url)) {
                    addToQueue(link.attr("abs:href"));
                }
            }
            addPageToList(new Page(doc.baseUri(), currentID));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    void parseText(Document doc, int id) {
        try {
            String bodyText = parser.getBody(doc);
            for (String word : bodyText.split(" ")) {
                boolean duplicate = false;
                word = word.toLowerCase();
                for(int i = 0; i < words.size(); i++){
                    Word listword = words.get(i);
                    if(listword.getWord().equals(word)){
                        listword.addURLID(id);
                        words.set(i, listword);
                        duplicate = true;
                        break;
                    }
                }
                if(!duplicate) {
                    words.add(new Word(word.toLowerCase(), id));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
