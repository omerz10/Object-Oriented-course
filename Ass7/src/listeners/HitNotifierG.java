package listeners;

/**
 * HitNotifier.
 *
 * @param <T> a Task
 */
public interface HitNotifierG<T> {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl a listeners.HitListener
     */
    void addHitListener(HitListenerG<T> hl);


    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl a listeners.HitListener
     */
    void removeHitListener(HitListenerG<T> hl);
}