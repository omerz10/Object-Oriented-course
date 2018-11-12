import java.util.List;
import java.util.Map;

/**
 * Log.
 * <p>
 * Omer Zucker
 * 200876548
 */
public class Log extends BinaryExpression implements Expression {

    /**
     * Construct Log with two expressions.
     *
     * @param leftExp  an expression
     * @param rightExp an expression
     */
    public Log(Expression leftExp, Expression rightExp) {
        super(leftExp, rightExp, "log");
    }

    /**
     * Construct Log with expression and number.
     *
     * @param leftExp  an expression
     * @param rightExp a number
     */
    public Log(Expression leftExp, double rightExp) {
        super(leftExp, new Num(rightExp), "log");
    }

    /**
     * Construct Log with expression and number.
     *
     * @param leftExp  a number
     * @param rightExp an expression
     */
    public Log(double leftExp, Expression rightExp) {
        super(new Num(leftExp), rightExp, "log");
    }

    /**
     * Construct Log with two numbers.
     *
     * @param leftExp  a number
     * @param rightExp a number
     */
    public Log(double leftExp, double rightExp) {
        super(new Num(leftExp), new Num(rightExp), "log");
    }

    /**
     * Construct Log with expression and variable.
     *
     * @param leftExp  an expression
     * @param rightExp a variable
     */
    public Log(Expression leftExp, String rightExp) {
        super(leftExp, new Var(rightExp), "log");
    }

    /**
     * Construct Log with expression and variable.
     *
     * @param leftExp  a variable
     * @param rightExp an expression
     */
    public Log(String leftExp, Expression rightExp) {
        super(new Var(leftExp), rightExp, "log");
    }

    /**
     * Construct Log with two variables.
     *
     * @param leftExp  a variable
     * @param rightExp an variable
     */
    public Log(String leftExp, String rightExp) {
        super(new Var(leftExp), new Var(rightExp), "log");
    }

    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     *
     * @param assignment given assignment
     * @return result of the assignment
     * @throws Exception expression is not in assignment
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.evaluate(assignment);
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     *
     * @return result of expression
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
     * Returns a nice string representation of the expression.
     *
     * @return a nice string representation of the expression
     */
    public String toString() {
        return super.toString();
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
     * Returns the expression tree resulting from differentiating the current expression relative to variable `var`.
     *
     * @param var a variable
     * @return differentiate of var
     */
    public Expression differentiate(String var) {
        return new Div(this.getExpression1().differentiate(var), new Mult(this.getExpression2(),
                new Log(new Var("e"), this.getExpression1())));

    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @return simplify of the expression
     */
    public Expression simplify() {
        Expression exp1 = this.getExpression1().simplify();
        Expression exp2 = this.getExpression2().simplify();
        // there are no variables in this expression
        if (this.getVariables().isEmpty()) {
            try {
                return new Num(this.evaluate());
            } catch (Exception e) {
                System.out.print("");
            }
        }
        // log of base & and e
        try {
            if (((exp1.toString().equalsIgnoreCase("e")) && (exp2.evaluate() == (Math.E)))
                    || ((exp2.toString().equalsIgnoreCase("e")) && (exp1.evaluate() == (Math.E)))) {
                return new Num(1);
            }
        } catch (Exception e) {
            System.out.print("");
        }
        if (exp1.toString().equalsIgnoreCase(exp2.toString())) {
            return new Num(1);
        }
        return new Log(exp1, exp2);

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


