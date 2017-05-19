/**
 * Programmer : matheusbarrs13
 *
 * date : May 18, 2017
 *
 * Description : Solve any sudoku grid (easy ones)
 *
 * Algorithm : Check cell by cell, and see what values they can handle. If there
 * is only one value left, fill that cell with that value
 *
 */

// import java.util.Scanner;
import java.util.*;

class sudoku {

   public static int grid[][];
   public static int possibleValues[][][] = new int [9][9][9];

   public static void main(String [] args) {
      grid = new int[][] {{4,0,2, 0,0,1, 0,0,0},
                      {8,0,0, 2,6,9, 0,7,4},
                      {6,0,1, 7,0,0, 2,8,5},
                      {7,0,8, 5,0,2, 4,0,1},
                      {0,0,0, 4,0,8, 0,0,0},
                      {2,0,3, 6,0,7, 9,0,8},
                      {9,8,7, 0,0,5, 6,0,3},
                      {1,5,0, 3,7,6, 0,0,2},
                      {0,0,0, 9,0,0, 5,0,7}};

                      print();
      int zeros = countsZeros();
      int changes = 0;
      populate();
      while (changes < zeros) {
         for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
               // If we have to fill that cell...
               if (grid[i][j] == 0) {
                  for (int k = 1; k <= 9; k++) {
                     if (checkAvail(i, j, k)) {
                        if (canIPenIt(i, j)) {
                           grid[i][j] = k;
                           changes++;
                           //System.out.println("... TST");
                           //print();
                           //System.out.println("In row: " + i + ". Col : " + j + " it is now: " + k);
                        }
                     }
                  }
               }
            }
         }
      }
      System.out.println("DONE?");
      print();

   }

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
      for (int i = 0; i < 9; i++) {
         if (num == grid[row][i]) {
            possibleValues[row][col][num-1] = -1;
            return false;
         }
         if (num == grid[i][col]) {
            possibleValues[row][col][num-1] = -1;
            return false;
         }
      }
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

   public static boolean canIPenIt (int row, int col) {
      int negatives = 0;
      for (int i = 0; i < 9; i++) {
         if (possibleValues[row][col][i] == -1) {
            negatives++;
         }
      }

      if (negatives == 8) {
         return true;
      }

      return false;
   }

   // public static int findAvailableCase (int row, int col) {
   //    int index = 0;
   //
   //    for (int i = 0; i < 9; i++) {
   //       if (possibleValues[row][col][i] == -1) {
   //          index = i;
   //          return index;
   //       }
   //    }
   //
   //    return index;
   // }

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
