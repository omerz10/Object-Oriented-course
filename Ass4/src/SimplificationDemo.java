/**
 * SimplificationDemo.
 *
 * Omer Zucker
 * 200876548
 */
public class SimplificationDemo {


    /**
     * main.
     *
     * @param args args
     * @throws Exception exception
     */
    public static void main(String[] args) throws Exception {
        // first expression of Pow is also Pow
        Expression e1 = new Pow(new Pow(new Var("x"), 3), new Var("x"));
        System.out.println(e1);
        System.out.println(e1.simplify());

        // // second expression of Pow is also Pow
        Expression e2 = new Pow(new Var("x"), new Pow(new Var("x"), 3));
        System.out.println(e2);
        System.out.println(e2.simplify());
    }
}
