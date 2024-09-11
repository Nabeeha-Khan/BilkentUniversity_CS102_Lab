import java.util.Arrays;

public class Student
{
    //Instance Variables
    private String name, surname, schoolID;
    private int age;
    private Grade[] grades;

    //Constructor method
    public Student(String name, String surname, String schoolID, int age)
    {
        this.name = name;
        this.surname = surname;
        this.schoolID = schoolID;
        this.age = age;
        grades = new Grade[0];
    }

    //Default constructor method
    public Student()
    {
    }

    //Method to display information of student
    public String toString()
    {
        return schoolID + ", " + name + " " + surname + ", " + age;
    }

    //Sets a new grade under a student
    public void setGrade(String examName, float weight, float points) throws Exception
    {
        
        //Checks if there is an exam name already available with the name
        for(int i = 0; i < grades.length; i++)
        {
            if(grades[i].getExamName().equals(examName))
            {
                grades[i].setPoints(points);
                grades[i].setWeight(weight);
                return;
            }
        }

        //Implementation for when exam name is unique
        {
            //Throws exception if length is at least 3
            if(examName.length() <= 3)
            {
                throw new Exception(examName + " must be longer than 3 characters!");
            }
            //Creates object of grade and add in array
            else
            {
                Grade newGrade = new Grade(examName, weight, points);
                grades = Arrays.copyOf(grades, grades.length + 1);
                grades[grades.length - 1] = newGrade;
            }
        }
    }

    //Getter method for name
    public String getName()
    {
        return name;
    }
    
    //Getter method for surname
    public String getSurname()
    {
        return surname;
    }

    //Getter method for age
    public int getAge()
    {
        return age;
    }

    //Getter method for school ID
    public String getSchoolID()
    {
        return schoolID;
    }

    //Getter method for grades array
    public Grade[] getGrade()
    {
        return grades;
    }
}