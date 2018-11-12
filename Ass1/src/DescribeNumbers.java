/**
 * DescribeNumbers function prints max/min/average values.
 * @author Omer zucker
 * @since 25/03/2017.
 * @version 1.0
 */
public class DescribeNumbers {
    /**
     * main function.
     * @param args input from user
     */
    public static void main(String[] args) {
        int[] numbers; // declares an array of integers
        numbers = new int[args.length]; // allocates memory for number of args in input
        numbers = stringsToInts(args);
        System.out.println("min: " + min(numbers));
        System.out.println("max: " + max(numbers));
        System.out.println("avg: " + avg(numbers));
    }

    /**
     * The function gets array of stings and turn it to array of int.
     * @param numbers gets array of strings from main
     * @return array of int
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] intArray; // declares an array of integers
        intArray = new int[numbers.length]; // allocates memory for number of args in input
        for (int i = 0; i < numbers.length; i++) {
            intArray[i] = Integer.parseInt(numbers[i]);
        }
        return intArray;
    }

    /**
     * The function gets array of numbers and returns the min value.
     * @param numbers gets array of strings from main
     * @return min value
     */
    public static int min(int[] numbers) {
        int min = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }

    /**
     * The function gets array of numbers and returns the max value.
     * @param numbers gets array of strings from main
     * @return max value
     */
    public static int max(int[] numbers) {
        int max = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (max < numbers[i]) {
                max = numbers[i];
            }
        }
        return max;
    }

    /**
     * The function gets array of numbers and calculate the average.
     * @param numbers gets array of strings from main
     * @return average of numbers in array
     */
    public static float avg(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        float avg = (float) sum / numbers.length;
        return avg;
    }
}

