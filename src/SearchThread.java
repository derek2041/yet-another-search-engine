import java.util.List;

/**
 * Created by Luke on 4/10/2017.
 */
public class SearchThread implements Runnable {

    public int start;
    public int finish;
    public String[] terms;
    public static List<Page> pageList;
    public static List<Word> wordList;
    public static List<Result> resultSet;

    public SearchThread(int start, int finish, String[] terms) {
        this.start = start;
        this.finish = finish;
        this.terms = terms;
    }

    public void setLists(List<Page> pageList, List<Word> wordList, List<Result> resultSet){
        this.pageList = pageList;
        this.wordList = wordList;
        this.resultSet = resultSet;

    }

    public static List<Result> getResults(){
        return resultSet;
    }


    public void run() {
        //Search s = new Search();
        //pageList = getpage();
        //wordList = getword;

        for (String term : terms) {
            for (int i = start; i <= finish; i++) {
                if (wordList.get(i).getWord().equals(term)) {
                    Word word = wordList.get(i);
                    for (int id : word.getList()) {
                        //System.out.println("ID: " + id);
                        //System.out.println("result set:" + resultSet);

                        if (resultSet == null) {
                            String url = "NOT REAL!";
                            Page fakePage = new Page("url", id);
                            for (int j = 0; j < pageList.size(); j++) {
                                Page page = pageList.get(j);
                                if (page.equals(fakePage)) {
                                    url = page.getURL();
                                }
                            }
                            //System.out.println("url: " + url);
                            resultSet.add(new Result(url, id));
                        }
                        boolean duplicate = false;
                        //for(int k = 0; i < resultSet.size(); k++){
                        int count = 0;
                        for (Result result : resultSet) {

                            if (result.getURLID() == id) {
                                result.incrementScore();
                                resultSet.set(count, result);
                                duplicate = true;
                                break;
                            }
                            count++;
                        }
                        if (!duplicate) {
                            String url = "NOT REAL!";
                            Page fakePage = new Page("url", id);
                            for (int j = 0; j < pageList.size(); j++) {
                                Page page = pageList.get(j);
                                if (page.equals(fakePage)) {
                                    url = page.getURL();
                                }
                            }
                            //System.out.println("URL: " + url);
                            //System.out.println("result set: " + resultSet);
                            resultSet.add(new Result(url, id));
                        }
                    }
                }

            }
        }
    }

    public Word findTerm(String term) {
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).getWord().equals(term)) {
                Word word = wordList.get(i);
                return word;
            }
        }
        return null;


    }
}

