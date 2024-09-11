import java.util.Arrays;

public class PolynomialTest 
{
    public static void main(String[] args) 
    {
        //Implication of polynomial using array of coefficients
        double[] coefficients = {3, 4, 5, 2};
        Polynomial poly1 = new Polynomial(coefficients);
        double[] coefficients2 = {2, 4, 1};
        Polynomial poly2 = new Polynomial(coefficients2);

        System.out.println("P(x) + Q(x) = " + poly1.add(poly2));
        System.out.println("P(x) - Q(x) = " + poly1.sub(poly2));
        System.out.println("P(x) * Q(x) = " + poly1.mul(poly2));


        double[] coefficients3 = {3, 4, 1};
        Polynomial poly3 = new Polynomial(coefficients3);
        double[] coefficients4 = {2, 1};
        Polynomial poly4 = new Polynomial(coefficients4);

        System.out.println("P(Q(x)) = " + poly3.compose(poly4));


        double[] coefficients5 = {3, 4, 1, 3, 0, 2};
        Polynomial poly5 = new Polynomial(coefficients5);
        double[] coefficients6 = {2, 1};
        Polynomial poly6 = new Polynomial(coefficients6);

        System.out.println("P(x) / Q(x) = " + poly5.div(poly6));

        double[] coefficients7 = {0, 1};
        Polynomial poly7 = new Polynomial(coefficients7);
        double[] coefficients8 = {0, 1};
        Polynomial poly8 = new Polynomial(coefficients8);
        System.out.println("P(x).findEqual(Q(x)) = " + Arrays.toString((poly7.findEqual(poly8))));
    }
}
