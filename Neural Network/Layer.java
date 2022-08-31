public class Layer {
    int numNodesIn, numNodesOut;
    double[][] weights;
    double[] biases;

    // Constructor
    public Layer(int numNodesIn, int numNodesOut) {
        this.numNodesIn = numNodesIn;
        this.numNodesOut = numNodesOut;

        weights = new double[numNodesIn][numNodesOut];
        biases = new double[numNodesOut];
    }

    // Calculate outputs for the layer
    public double[] CalculateOutputs(double[] inputs) {
        double[] activations = new double[numNodesOut];

        for(int nodeOut = 0; nodeOut < numNodesOut; nodeOut++) {
            double weightedInput = biases[nodeOut];
            for(int nodeIn = 0; nodeIn < numNodesIn; nodeIn++) {
                weightedInput += inputs[nodeIn] * weights[nodeIn][nodeOut];
            }
            activations[nodeOut] = weightedInput;
        }

        return activations;
    }

    // Sigmoid function to squish outputs from 0 to 1
    double ActivationFunction(double weightedInput) {
        return 1 / (1 + Math.exp(-weightedInput));
    }

    double NodeCost(double outputActivation, double expectedOutput) {
        double error = outputActivation - expectedOutput;
        return error * error;
    }
}
