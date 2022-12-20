import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CombinationLockRunner {
    public static void main(String[] args) {
        Scanner file;
        try {
            file = new Scanner(new File("Combination Lock/Combination.txt"));
        } catch(IOException e) {
            System.out.println("Unable to read file");
            System.out.println(e.getMessage());
            return;
        }

        CombinationLock combination = new CombinationLock(file.useDelimiter("\\A").next());
        Scanner input = new Scanner(System.in);

        while(true) {
            System.out.println("Enter your guess:");
            String guess = input.nextLine();
            String accuracy;
            try {
                accuracy = combination.getClue(guess);
            } catch(IllegalArgumentException e) {
                System.out.println(e.getLocalizedMessage());
                accuracy = "-";
            }
            if(guess.equals(accuracy)) {
                break;
            }
            System.out.println(accuracy + "\n");
        }

        System.out.println("Correct!");

        input.close();
    }
}
