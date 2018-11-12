import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Var.
 * <p>
 * Omer Zucker
 * 200876548
 */
public class Var implements Expression {
    private String variable;

    /**
     * Construct var with variable (String).
     *
     * @param var a variable
     */
    public Var(String var) {
        this.variable = var;
    }

    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     *
     * @param assignment given assignment
     * @return result of the assignment
     * @throws Exception variable is not in assignment
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        // variable is in the assignment
        if (assignment.containsKey(this.variable)) {
            return assignment.get(this.variable);
        }
        throw new Exception("this variable is not in the assignment");
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     *
     * @return exception for empty assignment
     * @throws Exception expression is not in assignment
     */
    public double evaluate() throws Exception {
        // this expression compare from variable
        throw new Exception("this expression contains a variable");
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression
     */
    public List<String> getVariables() {
        List<String> list = new ArrayList<String>();
        list.add(this.variable);
        return list;
    }

    /**
     * Returns a nice string representation of the expression.
     *
     * @return a nice string representation of the expression
     */
    public String toString() {
        return this.variable;
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
        // if this variable is val no meter if is capital or small letter.
        if (this.variable.equalsIgnoreCase(var)) {
            return expression;
        }
        return this;
    }

    /**
     * Returns the expression tree resulting from differentiating the current expression relative to variable `var`.
     *
     * @param var a variable
     * @return differentiate of var
     */
    public Expression differentiate(String var) {
        // var might be a differentiate of other variable
        if (!this.toString().equalsIgnoreCase(var)) {
            return (Expression) new Num(0);
        }
        return (Expression) new Num(1);

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
