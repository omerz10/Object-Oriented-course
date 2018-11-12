import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 * BinaryExpression.
 * <p>
 * Omer Zucker
 * 200876548
 */
public abstract class BinaryExpression extends BaseExpression {

    /**
     * Construct new binary Expression by expression and number.
     *
     * @param exp1    an expression
     * @param exp2    a expression
     * @param oprator an operator
     */
    public BinaryExpression(Expression exp1, Expression exp2, String oprator) {
        super(exp1, exp2, oprator);
    }

    /**
     * Construct new binary Expression by expression and number.
     *
     * @param exp      an expression
     * @param number   a number
     * @param operator an operator
     */
    public BinaryExpression(Expression exp, Num number, String operator) {
        super(exp, number, operator);
    }

    /**
     * Construct new binary Expression by number and expression.
     *
     * @param exp      an expression
     * @param number   a number
     * @param operator an operator
     */
    public BinaryExpression(Num number, Expression exp, String operator) {
        super(number, exp, operator);
    }

    /**
     * Construct new binary Expression by number and variable.
     *
     * @param number1  a number
     * @param number2  a number
     * @param operator an operator
     */
    public BinaryExpression(Num number1, Num number2, String operator) {
        super(number1, number2, operator);
    }

    /**
     * Construct new binary Expression by expression and variable.
     *
     * @param exp      expression
     * @param variable expression2
     * @param operator an operator
     */
    public BinaryExpression(Expression exp, Var variable, String operator) {
        super(exp, variable, operator);
    }

    /**
     * Construct new binary Expression by variable and expression.
     *
     * @param variable a variable
     * @param exp      an expression
     * @param operator an operator
     */
    public BinaryExpression(Var variable, Expression exp, String operator) {
        super(variable, exp, operator);
    }

    /**
     * Construct new binary Expression by two variables.
     *
     * @param variable1 a variable
     * @param variable2 a variable
     * @param operator  an operator
     */
    public BinaryExpression(Var variable1, Var variable2, String operator) {
        super(variable1, variable2, operator);
    }

    /**
     * Construct new binary Expression by variable and number.
     *
     * @param variable a variable
     * @param number   a number
     * @param operator an operator
     */
    public BinaryExpression(Var variable, Num number, String operator) {
        super(variable, number, operator);
    }

    /**
     * Construct new binary Expression by number and variable.
     *
     * @param number   a number
     * @param variable a variable
     * @param operator an operator
     */
    public BinaryExpression(Num number, Var variable, String operator) {
        super(number, variable, operator);
    }

    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     *
     * @param assignment given assignment
     * @return result of expression
     * @throws Exception expression is not in assignment
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.calculateFinalResult(this.getExpression1().evaluate(assignment),
                this.getExpression2().evaluate(assignment));
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     *
     * @return result of expression
     * @throws Exception expression is not in assignment
     */
    double evaluate() throws Exception {
        return this.calculateFinalResult(this.getExpression1().evaluate(), this.getExpression2().evaluate());
    }

    /**
     * Return final result after calculates with given one of operators.
     *
     * @param e1 an expression
     * @param e2 an expression
     * @return final result
     */
    public double calculateFinalResult(double e1, double e2) {
        double finalResult = 0.0;
        // Plus
        if (this.getOperator().equals("+")) {
            finalResult = e1 + e2;
            // Minus
        } else if (this.getOperator().equals("-")) {
            finalResult = e1 - e2;
            // Multiply
        } else if (this.getOperator().equals("*")) {
            finalResult = e1 * e2;
            // Divide
        } else if (this.getOperator().equals("/")) {
            finalResult = e1 / e2;
            // Pow
        } else if (this.getOperator().equals("^")) {
            finalResult = Math.pow(e1, e2);
            // Log
        } else if (this.getOperator().equalsIgnoreCase("log")) {
            finalResult = Math.log(e2) / Math.log(e1);
        }
        return finalResult;

    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression
     */
    public List<String> getVariables() {
        List<String> list = new ArrayList<String>();
        // add all variable from expression 1 & 2
        list.addAll(this.getExpression1().getVariables());
        list.addAll(this.getExpression2().getVariables());
        Set<String> set = new HashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
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
        Expression exp1 = this.getExpression1().assign(var, expression);
        Expression exp2 = this.getExpression2().assign(var, expression);
        return this.createExpression(exp1, exp2, this.getOperator());
    }

    /**
     * @param e1       an expression
     * @param e2       an expression
     * @param operator an operator
     * @return final expression
     */
    public Expression createExpression(Expression e1, Expression e2, String operator) {
        if (operator.equals("+")) {
            return new Plus(e1, e2);
        } else if (operator.equals("-")) {
            return new Minus(e1, e2);
        } else if (operator.equals("*")) {
            return new Mult(e1, e2);
        } else if (operator.equals("/")) {
            return new Div(e1, e2);
        } else if (operator.equals("^")) {
            return new Pow(e1, e2);
        } else if (operator.equalsIgnoreCase("log")) {
            return new Log(e1, e2);
        } else {
            throw new RuntimeException("Unfamiliar operator");
        }
    }


}
