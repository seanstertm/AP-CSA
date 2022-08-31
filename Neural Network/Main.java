import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;

// Main class to set up network
public class Main {

    public static long startTime; 
    
    // Entry Point
    public static void main(String[] args) {

        try (Scanner s = new Scanner(System.in)) {
            System.out.println("(G)uess | (L)earn | (C)onfig data");
            String text = s.nextLine().toLowerCase();
            switch(text) {
                case "g":
                    Guess();
                    break;
                case "l":
                    Learn();
                    break;
                case "c":
                    Config();
                    break;
            }
        }
    }

    // Running Guess mode
    public static void Guess() {
        try {
            ImageToColors("drawing.jpg");
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println(String.format("Finished translating image in %f seconds", ((System.nanoTime()-startTime)/1000000000.0)));
    }

    // Running Learn mode
    public static void Learn() {
        NeuralNetwork mainNetwork = new NeuralNetwork(Config.SIZE);
    }

    // Running Config mode
    public static void Config() {
        System.out.println(String.format("Image size: %d x %d",Config.WIDTH,Config.HEIGHT));
    }

    // Turns image path into an array of doubles representing the brightness of each pixel 0 to 1
    public static double[] ImageToColors(String imagePath) throws IOException {
        // Opens image
        final BufferedImage bufferedImageRead = ImageIO.read(new File(imagePath));

        final BufferedImage bufferedImage = new BufferedImage(bufferedImageRead.getWidth(), bufferedImageRead.getHeight(), BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(bufferedImageRead, 0, 0, null);

        final int[] imageBufferData = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();

        double[] returnArray = new double[Config.WIDTH * Config.HEIGHT];

        for (int i = 0 ; i < imageBufferData.length ; i++) {
            returnArray[i] = new Color(imageBufferData[i]).getRed() / 255.0;
        }

        return returnArray;
    }
}