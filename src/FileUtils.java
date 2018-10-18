/**
 * Created by Derek on 4/2/2017.
 */
import java.util.List;
import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;

public class FileUtils implements Serializable {
    public FileUtils() {

    }
    List<Page> getPageList(String filePath) {
        if (filePath == null || filePath == "") {
            return null;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            List<Page> inputStream = ((List<Page>)ois.readObject());
            ois.close();
            return inputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    List<Word> getWordList(String filePath) {
        if (filePath == null) {
            return null;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            List<Word> inputStream = ((List<Word>)ois.readObject());
            ois.close();
            return inputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    boolean savePageTable(List<Page> pageTable, String filePath) {
        //FileInputStream fis = new FileInputStream(new File(filePath));
        if (pageTable == null || filePath == null) {
            return false;
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            /*for (int i = 0; i < pageTable.size(); i++) {
                oos.writeObject(pageTable.get(i));
            } */
            oos.writeObject(pageTable);
            oos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    boolean saveWordTable(List<Word> wordTable, String filePath) {
        //FileOutputStream fos = new FileOutputStream(new File(filePath));
        if (wordTable == null || filePath == null) {
            return false;
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            /* for (int i = 0; i < wordTable.size(); i++) {
                oos.writeObject(wordTable.get(i));
            } */
            oos.writeObject(wordTable);
            oos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
