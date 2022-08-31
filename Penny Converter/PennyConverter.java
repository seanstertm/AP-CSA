import java.util.InputMismatchException;
import java.util.Scanner;

public class PennyConverter {
    // Entry point
    public static void main(String[] args) {
        // Initialize scanner
        Scanner s = new Scanner(System.in);

        // Grab user input
        int pennies = PennyCountInput(s);
        
        // Return penny counts
        System.out.print(String.format("Dollars: %d\nQuarters: %d\nDimes: %d\nNickels: %d\nPennies: %d", (pennies/100) + 0 * (pennies %= 100),(pennies/25) + 0 * (pennies %= 25),(pennies/10) + 0 * (pennies %= 10),(pennies/5) + 0 * (pennies %= 5),pennies));
    }

    // Retrieve user input
    private static int PennyCountInput(Scanner s) {
        // Surrounded in try statement to retry if fail
        try {
            // Request user pennies
            System.out.print("Please enter how many pennies you have: ");

            // Store next integer
            int pennies = s.nextInt();

            return pennies;
        } catch (InputMismatchException e) {
            // When a type int is not returned
            System.out.print("\nThat isn't a number.\n\n");

            // Clears buffer
            s.nextLine();

            // Calls function again for retry
            return PennyCountInput(s);
        }
    }
}