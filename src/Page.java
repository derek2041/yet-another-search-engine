import java.io.*;
import java.io.Serializable;
/**
 * Created by Derek on 3/21/2017.
 */
public class Page implements Serializable, Comparable<Page> {
    final static long  serialVersionUID = -1827677255104766839L;
    String url;
    private int urlID;

    Page(String url, int urlID) {
        this.url = url;
        this.urlID = urlID;
    }

    public int compareTo(Page candidate) {
        if (this.urlID == candidate.urlID) {
            return 0;
        } else if (this.urlID > candidate.urlID) {
            return 1;
        } else {
            return -1;
        }
    }

    public boolean equals(Object obj) {
        Page newPage = (Page) obj;
        if (this.urlID == newPage.urlID || this.url.equals(newPage.url)) {
            return true;
        } else {
            return false;
        }
    }

    public String getURL() {
        return this.url;
    }

    public int getURLID() {
        return this.urlID;
    }
}
