package animations;

/**
 * Counter.
 * <p>
 * Omer Zucker
 * 200876548
 */
public class Counter {
    private int count;

    /**
     * add number to current count.
     *
     * @param number a number
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number a number
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * get current count.
     *
     * @return value of counter
     */
    public int getValue() {
        return count;
    }
}
