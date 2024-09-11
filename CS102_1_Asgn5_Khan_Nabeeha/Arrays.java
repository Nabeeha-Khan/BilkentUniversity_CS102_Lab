public class Arrays
{
    private char[][] array;

    //Initializes array to given dimension 
    public void createArray(int rows, int columns)
    {
        array = new char[rows][columns];
    }

    //Fills array with the given character in the given range of rows and columns
    public void fillChar(char c, int rowStart, int rowEnd, int columnStart, int columnEnd) 
    {
        if (rowStart < rowEnd) 
        {
            fillCharHelper(c, rowStart, columnStart, columnEnd);
            fillChar(c, ++rowStart, rowEnd, columnStart, columnEnd);
        }
    }

    //Helper method to fill each column of given row
    private void fillCharHelper(char c, int row, int columnStart, int columnEnd) 
    {
        if (columnStart < columnEnd) 
        {
            array[row][columnStart] = c;
            fillCharHelper(c, row, columnStart + 1, columnEnd);
        } 
    }

    //Prints values in array
    public void print()
    {
        printHelper1(0);   
    }

    //Helper method to print each row
    private void printHelper1(int row)
    {
        if(row < array.length)
        {
            printHelper2(row, 0);
            System.out.println();
            //Resursively calls itself for next row
            printHelper1(++row);
        }
    }

    //Helper method to print each column of given row
    private void printHelper2(int row, int column)
    {
        if(column < array[row].length)
        {
            System.out.print(array[row][column]);
            //Resursively call itself for next column
            printHelper2(row, ++column);
        }
    }

    //Creates pattern of alternating rectangular boundaries filled with chars # and *
    public void pattern1()
    {
        pattern1Helper(0, array.length, 0, array[0].length);
    }

    //Helper method to create pattern
    private void pattern1Helper(int rowStart, int rowEnd, int colStart, int colEnd) 
    {
        if (rowStart <= rowEnd && colStart <= colEnd) 
        {
            // Fill the outer rectangular boundary with '*'
            fillChar('*', rowStart, rowEnd, colStart, colEnd);

            // Fill the inner rectangular boundary with '#'
            fillChar('#', rowStart + 1, rowEnd - 1, colStart + 1, colEnd - 1);

            // Recur for the smaller rectangle
            pattern1Helper(rowStart + 2, rowEnd - 2, colStart + 2, colEnd - 2);
        }

    }  
    
    //Creates pattern by shifting * and filling remaining with #
    public void pattern2(int fillWidth, int shiftAmount) 
    {
        // Fill the entire array with '#'
        fillChar('#', 0, array.length, 0, array[0].length);
    
        // Fill each row with '*' characters
        fillRowWithStars(0, fillWidth, shiftAmount, 0);
    }
    
    // Helper method to fill a row with '*' characters
    private void fillRowWithStars(int rowIndex, int fillWidth, int shiftAmount, int columnIndex) 
    {
        // Base case: if rowIndex exceeds the array size, stop recursion
        if (rowIndex >= array.length)
            return;
    
        // Fill '*' characters in the current row
        fillRowHelper(rowIndex, fillWidth, shiftAmount, columnIndex);
    
        // Recursive call for the next row
        fillRowWithStars(rowIndex + 1, fillWidth, shiftAmount, (columnIndex + shiftAmount) % array[rowIndex].length);
    }
    
    // Helper method to fill '*' characters in a row
    private void fillRowHelper(int rowIndex, int fillWidth, int shiftAmount, int columnIndex) 
    {
        int rowLength = array[rowIndex].length;
    
        // Base case: if fillWidth is 0 or the row is filled, stop recursion
        if (fillWidth <= 0 || columnIndex >= rowLength)
            return;
    
        // Fill '*' character at the current index
        array[rowIndex][columnIndex] = '*';
    
        // Recursive call for the next character in the row
        fillRowHelper(rowIndex, fillWidth - 1, shiftAmount, (columnIndex + 1) % rowLength);
    }

    //Creates pattern of integers 
    public void pattern3() 
    {
        // Start filling the pattern from the top-left corner
        fillPattern(0, 0); 
    }

    private void fillPattern(int row, int col) 
    {
        // Base case: if out of bounds, return
        if (row >= array.length || col >= array[0].length)
            return;

        // Fill the first row and first column with '1'
        if (row == 0 || col == 0)
            array[row][col] = '1';

        // Calculate the current element using the specified formula
        if (row > 0 && col > 0) 
        {
            int upNeighbor = Integer.parseInt(String.valueOf(array[row - 1][col]));
            int leftNeighbor = Integer.parseInt(String.valueOf(array[row][col - 1]));
            array[row][col] = (char) ((upNeighbor + leftNeighbor) % 10 + '0');  //Adding '0' so that we get ascii number of character represntation of that integer
        }

        // Move to the next cell (right, then down)
        if (col + 1 < array[0].length)
            fillPattern(row, col + 1);
        else if (row + 1 < array.length)
            fillPattern(row + 1, 0);
    }

    //Returns the maximum sum of row 
    public int findMaxRowSum()
    {
        // Start finding the maximum row sum from the first row
        return findMaxRowSumHelper(0, 0);
    }

    //Helper method to find the maximum sum
    private int findMaxRowSumHelper(int row, int maxSum)
    {
        // Base case: If the current row index is beyond the array bounds, return the maximum sum found so far
        if(row >= array.length)
            return maxSum;

        // Calculate the sum of the current row
        int sum = findRowSum(row);
        // Update the maximum sum if the sum of the current row is greater
        if(sum > maxSum)
            maxSum = sum;

        // Recursively call itself for the next row    
        return findMaxRowSumHelper(++row, maxSum);
    }

    //Helper method to find sum of each row
    private int findRowSum(int row)
    {
        // Start calculating the sum of the row using a helper method
        return findRowSumHelper(row, 0, 0);
    }

    //Helper method that returns sum of a row using recursion
    private int findRowSumHelper(int row, int column, int sum)
    {
        // Base case: If the current column index is beyond the array bounds, return the sum of the row
        if(column >= array[row].length)
            return sum;
        // Convert the character value to an integer and add it to the sum
        sum += Integer.parseInt("" + array[row][column]);

        //Recursively call itself for next column
        return findRowSumHelper(row, ++column, sum);    
    }
}