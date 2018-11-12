import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Num.
 * <p>
 * Omer Zucker
 * 200876548
 */
public class Num implements Expression {
    private double number;

    /**
     * Construct Num with a number (double).
     *
     * @param number a number
     */
    public Num(double number) {
        this.number = number;
    }

    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     *
     * @param assignment given assignment
     * @return result of the assignment
     * @throws Exception number is not in assignment
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.number;
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     *
     * @return exception for empty assignment
     * @throws Exception number is not in the assignment
     */
    public double evaluate() throws Exception {
        return this.number;
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression
     */
    public List<String> getVariables() {
        List<String> list = new ArrayList<String>();
        return list;
    }

    /**
     * Returns a nice string representation of the expression.
     *
     * @return a nice string representation of the expression
     */
    public String toString() {
        return Double.toString(this.number);
    }

    /**
     * Returns a new expression in which all occurrences of the variable var are replaced with the
     * provided expression (Does not modify the current expression).
     *
     * @param var        a variable (String)
     * @param expression an expression
     * @return the expression that was assign to the var
     */
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * Returns the expression tree resulting from differentiating the current expression relative to variable `var`.
     *
     * @param var a variable
     * @return differentiate of var
     */
    public Expression differentiate(String var) {
        return (Expression) new Num(0);
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @return an expression
     */
    public Expression simplify() {
        return this;
    }

    /**
     * Returns the commutative expression.
     *
     * @return commutative expression
     */
    public Expression commutativeExp() {
        return this;
    }

    /**
     * Returns true if the expression is commutative.
     *
     * @return true if the expression is commutative
     */
    public boolean isCommutative() {
        return false;
    }
}
