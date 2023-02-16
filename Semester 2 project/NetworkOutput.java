public class NetworkOutput {
    public int predictedClass;
    public double[] outputs;

    public NetworkOutput(double[] outputs, int predictedClass) {
        this.outputs = outputs;
        this.predictedClass = predictedClass;
    }
}
