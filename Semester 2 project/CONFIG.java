public class CONFIG {
    public static Activation activationType = new Sigmoid();
    public static Loss lossType = new SquaredResiduals();

    public static double learnRate = 0.05;
    public static double regularization = 0;
    public static double momentum = 1;
}
