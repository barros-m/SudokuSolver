/**
 * Programmer : matheusbarrs13
 *
 * date : May 18, 2017
 *
 * Description : Solve any sudoku grid (EASY ones)
 *
 * Algorithm : Check cell by cell, and see what values they can handle. If there
 * is only one value left, fill that cell with that value
 *
 */

// import java.util.Scanner;
import java.util.*;
import java.io.*;

class sudoku {

   public static int grid[][];
   public static int possibleValues[][][] = new int [9][9][9];

   public static void main(String [] args) {
      // grid = new int[][] {{4,0,2, 0,0,1, 0,0,0},
      //                 {8,0,0, 2,6,9, 0,7,4},
      //                 {6,0,1, 7,0,0, 2,8,5},
      //                 {7,0,8, 5,0,2, 4,0,1},
      //                 {0,0,0, 4,0,8, 0,0,0},
      //                 {2,0,3, 6,0,7, 9,0,8},
      //                 {9,8,7, 0,0,5, 6,0,3},
      //                 {1,5,0, 3,7,6, 0,0,2},
      //                 {0,0,0, 9,0,0, 5,0,7}};

      grid = new int[][] {{0,4,0,9,6,0,7,5,0},
                        {0,7,1,5,0,8,6,0,0},
                        {0,0,9,0,0,0,3,4,8},
                        {0,0,8,0,9,0,0,3,5},
                        {0,5,6,3,0,4,1,7,0},
                        {7,3,0,0,2,0,9,0,0},
                        {6,8,7,0,0,0,2,0,0},
                        {0,0,5,2,0,6,8,9,0},
                        {0,9,3,0,7,1,0,6,0}};



      String fileName = args[0];

      try {
         Scanner txtIn = new Scanner (new File(fileName));

         for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
               grid[i][j] = txtIn.nextInt();
            }
         }

      }
      catch (FileNotFoundException ex) {

      }


      print();
      // Number of 0's in the program
      int zeros = countsZeros();
      // changes to be made
      int changes = 0;
      // populate possibleValues[][][]
      populate();

      while (changes < zeros) {
         for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
               // If we have to fill that cell...
               if (grid[i][j] == 0) {
                  for (int k = 1; k <= 9; k++) {
                     // If that number is available...
                     if (checkAvail(i, j, k)) {
                        // ... check if it is the only one
                        if (canIPenIt(i, j)) {
                           // Pen this number
                           grid[i][j] = k;
                           // Great, you did a change
                           changes++;
                        }
                     }
                  }
               }
            }
         }
      }
      System.out.println("DONE...");
      print();

   }

   /**
    * Count the amount of changes (loops) the program needs to do
    * @return [description]
    */
   public static int countsZeros () {
      int zeros = 0;
      for (int i = 0; i < 9; i++) {
         for (int j = 0; j < 9; j++) {
            if (grid[i][j] == 0) {
               zeros++;
            }
         }
      }
      return zeros;
   }

   // populate with all possible values
   public static void populate() {
      for (int i = 0; i < 9; i++) {
         for (int j = 0; j < 9; j++) {
            for(int k = 0; k < 9; k++){
               possibleValues[i][j][k] = k;
            }
         }
      }
   }
   /**
    * See if it is possible to put num in that cell
    *
    * @param int row
    * @param int col
    * @param int num candidate number
    */
   public static boolean checkAvail(int row, int col, int num) {
      // Check if num is avalable...
      for (int i = 0; i < 9; i++) {
         // ... based on the row
         if (num == grid[row][i]) {
            // If it is not available, set it to -1, and return false
            possibleValues[row][col][num-1] = -1;
            return false;
         }
         // ...based on column
         if (num == grid[i][col]) {
            // If it is not available, set it to -1, and return false
            possibleValues[row][col][num-1] = -1;
            return false;
         }
      }
      // ...based on small grid (3*3)
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++){
            if (num == grid[(row/3) * 3 + ((row + i) % 3)][(col/3) * 3 +((col + j) % 3)]) {
               possibleValues[row][col][num-1] = -1;
               return false;
            }
         }
      }
      return true;
   }

   /**
    * Check if there is only one possibility
    * @param  int row           [description]
    * @param  int col           [description]
    * @return     [description]
    */
   public static boolean canIPenIt (int row, int col) {
      int negatives = 0;
      for (int i = 0; i < 9; i++) {
         if (possibleValues[row][col][i] == -1) {
            negatives++;
         }
      }
      // Is there is only one possibility (all others would be -1)
      if (negatives == 8) {
         return true;
      }

      return false;
   }

   /**
    * Print grid
    */
   public static void print() {
      for (int i = 0; i < 9; i++) {
         for (int j = 0; j < 9; j++) {
            if (j % 3 == 0) {
               System.out.print(" ");
            }
            if (grid[i][j] == 0) {
               System.out.print('-');
            }
            else {
               System.out.print(grid[i][j]);
            }
         }
         if (i % 3 == 2) {
            System.out.println();
         }
         System.out.println();
      }
   }
}
