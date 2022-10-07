import java.util.Scanner;
import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int k = in.nextInt();
    k = k + 1;

    char[][] grid = new char[k][k];
   
    for (int i = 0; i < k; i++){
      for (int j = 0; j < k; j++){
        grid[i][j] = '.';
      }
    }

    while(in.hasNextInt()) {
      int row = in.nextInt();
      int col = in.nextInt();

      row = row - 1;
      col = col - 1;

      grid[row][col] = 'x';
    }

   
   
    System.out.println("The output is " + path(grid, k, k-1, k, 0, 0, 0, 0));
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

  public static void graph(char[][] grid, int k){ // prints out the grid
    for (int i = 0; i < k; i++){
      for (int j = 0; j < k; j++){
        System.out.print(grid[i][j]);
      }
      System.out.println();
    }
  }

  public static int path(final char[][] grid, int k, int mx, int my, int hx, int hy, int count, int val){
    if (mx != hx && hy != my){ // checks if h and m are at the mid point yet
      if ((count % 2) == 1){ // count is here to flip between h and m moving
        if (mx < k && my < k){ // checks if m is still within the bounds of the grid
          if (grid[mx][my] == '.'){ // checks if the spot is open
            grid[mx][my] = 'm';
            graph(grid, k);
			
			// below checks south north west and east spots
			
            if (grid[mx-1][my] == '.'){
              path(grid, k, mx-1, my, hx, hy, count+1, val);
            } else if (grid[mx][my-1] == '.') {
              path(grid, k, mx, my-1, hx, hy, count+1, val);
            } else if (grid[mx+1][my] == '.') {
              path(grid, k, mx +1, my, hx, hy, count+1, val);
            } else if (grid[mx][my+1] == '.') {
              path(grid, k, mx, my + 1, hx, hy, count+1, val);
            }
			
          }
        }
      } else {
        if (hx < k && hy < k){  // checks if h is within bounds
          if (count == 0) { // special case for first iteration
            if (grid[hx][hy] == '.'){
              grid[hx][hy] = 'h';
              graph(grid,k);
              path(grid, k, mx, my, hx, hy, count+1, val);
            }
          } else {
            if (grid[hx][hy] == '.'){
              grid[hx][hy] = 'h';
              graph(grid,k);
              if (grid[hx+1][hy] == '.'){
                 path(grid, k, mx, my, hx+1, hy, count+1, val);
              } else if (grid[hx][hy+1] == '.') {
                 path(grid, k, mx, my, hx, hy+1, count+1, val);
              } else if (grid[hx][hy-1] == '.') {
                 path(grid, k, mx, my, hx, hy-1, count+1, val);
              } else if (grid[hx-1][hy] == '.') {
                 path(grid, k, mx, my, hx-1, hy, count+1, val);
              }
            }
          }
        }
      }
    } else {
      if (check(grid, k) == true) {
        val = val+1;
        path(grid, k, k-1, k-1, val, val, 0, val);
        path(grid, k, k, k-1, 0, val, 0, val);
        path(grid, k, k-1, k, val, 0, 0, val);
      } else {
        path(grid, k, k-1, k-1, val, val, 0, val);
        path(grid, k, k, k-1, 0, val, 0, val);
        path(grid, k, k-1, k, val, 0, 0, val);
        return val;
      }
    }
    return 0;
  }
}