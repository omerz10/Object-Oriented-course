import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * UnaryExpression.
 * <p>
 * Omer Zucker
 * 200876548
 */
public class UnaryExpression extends BaseExpression {

    /**
     * Construct new unary Expression by expression.
     *
     * @param exp      an expression
     * @param operator an operator
     */
    public UnaryExpression(Expression exp, String operator) {
        super(exp, operator);
    }

    /**
     * Construct new unary Expression by number.
     *
     * @param number   a number
     * @param operator an operator
     */
    public UnaryExpression(double number, String operator) {
        super(new Num(number), operator);
    }

    /**
     * Construct new unary Expression by variable.
     *
     * @param variable a number
     * @param operator an operator
     */
    public UnaryExpression(String variable, String operator) {
        super(new Var(variable), operator);
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
        return calculateFinalResult(this.getExpression1().evaluate(assignment));
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     *
     * @return exception for empty assignment
     * @throws Exception expression is not in assignment
     */
    public double evaluate() throws Exception {
        return this.calculateFinalResult(getExpression1().evaluate());
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression
     */
    public List<String> getVariables() {
        List<String> list = new ArrayList<String>();
        list.addAll(this.getExpression1().getVariables());
        return list;
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @return an expression
     */
    public Expression simplify() {
        Expression oneExp = this.getExpression1();
        if (this.getVariables().isEmpty()) {
            try {
                return new Num(this.evaluate());
            } catch (Exception e) {
                System.out.print("");
            }
        }
        return createExpression(oneExp, this.getOperator());
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
        Expression newExp, finalExp;
        newExp = this.getExpression1().assign(var, expression);
        finalExp = this.createExpression(newExp, this.getOperator());
        return finalExp;
    }

    /**
     * calculate the result of the expression.
     *
     * @param number a number
     * @return the result of an expression.
     */
    public double calculateFinalResult(double number) {
        double finalResult = 0.0;
        if (this.getOperator().equalsIgnoreCase("neg")) {
            finalResult = -number;
        } else if (this.getOperator().equalsIgnoreCase("sin")) {
            finalResult = Math.sin(Math.toRadians(number));
        } else if (this.getOperator().equalsIgnoreCase("cos")) {
            finalResult = Math.cos(Math.toRadians(number));
        }
        return finalResult;
    }

    /**
     * @param exp      an expression
     * @param operator an operator
     * @return final expression
     */
    public Expression createExpression(Expression exp, String operator) {
        if (operator.equalsIgnoreCase("neg")) {
            return new Neg(exp);
        } else if (operator.equalsIgnoreCase("sin")) {
            return new Sin(exp);
        } else if (operator.equalsIgnoreCase("cos")) {
            return new Cos(exp);
        } else {
            throw new RuntimeException("Unfamiliar operator");
        }
    }
}
