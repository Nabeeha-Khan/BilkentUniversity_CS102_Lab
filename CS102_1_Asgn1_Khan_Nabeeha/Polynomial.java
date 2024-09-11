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
}
