public class Test 
{ 
    public static void main(String[] args) 
    {
        //Simple filling of arrays
        Arrays a = new Arrays();
        a.createArray(3, 3);
        a.fillChar('1', 0, 3, 0, 3);
        a.print();

        System.out.println();

        //Printing pattern 1
        Arrays patternA = new Arrays();
        patternA.createArray(10, 10);
        patternA.pattern1();
        patternA.print();

        System.out.println();

        //Printing pattern 2
        Arrays patternB = new Arrays();
        patternB.createArray(7, 13);
        patternB.pattern2(5, 4);
        patternB.print();

        System.out.println();

        //Printing pattern 3
        Arrays patternC = new Arrays();
        patternC.createArray(5, 5);
        patternC.pattern3();
        patternC.print();
        System.out.println("Maximum sum of row: " + patternC.findMaxRowSum());
    }

}
