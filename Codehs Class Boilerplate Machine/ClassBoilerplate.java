import java.awt.AWTException;
import java.util.*;

public class ClassBoilerplate {
    // Stores information retrieved from the user. Variables and name of file
    public static List<WrittenVariable> vars = new ArrayList<WrittenVariable>();
    public static String name;

    public static void main(String[] args) throws AWTException {
        // Initialize user input and typer
        Scanner input = new Scanner(System.in);
        Typer.SetUp();

        System.out.print("Enter class name: ");
        name = input.nextLine();
        System.out.println("\nEnter instance variable type followed by name using proper camelCase. Enter nothing to finish.");

        // Variable to display how many instance variables the user has input so far
        int instanceVariable = 1;
        
        // Define user's instance variables
        while(true) {
            System.out.println("\nInstance Variable " + instanceVariable + ":");
            String response = input.nextLine();

            // Split response by spacebar
            String[] responseTuple = response.split(" ", 2);
            
            // If response is invalid escape loop
            if(responseTuple.length != 2) { System.out.println("None\n"); break; }

            // If valid, add to list and continue
            instanceVariable++;
            vars.add(new WrittenVariable(responseTuple[0], responseTuple[1]));
        }

        // Verify information on user's end
        System.out.println("Generate class " + name + " using variables:");
        for(WrittenVariable var : vars) {
            System.out.println(var);
        }
        System.out.println("\nProceed?: [Y]es/[N]o");
        String response = input.nextLine();

        // If the response starts with y
        if(response.substring(0,1).toLowerCase().equals("y")) {
            System.out.println("Please tab anywhere into the file you'd like to write to in 5 seconds.");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            ClassDefinition();
            InstanceVariables();
            Constructor();
            GettersAndSetters();
        }

        // Close input stream before terminating
        input.close();
    }

    // Deletes everything in the files and begins a new definition and skeleton
    public static void ClassDefinition() throws AWTException {
        Typer.DelAll();
        Typer.Type("public class " + name + " ");
        Typer.BracketEnter();
        Typer.Enter();
    }

    // Types out the private instance variables for the project
    public static void InstanceVariables() throws AWTException {
        for(WrittenVariable var : vars) {
            Typer.Type("private " + var + ";");
            Typer.Enter();
        }
        Typer.Enter(2);
    }

    // Types out a default constructor, assigning all values to their instance variables
    public static void Constructor() throws AWTException {
        // This String join method essentially turns the List<WrittenVariable> into 
        // List<String> by turning it to a stream, mapping each item to a string, then 
        // turning the stream back into a list to be joined with commas
        Typer.Type("public " + name + "(" + String.join(", ", (vars.stream().map(var -> var.toString())).toList()) + ") ");
        Typer.BracketEnter();
        for(WrittenVariable var : vars) {
            Typer.Type("this." + var.getName() + " = " + var.getName() + ";");
            Typer.Enter();
        }
        Typer.Backspace(3);
        Typer.Down();
        Typer.Enter(3);
    }

    // Creates a pair of public get and set functions
    public static void GettersAndSetters() throws AWTException {
        for(WrittenVariable var : vars) {
            Getter(var);
            Typer.Down();
            Typer.Enter(2);

            Setter(var);
            Typer.Down();
            Typer.Enter(3);
        }
        Typer.Backspace(6);
    }

    // Writes the get function but leaves cursor inside
    public static void Getter(WrittenVariable var) throws AWTException {
        Typer.Type(var.generateGetter());
        Typer.BracketEnter();
        Typer.Type("return " + var.getName() + ";");
    }

    // Writes the set function but leaves cursor inside
    public static void Setter(WrittenVariable var) throws AWTException {
        Typer.Type(var.generateSetter());
        Typer.BracketEnter();
        Typer.Type("this." + var.getName() + " = " + var.getName() + ";");
    }
}
