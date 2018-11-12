import java.util.Map;
import java.util.TreeMap;

/**
 * ExpressionsTest.
 *
 * Omer Zucker
 * 200876548
 */
public class ExpressionsTest {

    /**
     * @param args not in use
     * @throws Exception for evaluate
     */
    public static void main(String[] args) throws Exception {
        Expression e = new Plus(new Plus(new Mult(new Num(2), "x"),
                new Sin(new Mult(new Num(4), "y"))), new Pow("e", "x"));
        System.out.println(e);
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", Math.E);
        double e1 = e.evaluate(assignment);
        System.out.println(e1);
        Expression e2 = e.differentiate("x");
        System.out.println(e2);
        System.out.println(e2.evaluate(assignment));
        System.out.println(e2.simplify());
    }
}
