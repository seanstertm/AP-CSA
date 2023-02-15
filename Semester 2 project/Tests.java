public class Tests {
    public static void main(String[] args) {
        Layer testLayer = new Layer(5, 1);
        for(double weight : testLayer.weights) {
            System.out.println(weight);
        }
    }
}
