import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Luke on 4/9/2017.
 */
public class Search {

    SearchThread st = new SearchThread(0, 1, new String[]{"yes"});

    public static List<Page> pageList;
    public static List<Word> wordList;
    public static List<Result> resultSet;
    public static List<Result> resultSetsorted;
    private String wordListFile;
    private String pageListFile;

    public Search(String wordListFile, String pageListFile) {
        this.wordListFile = wordListFile;
        this.pageListFile = pageListFile;
        resultSet = new ArrayList<>();
        resultSetsorted = new ArrayList<>();


    }

    public static List getpage() {
        return pageList;
    }
    public static List getword() {
        return wordList;
    }
    public static List getresult() {
        return resultSet;
    }


    public void setup(String wordListFile, String pageListFile) {
        FileUtils fu = new FileUtils();
        wordList = Collections.synchronizedList(fu.getWordList(wordListFile));
        pageList = Collections.synchronizedList(fu.getPageList(pageListFile));
        st.setLists(pageList,  wordList,  resultSet);



    }

    public void sort(){
        int maxscore = 1;
        for(Result result : resultSet){
            //for(int i = 0; i < resultSet.size(); i++){
            //Result result = resultSet.get(i);
            int score = result.getScore();
            //System.out.println("Score: " + score);
            if(score > maxscore){
                maxscore = score;
            }
        }

        int resultSize = resultSet.size();
        String[] urlList = new String[resultSize];


        int count = 0;
        for (int m = maxscore; m > 0; m--) {
            //for(int i = 0; i < resultSet.size(); i++) {
            for(Result result : resultSet) {
                boolean duplicate = false;
                //Result result = resultSet.get(i);
                int score = result.getScore();
                if (score == m) {
                    for(int i = 0; i < count; i++){
                        if(urlList[i].equals(result.getURL())){
                            duplicate = true;
                            break;
                        }
                    }
                    if(!duplicate) {
                        resultSetsorted.add(result);
                        urlList[count] = result.getURL();
                        count++;
                    }

                }
            }
        }

        /**
         //resultSet = resultSetsorted;
         resultSet.clear();
         for(Result result : resultSetsorted){
         resultSet.add(result);
         }
         **/

    }

    public List<Result> executeQuery(String query) {
        nullCheck();
        st.setLists(pageList,  wordList,  resultSet);
        //resultSet = new ArrayList<>();
        //System.out.println("result set: " + resultSet);


        String[] terms = query.toLowerCase().split(" ");

        //System.out.println("wordlist size: " + wordList.size());
        int wordsPer = wordList.size() / 5;
        int left = wordList.size() % 5;

        Thread[] threads = new Thread[5];
        threads[0] = new Thread(new SearchThread(0, wordsPer, terms));
        threads[1] = new Thread(new SearchThread(wordsPer + 1, wordsPer * 2, terms));
        threads[2] = new Thread(new SearchThread(wordsPer * 2 + 1, wordsPer * 3, terms));
        threads[3] = new Thread(new SearchThread(wordsPer * 3 + 1, wordsPer * 4, terms));
        threads[4] = new Thread(new SearchThread(wordsPer * 4 + 1, wordsPer * 5 + left - 1, terms));


        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //System.out.println("result set: " + resultSet);
        resultSet = getresult();

        sort();
        //System.out.println("post sort resultset size: " + resultSet.size());

        return resultSetsorted;
    }

    public void nullCheck() {
        if (wordList == null || pageList == null) {
            setup(wordListFile, pageListFile);
        }
    }


}
