import java.util.List;
import java.util.Map;

/**
 * Minus.
 * <p>
 * Omer Zucker
 * 200876548
 */
public class Minus extends BinaryExpression implements Expression {

    /**
     * Construct Minus with two expressions.
     *
     * @param leftExp  an expression
     * @param rightExp an expression
     */
    public Minus(Expression leftExp, Expression rightExp) {
        super(leftExp, rightExp, "-");
    }

    /**
     * Construct Minus with expression and number.
     *
     * @param leftExp  an expression
     * @param rightExp a number
     */
    public Minus(Expression leftExp, double rightExp) {
        super(leftExp, new Num(rightExp), "-");
    }

    /**
     * Construct Minus with expression and number.
     *
     * @param leftExp  a number
     * @param rightExp an expression
     */
    public Minus(double leftExp, Expression rightExp) {
        super(new Num(leftExp), rightExp, "-");
    }

    /**
     * Construct Minus with two numbers.
     *
     * @param leftExp  a number
     * @param rightExp a number
     */
    public Minus(double leftExp, double rightExp) {
        super(new Num(leftExp), new Num(rightExp), "-");
    }

    /**
     * Construct Minus with expression and variable.
     *
     * @param leftExp  an expression
     * @param rightExp a variable
     */
    public Minus(Expression leftExp, String rightExp) {
        super(leftExp, new Var(rightExp), "-");
    }

    /**
     * Construct Minus with expression and variable.
     *
     * @param leftExp  a variable
     * @param rightExp an expression
     */
    public Minus(String leftExp, Expression rightExp) {
        super(new Var(leftExp), rightExp, "-");
    }

    /**
     * Construct Minus with two variables.
     *
     * @param leftExp  a variable
     * @param rightExp an variable
     */
    public Minus(String leftExp, String rightExp) {
        super(new Var(leftExp), new Var(rightExp), "-");
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
        return (Expression) new Minus(this.getExpression1().differentiate(var),
                this.getExpression2().differentiate(var));
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
        // expressions are the exactly the same (variables)
        if (exp1.toString().equals(exp2.toString())) {
            return new Num(0);
        }
        // exp1 is zero
        if (exp1.toString().equalsIgnoreCase("0.0")) {
            return exp2;
        }
        // exp1 is zero
        if (exp2.toString().equalsIgnoreCase("0.0")) {
            return exp1;
        }
        //expression is commutative
        if (this.getExpression1().isCommutative()) {
            if (exp1.commutativeExp().toString().equalsIgnoreCase(exp2.toString())) {
                return new Num(0);
            }
        }
        return new Minus(exp1, exp2);
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
