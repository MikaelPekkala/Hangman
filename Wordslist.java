import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Wordslist {

    private List<String> words = new ArrayList<String>();

    public Wordslist(String fileName) {
        readFile(fileName);
    }

    public Wordslist(List<String> words) {
        this.words = words;
    }

    public void setWordsList(List<String> words) {
        this.words = words;
    }

    public List<String> giveWords() {
        return this.words;
    }

    public Wordslist wordsWithCertainLength(int length) {
        List<String> templist = new ArrayList<String>();
        for (int i = 0; i < words.size(); i++) {
            String temps = words.get(i);
            if (temps.length() == length) {
                templist.add(temps);
            }
        }
        return new Wordslist(templist);
    }

    public Wordslist wordsWithCertainChars(String str) { 
        List<String> templist = new ArrayList<String>();
        for (int i = 0; i < words.size(); i++) {
            String temps = words.get(i);
            if (str.length() != temps.length()) {
                continue;
            }
            for (int j = 0; j < temps.length(); j++) { 
                if (str.charAt(j) != '_' && str.charAt(j) != temps.charAt(j)) {
                    break;
                }
                if (str.length() == (j + 1)) {
                    templist.add(temps);
                }
            }

        }
        return new Wordslist(templist);
    }

    public void readFile(String fileName) {
        BufferedReader txt = null;
        try {
            txt = new BufferedReader(new FileReader(fileName));
            String row;
            while ((row = txt.readLine()) != null) {
                row = row.toLowerCase();
                words.add(row);
            }
            txt.close();
        } catch (FileNotFoundException e) {
            System.out.println(
                    "Opening the list has failed because: " + e.getLocalizedMessage());
                    System.out.println("Exiting...");
                    System.exit(0);
        } catch (IOException e) {
            System.out.println("Reading the file has failed because: " + e.getLocalizedMessage());
            System.out.println("Exiting...");
            System.exit(0);
        }
    }
}
