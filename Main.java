import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
   private static Scanner sc = new Scanner(System.in);

   public static void main(String[] args) {

      
      char guess = '\0';
      int input = 0; // variable used on different menus
      System.out.print("\n\nWelcome! Let's play hangman!\n");
      System.out.print("Please input the name of file(e.g wordslists.txt) > ");
      String file = sc.nextLine();
      // the file is read once per executed program

      while (true) {
         int guessQnt = 0; // the amount of made guesses
         Wordslist wordslist = new Wordslist(file);
         Hangman hangman = null;
         do {
            printMainMenu();
            input = readInt();
            switch (input) {
               case 1:
                  System.out.print("\n\nPlaying with random word.");
                  hangman = new Hangman(wordslist, guessQnt);
                  input = 0;
                  break;
               case 2:
                  System.out.print("How long words would you like to play with? >  ");
                  int length = readInt();
                  wordslist = wordslist.wordsWithCertainLength(length);
                  if(wordslist.giveWords().size() == 0){
                     System.out.println("Matching words not found in the file, exiting the program.");
                     System.exit(0);
                  }
                  System.out.print("\nPlaying with the words as long as " + length + " characters.");
                  hangman = new Hangman(wordslist, guessQnt);
                  
                  input = 0;
                  break;
                  
               case 3:
                  System.out.print("What characters should the words contain? (e.g type c_d_n_ for \"coding\") >   ");
                  String str = sc.nextLine();
                  wordslist = wordslist.wordsWithCertainChars(str);
                  if(wordslist.giveWords().size() == 0){
                     System.out.println("Matching words not found in the file, exiting the program.");
                     System.exit(0);
                  }
                  System.out.print("\nPlaying with the words containing at least following characters " + str);
                  hangman = new Hangman(wordslist, guessQnt);
                  input = 0;
                  break;
                
               case 0:
                  System.out.println("Exiting...");
                  System.exit(0);
               default:
                  System.out.println("False input, please try again > ");

            }
         } while (input != 0);
         do { // the game itself
            System.out.println("(You can type 0 to quit the game)");
            System.out.println("Guesses left:   " + hangman.guessesRemaining());
            System.out.println("Guessed characters:  " + hangman.guesses());
            System.out.print("\n");
            System.out.println(hangman.getProgress());
            System.out.print("Your guess > ");
            guess = readChar();
            System.out.print("\n");
            hangman.guess(guess);

            
            if(hangman.guessesRemaining() <= 0){ // if the player runs out of guesses, game over´.
               System.out.println("You ran out of guesses, game over!");
               System.out.println("Correct word was:  " + hangman.word());
               break;
           }
         } while (!hangman.isItOver()); 
         printEnding();

      } // While loop for the whole game

   }

   public static char readChar() {
      boolean ok = false;
      char letter = '\0';

      do {

         try {
            letter = sc.next().charAt(0);
            if (letter == '0') { // If the user decides to quit midgame
               System.out.println("Exiting...");
               System.exit(0);
            }
            sc.nextLine();
            ok = true;
         } catch (InputMismatchException ime) {
            sc.nextLine();
            System.out.print("Something went wrong, please try again > ");
         }

      } while (!ok); 

      return letter;
   }

   public static int readInt() {
      boolean ok = false;
      int integer = 0;

      do {

         try {
            integer = sc.nextInt();
            sc.nextLine();
            ok = true;
         } catch (InputMismatchException ime) {
            sc.nextLine();
            System.out.print("Something went wrong, please try again > ");
         }

      } while (!ok); 

      return integer;

   }

   public static void printMainMenu() {
      System.out.println("Type in your choice, please.");
      System.out.println(" 1) Play with random word");
      System.out.println(" 2) Play with words of certain length");
      System.out.println(" 3) Play with words, which have at least characters of your choice");
      System.out.println(" 0) Quit");
      System.out.print("\nYour choice > ");
   }

   public static void printEnding() {

      int input;
      do {
         System.out.println("What would you like to do next? Type in your choice, please.");
         System.out.println(" 1) Back to main menu");
         System.out.println(" 0) Quit");
         System.out.print("\nYour choice > ");
         input = readInt();
         switch (input) {
            case 1:
               System.out.print("\n");
               System.out.println("Using the same wordslist as in previous game.");
               input = 0;
               break;
            case 0:
               System.out.println("Exiting.. Thank you for playing!");
               System.exit(0);
            default:
               System.out.println("Wrong input, please try again.");
         }
      } while (input != 0);
   }
}
