import java.util.Map;
import java.util.List;

/**
 * Expression interface.
 * <p>
 * Omer Zucker
 * 200876548
 */
public interface Expression extends ExpExtend {

    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     *
     * @param assignment given assignment
     * @return result of the assignment
     * @throws Exception value is not in assignment
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     *
     * @return result of expression
     * @throws Exception an exception
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     *
     * @return a nice string representation of the expression
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable var are replaced with the
     * provided expression (Does not modify the current expression).
     *
     * @param var        a variable (String)
     * @param expression an expression
     * @return the expression that was assign to the var
     */
    Expression assign(String var, Expression expression);

    /**
     *  Returns the expression tree resulting from differentiating the current expression relative to variable `var`.
     * @param var a variable
     * @return differentiate of expression
     */
    Expression differentiate(String var);

    /**
     *  Returned a simplified version of the current expression.
     *
     *  @return simplify of the expression
     */
    Expression simplify();
}
