public class Polynomial 
{
    private double[] coefficients;

    //Constructor for polynomial with given degree and coefficient
    public Polynomial(int d, double c)
    {
        this.coefficients = new double[d + 1];
        this.coefficients[d] = c;
    }

    //Constructor for default polynomial 
    public Polynomial()
    {
        this.coefficients = new double[1];
        this.coefficients[0] = 0;
    }

    //Constructor for polynomial with given array of coefficients
    public Polynomial(double[] coefficients)
    {
        this.coefficients = coefficients;
    }

    //Getter method for returning coefficient of polynomial at given degree
    public double getCoefficient(int degree)
    {
        if(degree >= 0 && degree < this.coefficients.length)
            return this.coefficients[degree];

        return 0;
    }

    //Getter method for returning degree of polynomial
    public int getDegree()
    {
        for(int i = this.coefficients.length - 1; i >= 0; i--)
        {
            if(this.coefficients[i] != 0)
            {
                return i;
            }
        }
        return 0;
    }

    //Getter method for returning the string form of polynomial
    @Override
    public String toString()
    {
        String polynomial = "";

        for(int i = 0; i < this.coefficients.length; i++)
        {
            if(i == 0 && this.coefficients.length == 1)
            {
                polynomial += this.coefficients[i] + " ";
            }
            else if(i == 0 && this.coefficients[i] != 0)
            {
                polynomial += this.coefficients[i] + " ";
            }
            else
            {
                if(this.coefficients[i] != 0)
                {
                    if(this.coefficients[i] > 0)
                      polynomial += "+ ";
                 
                    polynomial += this.coefficients[i] + "x^" + i + " ";
                }
            }
        }

        return polynomial;
    }

    //Returns values of polynomial at some value of x by substititon method
    public double eval(double x)
    {
        double sum = 0;

        for(int i = 0; i < this.coefficients.length; i++)
        {
            sum += (this.coefficients[i] * Math.pow(x, i));
        }

        return sum;
    }

    //Returns values of polynomial at some value of x by Horner's method
    public double eval2(double x)
    {
        double sum = this.coefficients[coefficients.length - 1];

        for(int i = this.coefficients.length - 2; i >= 0; i--)
        {
            sum = sum * x + this.coefficients[i];
        }

        return sum;
    }

    //This method performs addition of two polynomials
    public Polynomial add(Polynomial polynomial2)
    {
        Polynomial largestPolynomial, smallestPolynomial;

        if(this.getDegree() >= polynomial2.getDegree())
        {
            largestPolynomial = new Polynomial(this.coefficients);
            smallestPolynomial = polynomial2;
        }
        else
        {
            largestPolynomial = polynomial2;

            smallestPolynomial = new Polynomial(this.coefficients);
        }

        double[] coefficientOfSum = new double[largestPolynomial.getDegree() + 1];

        for(int i = 0; i <= largestPolynomial.getDegree(); i++)
        { 
            if(i <= smallestPolynomial.getDegree())
                coefficientOfSum[i] = largestPolynomial.getCoefficient(i) + smallestPolynomial.getCoefficient(i);
            else
                coefficientOfSum[i] = largestPolynomial.getCoefficient(i);
        }
        
        Polynomial sum = new Polynomial(coefficientOfSum);

        return sum;
    }

    //This method performs subtraction of two polynomials
    public Polynomial sub(Polynomial polynomial2)
    {
        double[] minusOne = {-1};
        Polynomial negative = new Polynomial(minusOne);
        Polynomial negativePoly2 = polynomial2.mul(negative);

        return this.add(negativePoly2);
    }

    //This method performs multiplication of two polynomials
    public Polynomial mul(Polynomial polynomial2)
    {
        double[] coefficientOfMul = new double[this.getDegree() + polynomial2.getDegree() + 1];

        for(int i = 0; i <= this.getDegree(); i++)
        {
            for(int j = 0; j <= polynomial2.getDegree(); j++)
            {
                coefficientOfMul[i + j] += this.coefficients[i] * polynomial2.getCoefficient(j);
            }
        }

        Polynomial product = new Polynomial(coefficientOfMul);

        return product;
    }
    
    //This method performs composition of current polynomial with given polynomial
    public Polynomial compose(Polynomial polynomial2)
    {
        double[] coefficientOfCompose = new double[this.getDegree() * polynomial2.getDegree() + 1];
        coefficientOfCompose[0] = this.coefficients[0];

        Polynomial compose = new Polynomial(coefficientOfCompose);

        for(int i = 1; i < coefficientOfCompose.length; i++)
        {
            Polynomial powerOfpoly2 = polynomial2;

            for(int j = 2; j <= i; j++)
            {
                powerOfpoly2 = powerOfpoly2.mul(polynomial2);
            }

            double[] coefficient = {this.getCoefficient(i)};
            Polynomial coefficientI = new Polynomial(coefficient);

            compose = compose.add(powerOfpoly2.mul(coefficientI));
        }

        return compose;
    }

    //This method performs divison of two polynomials
    public Polynomial div(Polynomial polynomial2)
    {
        Polynomial P = new Polynomial(this.coefficients);
        Polynomial Q = polynomial2;
        Polynomial result = new Polynomial();

        while(P.getDegree() >= Q.getDegree())
        {
            int degreeP = P.getDegree();
            int degreeQ = Q.getDegree();
            double coefficientP = P.getCoefficient(degreeP);
            double coefficientQ = Q.getCoefficient(degreeQ);

            Polynomial T = new Polynomial(degreeP - degreeQ, coefficientP / coefficientQ);

            P = P.sub(Q.mul(T));

            result = result.add(T);
        }

        return result;
    }

    //This medthod returns array of numbers from 1 to 200 that return same value for both polynomials 
    public int[] findEqual(Polynomial polynomial2)
    {
        int[] result = new int[200];
        int currentSize = 0;

        for(int i = 1; i <= 200; i++)
        {
            if(this.eval(i) == polynomial2.eval(i))
            {
                result[currentSize] = i;
                currentSize++;
            }
        }

        if(currentSize == 200)
            return result;

        else if(currentSize == 0)
            return new int[] {0};

        else 
        {
            int[] modResult = new int[currentSize];

            for(int i = 0; i < currentSize; i++)
                modResult[i] = result[i];

            return modResult;
        }
    }
}
