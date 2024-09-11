public class PolynomialTest 
{
    public static void main(String[] args) 
    {
        //Implication of polynomial using array of coefficients
        double[] coefficients = {-1, 0, 2, 0, -3};
        Polynomial poly1 = new Polynomial(coefficients);
        System.out.println("Polynomial 1: " + poly1.toString());
        System.out.println("Degree of polynomial 1: " + poly1.getDegree());
        System.out.println("Coefficient of x at degree 2 is " + poly1.getCoefficient(2));
        System.out.println("Evaluating x at 5, we get " + poly1.eval(5));
        System.out.println("Evaluating x at 5 using Horner's method, we get " + poly1.eval2(5));
        System.out.println();

        //Implication of polynomial of default array
        Polynomial poly2 = new Polynomial();
        System.out.println("Polynomial 2: " + poly2.toString());
        System.out.println("Degree of polynomial 2: " + poly2.getDegree());
        System.out.println("Evaluating x at 3, we get " + poly2.eval(3));
        System.out.println("Evaluating x at 3 using Horner's method, we get " + poly2.eval2(3));
        System.out.println();

        //Implication of polynomial by given value of degree and coefficient
        Polynomial poly3 = new Polynomial(2, 3);
        System.out.println("Polynomial 3: " + poly3.toString());
        System.out.println("Degree of polynomial 3: " + poly3.getDegree());
        System.out.println("Evaluating x at 2, we get " + poly3.eval(2));
        System.out.println("Evaluating x at 2, using Horner's method, we get " + poly3.eval2(2));
        System.out.println();
    }
}
