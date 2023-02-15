public interface Activation {
    double Activate(double[] inputs, int index);
    
    double Derivative(double[] inputs, int index);

    ActivationType GetActivationType();
}
