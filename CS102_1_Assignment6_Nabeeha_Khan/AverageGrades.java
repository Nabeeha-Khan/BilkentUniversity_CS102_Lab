public class AverageGrades 
{
    //Instance variables
    Student student;
    float averageGrades;

    //Constructor method
    public AverageGrades(Student student, float averageGrades)
    {
        this.student = student;
        this.averageGrades = averageGrades;
    }

    //Getter method for grades
    public float getGrades()
    {
        return averageGrades;
    }

    //Getter method for student
    public Student getStudent()
    {
        return student;
    }

    //Returns string with student information and average grades
    public String toString()
    {
        return student.toString() + " - Average: " + averageGrades;
    }
}
