public class NeuralNetwork {
    Layer[] layers;

    // Constructor
    public NeuralNetwork(int[] layerSizes) {
        layers = new Layer[layerSizes.length - 1];
        for(int i = 0; i < layers.length; i++) {
            layers[i] = new Layer(layerSizes[i], layerSizes[i + 1]);
        }
    }

    // Runs inputs through the network
    double[] CalculateOutputs(double[] inputs) {
        for(Layer layer : layers) {
            inputs = layer.CalculateOutputs(inputs);
        }
        return inputs;
    }

    // Returns the index of the highest probable output
    int Classify(double[] inputs) {
        double[] outputs = CalculateOutputs(inputs);
        int returnValue = 0;
        for(int i = 0; i < outputs.length; i++) {
            returnValue = outputs[i] > outputs[returnValue] ? i : returnValue;
        }
        return returnValue;
    }

    // Runs an input through the network to find the cost
    double Cost(DataPoint dataPoint) {
        double[] outputs = CalculateOutputs(dataPoint.inputs);
        Layer outputLayer = layers[layers.length - 1];
        double cost = 0;

        for(int nodeOut = 0; nodeOut < outputs.length; nodeOut++) {
            cost += outputLayer.NodeCost(outputs[nodeOut], dataPoint.expectedOutputs[nodeOut]);
        }

        return cost;
    }

    // Runs a series of inputs through the network for average cost
    double Cost(DataPoint[] dataPoints) {
        double totalCost = 0;

        for(DataPoint dataPoint : dataPoints) {
            totalCost += Cost(dataPoint);
        }

        return totalCost/dataPoints.length;
    }
}
