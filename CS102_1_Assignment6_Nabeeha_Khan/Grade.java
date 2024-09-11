public class Grade 
{
    //Instance Variables
    private String examName;
    private float weight, points;

    //Constructor method
    public Grade(String examName, float weight, float points)
    {
        this.examName = examName;
        this.weight = weight;
        this.points = points;
    }

    //Default constructor method
    public Grade()
    {
    }

    //Getter method for exam name
    public String getExamName()
    {
        return examName;
    }

    //Getter method for weight
    public float getWeight()
    {
        return weight;
    }

    //Setter method for weight
    public void setWeight(float weight)
    {
        this.weight = weight;
    }

    //Getter method for points
    public float getPoints()
    {
        return points;
    }

    //Setter method for points
    public void setPoints(float points)
    {
        this.points = points;
    }
}
