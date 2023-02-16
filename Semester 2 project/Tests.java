import java.util.*;

public class Tests {
    public static void main(String[] args) {
        SmallerOfTwoNumbersTest();
    }

    public static void SmallerOfTwoNumbersTest() {
        Random rng = new Random();

        Scanner input = new Scanner(System.in);

        NeuralNetwork network = new NeuralNetwork(2, 3, 2);

        for(int i = 0; i < 150; i++) {
            DataPoint[] randomData = new DataPoint[50];
            for(int j = 0; j < 50; j++) {
                double x = rng.nextDouble();
                double y = rng.nextDouble();
                randomData[j] = new DataPoint(new double[]{x, y}, x > y ? 1 : 0, 2);
            }
            network.Learn(randomData);
        }

        double[] inputs = new double[2];
        System.out.print("Finished Training\n\nEnter a double: ");
        inputs[0] = input.nextDouble();
        System.out.print("Enter another double: ");
        inputs[1] = input.nextDouble();

        input.close();

        NetworkOutput output = network.Classify(inputs);
        System.out.println("\nSmallest number is: " + inputs[output.predictedClass]);
        for(int i = 0; i < inputs.length; i++) {
            System.out.println(inputs[i] +" confidence: " + Math.round(output.outputs[i] * 10000) / 100 + "%");
        }
    }
}
