package listeners;

/**
 * listeners.HitNotifier.
 *
 * Omer Zucker
 * 200876548
 */
public interface HitNotifier {
      /**
     * Add hl as a listener to hit events.
     * @param hl a listeners.HitListener
     */
    void addHitListener(HitListener hl);


    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl a listeners.HitListener
     */
    void removeHitListener(HitListener hl);
}
