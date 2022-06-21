import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Hangman {

    private String word;
    private List<Character> guesses = new ArrayList<Character>();
    private int guessQt;
    private int guessLimit;
    private char[] progress;

    public Hangman(Wordslist wordslist, int guessQt) {
        setGuessLimit(10);
        setGuessQt(guessQt);
        setWord(wordslist);
        setProgress();
    }

    public int getGuessLimit() {
        return this.guessLimit;
    }

    public void setGuessLimit(int guessLimit) {
        this.guessLimit = guessLimit;
    }

    public char[] getProgress() {
        return this.progress;
    }

    public void setProgress() { // transforms the characters to appear as '_' in UI
        char[] temp = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            temp[i] = '_';
        }
        this.progress = temp;
    }

    public void setWord(Wordslist wordslist) {
        Random rand = new Random();
        List<String> words = wordslist.giveWords();
        String temp = words.get(rand.nextInt(words.size()));
        this.word = temp;
    }

    public String getWord() {
        return this.word;
    }

    public String word() {
        return this.word;
    }

    public int getGuessQt() {
        return this.guessQt;
    }

    public void setGuessQt(int guessQt) {
        this.guessQt = guessQt;
    }

    public List<Character> guesses() {
        return this.guesses;
    }

    public void setGuesses(List<Character> guesses) {
        this.guesses = guesses;
    }

    public boolean guess(Character letter) {

        if (!word.contains(letter.toString())) { // checks in directly, if true, the program does more detailed check-up
            guessQt++;
            if (!guesses.contains(letter)) {
                guesses.add(letter);
            }
            return false;
        } else {
            for (int i = 0; i < word.length(); i++) { // makes the char visible in UI
                if (word.charAt(i) == Character.toLowerCase(letter)) {
                    progress[i] = letter;
                }
            }
        }
        if (!guesses.contains(letter)) {
            guesses.add(letter);
        }
        return word.contains(letter.toString());
    }

    public int guessesRemaining() { // returns the amount of guesses remaining
        return guessLimit - guessQt;
    }

    public boolean isItOver() {
        for (int i = 0; i < word.length(); i++) {
            if (progress[i] == '_') {
                return false;
            }
        }
        System.out.println("You won!");
        System.out.println("The correct word is:  " + word());
        return true;
    }
}
