import java.util.Scanner;

public class Grazing {
   public static final void main(final String[] args) {
      Scanner in = new Scanner(System.in);
	  
	  int k = in.nextInt(); // taking in the input for k and adding one to make a 5x5
	  k = k + 1;
	  int val = 0;
	  char[][] grid = new char[k][k]; // the array that will hold the 5x5 grid
	  
	  for(int rw = 0; rw < k; rw++) { // making the grid filled with dots
	    for(int cl = 0; cl < k; cl++) { // as space holders 
		   grid[rw][cl] = '.';
	    }
      }
	  
	  while(in.hasNextInt()) { // marks the non grassy areas in the grid
	     int row = in.nextInt();
		 int col = in.nextInt();
		 row = row - 1;
		 col = col - 1;
	     grid[row][col]  = 'x';
	  }
	  
	  for(int rw = 0; rw < k; rw++) { // prints the grid
	    System.out.println();
	    for(int cl = 0; cl < k; cl++) {
		   System.out.print(grid[rw][cl]);
	    }
      }  
	  
	  mPath(grid, k, k-1, k, 0, 0, 0);
	  
	  for(int rw = 0; rw < k; rw++) { // prints the grid
	    System.out.println();
	    for(int cl = 0; cl < k; cl++) {
		   System.out.print(grid[rw][cl]);
	    }
      }  
	  
	  System.out.println(val);
	  
   }
   
   public static boolean check(char[][] grid, int k) { // checks if the grid has any dots in it still
      for (int i = 0; i < k; i++){
         for (int j = 0; j < k; j++){
            if (grid[i][j] == '.'){
               return false;
            }
         }
      }
      return true;
   }
   
   public static int mPCheck(final char[][] grid, int mx, int my, int hx, int hy, int val, int k) {
	  if (mx != hx && hy != my){
         if (check(grid, k) == true) {
            val = val+1;
            mPath(grid, k, k-1, k-1, val, val, val);
            mPath(grid, k, k, k-1, 0, val, val);
            mPath(grid, k, k-1, k, val, 0, val);
			
		    hPath(grid, k, k-1, k-1, val, val, val);
            hPath(grid, k, k, k-1, 0, val, val);
            hPath(grid, k, k-1, k, val, 0, val);
         } else {
            mPath(grid, k, k-1, k-1, val, val, val);
            mPath(grid, k, k, k-1, 0, val, val);
            mPath(grid, k, k-1, k, val, 0, val);
			
		    hPath(grid, k, k-1, k-1, val, val, val);
            hPath(grid, k, k, k-1, 0, val, val);
            hPath(grid, k, k-1, k, val, 0, val);
			
            return val;
         }
      }
   }}
   
   public static int mPath(final char[][] grid, int k, int mx, int my, int hx, int hy, int val) {
      if (mx != hx && hy != my){ // checks if h and m are at the mid point yet
         if (mx < k && my < k){ // checks if m is still within the bounds of the grid
            if (grid[mx][my] == '.'){
			   grid[mx][my] = 'm';
			   if (grid[hx+1][hy] == '.'){
                  hPath(grid, k, mx, my, hx+1, hy, val);
               } else if (grid[hx][hy+1] == '.') {
                  hPath(grid, k, mx, my, hx, hy+1, val);
               } else if (grid[hx][hy-1] == '.') {
                  hPath(grid, k, mx, my, hx, hy-1, val);
               } else if (grid[hx-1][hy] == '.') {
                  hPath(grid, k, mx, my, hx-1, hy, val);
               }
			}
         }
      } else {
		  mPCheck(grid, mx, my, hx, hy, val, k);
	  }
   }}
   
   public static int hPath(final char[][] grid, int k, int mx, int my, int hx, int hy, int val) {
      if (mx != hx && hy != my){ // checks if h and m are at the mid point yet
         if (hx < k && hy < k){ // checks if m is still within the bounds of the grid
            if (grid[hx][hy] == '.'){
               grid[hx][hy] = 'h';
			   
               if (grid[mx-1][my] == '.'){
                  mPath(grid, k, mx-1, my, hx, hy, val);
               } else if (grid[mx][my-1] == '.') {
                  mPath(grid, k, mx, my-1, hx, hy, val);
               } else if (grid[mx+1][my] == '.') {
                  mPath(grid, k, mx +1, my, hx, hy, val);
               } else if (grid[mx][my+1] == '.') {
                  mPath(grid, k, mx, my + 1, hx, hy, val);
               }
            }
         }
      } else {
		  mPCheck(grid, mx, my, hx, hy, val, k);
	  }
   }}
}
      