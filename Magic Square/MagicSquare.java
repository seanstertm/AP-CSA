import java.nio.channels.NotYetConnectedException;
import java.util.*;

public class MagicSquare
{
    private static int magicNumber;
    private static int size;
    private static int[] square;
    
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Magic square generator!\n\nEnter size: ");
        size = input.nextInt();
        input.close();
        
        long start = System.currentTimeMillis();
        
        setMagicNumber();
        if(size % 2 == 1) {
            createOddMagicSquare();
        } else { 
            throw new NotYetConnectedException();
        }

        long end = System.currentTimeMillis();
        
        System.out.println("Time taken: " + (end - start)/1000.0);

        System.out.println("Magic Number: " + magicNumber);
    }
    
    private static void setMagicNumber() {
        magicNumber = (int)(0.5 * size * size * size + 0.5 * size);
    }

    private static void createOddMagicSquare() {
        square = new int[size * size];
        int index = size/2;
        for(int i = 1; i <= size * size; i++) {
            square[index] = i;
            if(square[upAndRight(index)] == 0) {
                index = upAndRight(index);
            } else {
                index += size;
                if(index >= size * size) {
                    index -= size * size;
                }
            }
        }
        printSquare();
    }

    private static int upAndRight(int index) {
        int newIndex = index - (size - 1);
        if(index % size == size - 1) {
            newIndex -= size;
        }
        if(newIndex < 0) {
            newIndex += size * size;
        }
        if(index == size - 1) {
            newIndex = size * 2 - 1;
        }

        return newIndex;
    }

    // The method below prints the contents of an array.
    // There is no need to modify.
    public static void printArray(int[][] arr)
	{
	    for(int row = 0; row < arr.length; row++)
	    {
	        for(int col = 0; col < arr[row].length; col++)
	        {
	            if(1.0*arr[row][col]/10 < 1)
	            {
	                System.out.print(" ");
	            }
	            System.out.print(arr[row][col] + "  ");
	        }
	        System.out.println();
	    }
	}

    public static void printSquare() {
        for(int i = 0; i < size * size; i++) {
            System.out.print(square[i] + " ");
            if(square[i] < 10) {
                System.out.print(" ");
            }
            if((i + 1) % size == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}