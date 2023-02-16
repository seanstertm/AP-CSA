// These are not real JSONs, only my files can interpret these numbers
// This is done because codehs does not support external libraries

import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;

public class NetworkJson {
    public static String StringifyNetwork(NeuralNetwork network) {
        String string = Arrays.toString(network.layerSizes);

        return string;
    }

    public static void SaveNetwork(NeuralNetwork network) {
        try{
            FileWriter writer = new FileWriter("network.txt");
            writer.write(StringifyNetwork(network));
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static NeuralNetwork LoadNetwork() {
        FileReader reader;
        try {
            reader = new FileReader("network.txt");
        
            ArrayList<Character> networkString = new ArrayList<Character>();

            while(true) {
                if(reader.read() == -1) { break; }
                networkString.add((char)reader.read());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        NeuralNetwork network = new NeuralNetwork();

        return network;
    }

    // Double.parseDouble()
}
