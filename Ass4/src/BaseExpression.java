/**
 * BaseExpression.
 *
 * Omer Zucker
 * 200876548
 */
public abstract class BaseExpression {
    private Expression expression1;
    private Expression expression2;
    private String operator;

    /**
     * Construct new Expression by 1 expression.
     *
     * @param exp     an expression
     * @param operator an operator
     */
    public BaseExpression(Expression exp, String operator) {
        this.expression1 = exp;
        this.operator = operator;
    }

    /**
     * Construct new expression by a number.
     *
     * @param number   a number
     * @param operator an operator
     */
    public BaseExpression(Num number, String operator) {
        this.expression1 = number;
        this.operator = operator;
    }

    /**
     * Construct new expression by a variable.
     *
     * @param variable a variable
     * @param operator an operator
     */
    public BaseExpression(Var variable, String operator) {
        this.expression1 = variable;
        this.operator = operator;
    }

    /**
     * Construct new expression by two expressions.
     *
     * @param exp1     expression1
     * @param exp2     expression2
     * @param operator an operator
     */
    public BaseExpression(Expression exp1, Expression exp2, String operator) {
        this.expression1 = exp1;
        this.expression2 = exp2;
        this.operator = operator;
    }

    /**
     * Construct new expression by expression and number.
     *
     * @param exp      an expression
     * @param number   a number
     * @param operator an operator
     */
    public BaseExpression(Expression exp, Num number, String operator) {
        this.expression1 = exp;
        this.expression2 = number;
        this.operator = operator;
    }

    /**
     * Construct new expression by number and expression.
     *
     * @param exp      an expression
     * @param number   a number
     * @param operator an operator
     */
    public BaseExpression(Num number, Expression exp, String operator) {
        this.expression1 = number;
        this.expression2 = exp;
        this.operator = operator;
    }

    /**
     * Construct new expression by number and variable.
     *
     * @param number1  a number
     * @param number2 a number
     * @param operator an operator
     */
    public BaseExpression(Num number1, Num number2, String operator) {
        this.expression1 = number1;
        this.expression2 = number2;
        this.operator = operator;
    }

    /**
     * Construct new expression by expression and variable.
     *
     * @param exp      expression
     * @param variable expression2
     * @param operator an operator
     */
    public BaseExpression(Expression exp, Var variable, String operator) {
        this.expression1 = exp;
        this.expression2 = variable;
        this.operator = operator;
    }

    /**
     * Construct new expression by variable and expression.
     *
     * @param variable a variable
     * @param exp      an expression
     * @param operator an operator
     */
    public BaseExpression(Var variable, Expression exp, String operator) {
        this.expression1 = variable;
        this.expression2 = exp;
        this.operator = operator;
    }

    /**
     * Construct new expression by two variables.
     *
     * @param variable1 a variable
     * @param variable2 a variable
     * @param operator  an operator
     */
    public BaseExpression(Var variable1, Var variable2, String operator) {
        this.expression1 = variable1;
        this.expression2 = variable2;
        this.operator = operator;
    }

    /**
     * Construct new expression by variable and number.
     *
     * @param variable a variable
     * @param number   a number
     * @param operator an operator
     */
    public BaseExpression(Var variable, Num number, String operator) {
        this.expression1 = variable;
        this.expression2 = number;
        this.operator = operator;
    }

    /**
     * Construct new expression by number and variable.
     *
     * @param number  a number
     * @param variable a variable
     * @param operator an operator
     */
    public BaseExpression(Num number, Var variable, String operator) {
        this.expression1 = number;
        this.expression2 = variable;
        this.operator = operator;
    }

    /**
     * return the right expression.
     *
     * @return oneExp - right expression.
     */
    protected Expression getExpression1() {
        return this.expression1;
    }

    /**
     * return the left expression.
     *
     * @return secExp - left expression.
     */
    protected Expression getExpression2() {
        return this.expression2;
    }

    /**
     * return the operator.
     *
     * @return operator - the operator of the expression.
     */
    protected String getOperator() {
        return this.operator;
    }


    /**
     * Returns a nice string representation of the expression.
     *
     * @return a nice string representation of the expression
     */
    public String toString() {
        // for BinaryExpression
        if (this.getOperator().equals("+")) {
            return "(" + this.getExpression1().toString() + " + "
                    + this.getExpression2().toString() + ")";
        } else if (this.getOperator().equals("-")) {
            return "(" + this.getExpression1().toString() + " - "
                    + this.getExpression2().toString() + ")";
        } else if (this.getOperator().equals("*")) {
            return "(" + this.getExpression1().toString() + " * "
                    + this.getExpression2().toString() + ")";
        } else if (this.getOperator().equals("/")) {
            return "(" + this.getExpression1().toString() + " / "
                    + this.getExpression2().toString() + ")";
        } else if (this.getOperator().equals("^")) {
            return "(" + this.getExpression1().toString() + "^"
                    + this.getExpression2().toString() + ")";
        } else if (this.getOperator().equalsIgnoreCase("log")) {
            return "Log(" + this.getExpression1().toString() + ", "
                    + this.getExpression2().toString() + ")";
        // for UnaryExpression
        } else if (this.getOperator().equalsIgnoreCase("neg")) {
            return "(-" + this.getExpression1().toString() + ")";
        } else if (this.getOperator().equalsIgnoreCase("sin")) {
            return "sin(" + this.getExpression1().toString() + ")";
        } else if (this.getOperator().equalsIgnoreCase("cos")) {
            return "cos(" + this.getExpression1().toString() + ")";
        }  else {
            throw new RuntimeException("Unfamiliar operator");
        }
    }
}
