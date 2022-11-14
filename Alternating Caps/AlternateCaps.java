import java.util.Scanner;
import java.util.stream.*;

public class AlternateCaps {

    static String ans;
    static int i;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter string:");

        System.out.println(alternateCase(input.nextLine()));

        input.close();
    }

    public static String alternateCase(String str) {
        return String.join("", IntStream.range(0, str.length()).mapToObj(n -> (n % 2 == 0) ? Character.toUpperCase(str.toCharArray()[n]) : Character.toLowerCase(str.toCharArray()[n])).map(n -> n.toString()).collect(Collectors.toList()));
    }
}
