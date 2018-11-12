I executed simplification of complex Pow-
in case the expression pow is compare from two comlex expressions (devided by two cases):

1. new Pow(new Pow(exp, exp), new exp)
2. new Pow(new exp, new Pow(exp,exp)

I deal with it by checking if one of the expressions of the initial Pow is also a Pow by using "instanceof".

as you can see, this is how i wrote it in my program:

        // complex expression - exp1 is also pow type
        if (exp1 instanceof Pow) {
            newExp = new Mult(((BaseExpression) (exp1)).getExpression2(), exp2);
            exp1 = ((BaseExpression) exp1).getExpression1();
            exp1 = exp1.simplify();
            exp2 = newExp.simplify();
        }
        // complex expression - exp2 is also pow type
        if (exp2 instanceof Pow) {
            newExp = new Mult(((BaseExpression) (exp2)).getExpression1(),
                    ((BaseExpression) (exp2)).getExpression2());
            exp2 = newExp;
            exp1 = exp1.simplify();
            exp2 = exp2.simplify();
        }