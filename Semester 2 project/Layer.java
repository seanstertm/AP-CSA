import java.util.Random;

public class Layer {
    public int numNodesIn;
    public int numNodesOut;
    // Flattened to 1d arrays
    public double[] weights;
    public double[] biases;

    public double[] lossGradientWeights;
    public double[] lossGradientBiases;

    public double[] weightVelocities;
    public double[] biasVelocities;

    public Activation activation;

    public Layer(int numNodesIn, int numNodesOut) {
        this.numNodesIn = numNodesIn;
        this.numNodesOut = numNodesOut;
        // initialize activation

        weights = new double[numNodesIn * numNodesOut];
        biases = new double[numNodesOut];
        lossGradientWeights = new double[numNodesIn * numNodesOut];
        lossGradientBiases = new double[numNodesOut];
        weightVelocities = new double[numNodesIn * numNodesOut];
        biasVelocities = new double[numNodesOut];

        InitializeRandomWeights();
    }

    public double[] CalculateOutputs(double[] inputs) {
        double[] weightedInputs = new double[numNodesOut];
        for(int nodeOut = 0; nodeOut < numNodesOut; nodeOut++) {
            double weightedInput = biases[nodeOut];
            for(int nodeIn = 0; nodeIn < numNodesIn; nodeIn++) {
                weightedInput += inputs[nodeIn] * GetWeight(nodeIn, nodeOut);
            }
            weightedInputs[nodeOut] = weightedInput;
        }

        double[] activations = new double[numNodesOut];
        for(int nodeOut = 0; nodeOut < numNodesOut; nodeOut++) {
            activations[nodeOut] = activation.Activate(weightedInputs, nodeOut);
        }
        return activations;
    }

    public double GetWeight(int nodeIn, int nodeOut) {
        int flattenedIndex = nodeOut * numNodesIn + nodeIn;
        return weights[flattenedIndex];
    }

    private void InitializeRandomWeights() {
        Random rng = new Random();

        for(int i = 0; i < weights.length; i++) {
            // formula for normal curve distribution
            weights[i] = (Math.sqrt(-2.0 * Math.log(1 - rng.nextDouble())) * Math.cos(2.0 * Math.PI * (1 - rng.nextDouble()))) / Math.sqrt(numNodesIn);
        }
    }
}
