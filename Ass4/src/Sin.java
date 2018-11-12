import java.util.List;
import java.util.Map;

/**
 * Sin.
 * <p>
 * Omer Zucker
 * 200876548
 */
public class Sin extends UnaryExpression implements Expression {

    /**
     * Construct Sin by expression.
     *
     * @param exp an expression
     */
    public Sin(Expression exp) {
        super(exp, "sin");
    }

    /**
     * Construct Sin by number.
     *
     * @param number a number
     */
    public Sin(double number) {
        super(new Num(number), "sin");
    }

    /**
     * Construct Sin by variable.
     *
     * @param variable a number
     */
    public Sin(String variable) {
        super(new Var(variable), "sin");
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
        return super.evaluate(assignment);
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     *
     * @return exception for empty assignment
     * @throws Exception expression is not in assignment
     */
    public double evaluate() throws Exception {
        return super.evaluate();
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression
     */
    public List<String> getVariables() {
        return super.getVariables();
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
        return super.assign(var, expression);
    }

    /**
     * Returns a nice string representation of the expression.
     *
     * @return a nice string representation of the expression
     */
    public String toString() {
        return super.toString();
    }

    /**
     * Returns the expression tree resulting from differentiating the current expression relative to variable `var`.
     *
     * @param var a variable
     * @return differentiate of var
     */
    public Expression differentiate(String var) {
        return new Mult(this.getExpression1().differentiate(var), new Cos(this.getExpression1()));
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @return simplify of the expression
     */
    public Expression simplify() {
        return super.simplify();
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