package game;

/**
 * Task.
 *
 * @param <T> a given Task
 */
public interface Task<T> {
    /**
     * Return task.
     *
     * @return T task
     */
    T run();
}
