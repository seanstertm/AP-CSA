import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Typer {

    private static Robot robot;

    // Creates new robot
    public static void SetUp() throws AWTException {
        robot = new Robot();
    }

    // Will press and release key
    public static void Press(int keycode) {
        robot.keyPress(keycode);
        robot.keyRelease(keycode);
    }

    // Will press and release key while holding shift
    public static void ShiftPress(int keycode) {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(keycode);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(keycode);
    }

    // Same as [ctrl] + [A] -> [backspace]
    public static void DelAll() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
    }

    // Method to type string
    public static void Type(String payload) {
        char[] letters = payload.toCharArray();
        for(char letter : letters) {
            // Handle parenthesis as special cases since they need shift key
            if(letter == '(') { ShiftPress(KeyEvent.VK_9); }
            else if(letter == ')') { ShiftPress(KeyEvent.VK_0); }
            // Check capitalization and use corresponding function
            else if(Character.toLowerCase(letter) != letter) {
                ShiftPress(KeyEvent.getExtendedKeyCodeForChar(letter));
            } else {
                Press(KeyEvent.getExtendedKeyCodeForChar(letter));
            }
        }
    }

    // Handle multiple arguments and type them on different lines
    public static void Type(String...payload) {
        for(String line : payload) {
            Type(line);
            Enter();
        }
    }

    // Common kestrokes with overloads for repeated use
    public static void BracketEnter() {
        ShiftPress(KeyEvent.VK_OPEN_BRACKET);
        Enter();
    }

    public static void Enter() {
        Press(KeyEvent.VK_ENTER);
    }

    public static void Enter(int times) {
        for(int i = 0; i < times; i++) {
            Enter();
        }
    }

    public static void Down() {
        Press(KeyEvent.VK_DOWN);
    }

    public static void Down(int times) {
        for(int i = 0; i < times; i++) {
            Down();
        }
    }

    public static void Backspace() {
        Press(KeyEvent.VK_BACK_SPACE);
    }

    public static void Backspace(int times) {
        for(int i = 0; i < times; i++) {
            Backspace();
        }
    }
}