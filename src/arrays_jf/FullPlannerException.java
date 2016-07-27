package arrays_jf;

/**
 * Exception is thrown when the Planner is at max capacity and the user tries to add
 * another Course to the Planner.
 * 
 * @author flyasth3sky
 */
public class FullPlannerException extends RuntimeException {

    /**
     * Creates a new instance of <code>FullPlannerException</code> without
     * detail message.
     */
    public FullPlannerException() {
    }

    /**
     * Constructs an instance of <code>FullPlannerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FullPlannerException(String msg) {
        super(msg);
    }
}
