/**
 * Sort Function is sorting a list of numbers in ascend or descend order.
 * @author Omer zucker
 * @since 25/03/2017.
 * @version 1.0
 */
public class Sort {
    /**
     * main function.
     * @param args input from user
     */
    public static void main(String[] args) {
        int[] numbers; // declares an array of integers
        numbers = new int[args.length - 1]; // allocates memory for number of args in input
        if (args[0].equals("asc")) {
            numbers = stringsToInts(args);
            ascSort(numbers);
        } else {   // args[0].equals("desc")
            numbers = stringsToInts(args);
            descSort(numbers);
        }
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }

    /**
     * The function gets array of stings and turn it to array of int without first arg.
     * @param numbers gets array of strings from main
     * @return array of int
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] intArray; // declares an array of integers
        intArray = new int[numbers.length - 1]; // allocates memory for number of args in input
        for (int i = 0; i < (numbers.length - 1); i++) {
            intArray[i] = Integer.parseInt(numbers[i + 1]);
        }
        return intArray;
    }

    /**
     * The function sorts an array of numbers in ascend order.
     * @param numbers list of numbers
     */
    public static void ascSort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    swap(numbers, j);
                }
            }
        }

    }

    /**
     * The function sorts an array of numbers in ascend order.
     * @param numbers list of numbers
     */
    public static void descSort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - 1; j++) {
                if (numbers[j] < numbers[j + 1]) {
                    swap(numbers, j);
                }
            }
        }
    }

    /**
     * The function gets an array of integers and swap 2 args.
     * @param numbers array of numbers
     * @param j index to swap
     */
    public static void swap(int[] numbers, int j) {
        int temp = numbers[j];
        numbers[j] = numbers[j + 1];
        numbers[j + 1] = temp;
    }
}
