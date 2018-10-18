import java.io.Serializable;

/**
 * Created by Luke on 4/9/2017.
 */
public class Result implements Serializable, Comparable<Result> {

    public int score;
    public static final long serialVersionUID = -938761094876384658L;
    public String url;
    public int urlID;

    Result(String url, int urlID){
        this.url = url;
        this.urlID = urlID;
        score = 1;
    }

    public int compareTo(Result candidate){
        if(this.score > candidate.getScore()){
            return -1;
        }
        if(this.score == candidate.getScore()){
            return 0;
        }
        else{
            return 1;
        }
    }

    public boolean equals(Object obj){
        if(obj instanceof Result){
            Result newResult = (Result) obj;
            if(this.url.equals(newResult.getURL()) || this.urlID == newResult.getURLID()){
                return true;
            }
        }
        return false;
    }

    public void incrementScore(){
        score++;
    }

    public void updateScore(int score){
        this.score = this.score + score;
    }

    public int getScore() {
        return this.score;
    }

    public int getURLID() {
        return this.urlID;
    }

    public String getURL() {
        return this.url;
    }

}
