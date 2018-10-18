/**
 * Created by Derek on 3/21/2017.
 */
import java.util.*;
import java.io.*;
import java.io.Serializable;

public class Word implements Serializable {

    static final long serialVersionUID = -3696191086353573895L;
    private String word;
    private List<Integer> postings = new ArrayList<>();

    public Word(String word, int urlID) {
        this.word = word;
        this.postings.add(urlID);
    }

    public void addURLID(int urlID) {
        this.postings.add(urlID);
    }

    public String getWord() {
        return this.word;
    }

    public List<Integer> getList() {
        return this.postings;
    }

    public boolean equals(Object obj) {
        Word newWord = (Word) obj;
        if (this.word.equals(newWord.word)) {
            return true;
        } else {
            return false;
        }
    }
}