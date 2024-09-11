import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class School
{
    //Instance Variables
    private Student[] students;
    private int noOfStudents;

    //Constructor method
    public School()
    {
        this.students = new Student[10];
        this.noOfStudents = 0;
    }

     //Adds a new student into the student array
    public void addStudent(String schoolID, String name, String surname, int age) throws Exception
    {
        //Checks if school ID are unique (throws exception otherwise)
        if(noOfStudents != 0)
        {
            for(int i = 0; i < noOfStudents; i++)
            {     
                if(students[i].getSchoolID().equals(schoolID))
                {
                    throw new Exception("Duplicate ID: " + schoolID);
                }
            }
        }

        //Creates new student object
        Student student = new Student(name, surname, schoolID, age);

        //Expands the array to twice its size if current size is greater than half the size of array
        if(noOfStudents + 1 > (students.length / 2))
        {
            Student[] copystudents = Arrays.copyOf(students, 2 * students.length);
            students = copystudents;
        }

        //Simply sets the value of first student
        
        //Gets position
        int position = getPosition(student);

        //Shifts all values till that position ome step ahead
        for (int i = noOfStudents; i > position; i--)
        {
            students[i] = students[i - 1];
        }

        //Inserts student at the particular position
        students[position] = student;
        noOfStudents++;
        
    }

    //Helper method that gets position of the new student based on increasing order of school ID
    private int getPosition(Student student)
    {
        int position = noOfStudents;

        for(int i = 0; i < noOfStudents; i++)
        {
            if(student.getSchoolID().compareTo(students[i].getSchoolID()) < 0)
            {
                position = i;
            }
        }

        return position;
    }

    //Returns student object based on school ID (throws exception if not found)
    public Student getStudent(String schoolID) throws Exception
    {
        int index = binarySearch(0, noOfStudents - 1, schoolID);

        if(index != -1)
        {
            return students[index];
        }
        else
        {
            throw new Exception("No such student with the id " + schoolID + "!");
        }
    }

    //Helper method to conduct binary search
    private int binarySearch(int low, int high, String schoolID)
    {
        if (low <= high)
        {
            int mid = (low + high) / 2;
            if (students[mid].getSchoolID().equals(schoolID))
            {
                return mid;
            }
            else if (students[mid].getSchoolID().compareTo(schoolID) < 0)
            {
                return binarySearch(mid + 1, high, schoolID); 
            }
            else
            {
                return binarySearch(low, mid - 1, schoolID);
            }
        }
        else
        {
            return -1;
        }
    }

    //Returns array of students sorted by name
    public Student[] getStudentNameByOrder()
    {
        Student[] sortByName = new Student[noOfStudents];
        for(int i = 0; i < sortByName.length; i++)
            sortByName[i] = students[i];

        quickSort(sortByName, 0, sortByName.length - 1);
        return sortByName;
    }

    //Swaps students
    private void swapStudent(Student[] arr, int i, int j)
    {
        Student temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //Does partitioning in quick sort
    private int partition(Student[] arr, int low, int high)
    {
        Student pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) 
        {
            if (arr[j].getName().compareTo(pivot.getName()) < 0) 
            {
                i++;
                swapStudent(arr, i, j);
            }
            else if(arr[j].getName().equals(pivot.getName()))
            {
                if(arr[j].getSurname().compareTo(pivot.getSurname()) < 0) 
                {
                    i++;
                    swapStudent(arr, i, j);
                }
            }
        }
        swapStudent(arr, i + 1, high);
        return (i + 1);
    }

    //Helper method to perform quick sort
    private void quickSort(Student[] arr, int low, int high)
    {
        if (low < high) 
        {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }


    //Prints students by name order
    public void printStudentsByNameOrder()
    {
        Student[] studentsByName = getStudentNameByOrder();
        for(int i = 0; i < noOfStudents; i++)
        {
            System.out.println(studentsByName[i].toString());;
        }
    }

    //Prints students by school ID order
    public void printStudents()
    {
        for(int i = 0; i < noOfStudents; i++)
        {
            System.out.println(students[i].toString());;
        }
    }

    //Calculates the grade average of a student
    public float getGradeAverage(Student s)
    {
        Grade[] grades = s.getGrade();
        float sumOfPoints = 0, sumOfWeight = 0;
        for(int i = 0; i < grades.length; i++)
        {
            float points = grades[i].getPoints();
            float weight = grades[i].getWeight();

            sumOfPoints += points * weight;
            sumOfWeight += weight;
        }

        if(sumOfWeight == 0)
            return 0;
        return sumOfPoints / sumOfWeight;
    }

    //Prints students by grade average order
    public void printStudentGradeAverage()
    {
        //Creates array of students with average grades 
        AverageGrades[] studentsByGrade = new AverageGrades[noOfStudents];

        //Gets each student, calculates their grade average, create a new object and add to array
        for(int i = 0; i < noOfStudents; i++)
        {
            float averageGrade = getGradeAverage(students[i]);
            AverageGrades studentGrade = new AverageGrades(students[i], averageGrade);
            studentsByGrade[i] = studentGrade;
        }

        //Call helper method for sorting
        bubbleSort(studentsByGrade);

        //Displays students by decreasing order(skipping those with no grades)
        for(int i = 0; i < noOfStudents; i++)
        {
            if(studentsByGrade[i].getGrades() != 0)
                System.out.println(studentsByGrade[i].toString());
        }
    }

    //Helper method to implement bubble sort
    private void bubbleSort(AverageGrades[] studentsByGrades) 
    {  
        int n = noOfStudents;   
        for(int i = 0; i < n; i++)
        {  
            for(int j = 1; j < (n - i); j++)
            {  
                if(studentsByGrades[j - 1].getGrades() < studentsByGrades[j].getGrades())
                {
                    swapGrades(studentsByGrades, j - 1, j);
                }
                else if(studentsByGrades[j - 1] == studentsByGrades[j])     
                {
                    if(studentsByGrades[j - 1].getStudent().getSchoolID().compareTo(studentsByGrades[j].getStudent().getSchoolID()) < 0)
                    {
                        swapGrades(studentsByGrades, j - 1, j);
                    }
                }   
            }
        }
    } 

    //Helper method for swaping averageGrade objects
    private void swapGrades(AverageGrades[] studentsByGrades, int i, int j)
    {
        AverageGrades temp = studentsByGrades[i];
        studentsByGrades[i] = studentsByGrades[j];
        studentsByGrades[j] = temp;
    }

    //Prints grade of student using school ID 
    public void printGradesOf(String schoolID) throws Exception
    {
        //Finds student
        Student student = getStudent(schoolID);
        System.out.println("Student: " + student.toString());

        //Prints grades
        System.out.println("Grades: ");
        Grade[] grades = student.getGrade();
        for(int i = 0; i < grades.length; i++)
        {
            System.out.printf("\t%s (Weight: %.1f) %.1f \r\n", grades[i].getExamName(), grades[i].getWeight(), grades[i].getPoints());
        }
    }

//Reads text file
    public void processTextFile(String fileName) throws Exception 
    {
        //Creates file object
        File inputFile = new File(fileName);
        Scanner in = new Scanner(inputFile);
        
        //Reads line by line
        while(in.hasNextLine())
        {
            String string = in.nextLine();

            //Adds student
            if(string.contains("Student:"))
            {
                string = string.replace("Student:", "");

                String[] parts = string.split(",");
                String fullName = parts[0];
                String[] nameParts = fullName.split(" ");
                String name = nameParts[0].trim();
                String surname = nameParts[1].trim();
                int age = Integer.parseInt(parts[1].trim());
                String schoolID = parts[2].trim();

                try {
                    addStudent(schoolID, name, surname, age);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            //Sets grade of the student whose school ID is given
            else if(string.contains("Grade:"))
            {
                string = string.replace("Grade:", "");

                String[] parts = string.split(",");
                String schoolID = parts[0].trim();
                
                try {
                    Student student = getStudent(schoolID);
                    String examName = parts[1].trim();
                    float weight = Float.parseFloat(parts[2].trim());
                    float points = Float.parseFloat(parts[3].trim());
                    try {
                        student.setGrade(examName, weight, points); 
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            //Displays grades of student
            else if(string.contains("GradesOf:"))
            {
                String schoolID = string.replace("GradesOf:", "");

                try {
                    printGradesOf(schoolID.trim());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            //Displays students acc to school ID
            else if(string.contains("PrintStudents"))
            {
                printStudents();
            }
            //Displays students acc to grade average
            else if(string.contains("PrintByGradeAverages"))
            {
                printStudentGradeAverage();
            }
            //Displays students acc to name order
            else if(string.contains("PrintByNameOrder"))
            {
                printStudentsByNameOrder();
            }
            
        }

        in.close();
    }
}
