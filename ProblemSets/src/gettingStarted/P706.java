import java.util.Scanner;

public class P706 {
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        int s = c.nextInt();
        int n = c.nextInt();
        while(s!=0 && n!=0){
            drawNumber(s,n);
            s = c.nextInt();
            n = c.nextInt();
        }
    }

    private static void drawNumber(int s, int n) {
        int digits = (n+"").length();
        char [][][] grids = new char [digits][][];
        while(n>0){
            // initiate the grid
            char [][] grid = new char [2*s+3][s+2];

            // fill the grid with spaces
            for(int i=0; i<grid.length; i++)
                for(int j=0; j<grid[i].length; j++)
                    grid[i][j] = ' ';

            int digit = n%10;
            n /= 10;
            switch (digit){
                case 1:
                    drawTopRightLine(grid,s);
                    drawBottomRightLine(grid,s);
                    break;

                case 2:
                    drawTopLine(grid,s);
                    drawTopRightLine(grid,s);
                    drawMiddleLine(grid,s);
                    drawBottomLeftLine(grid,s);
                    drawBottomLine(grid,s);
                    break;

                case 3:
                    drawTopLine(grid,s);
                    drawTopRightLine(grid,s);
                    drawMiddleLine(grid,s);
                    drawBottomRightLine(grid,s);
                    drawBottomLine(grid,s);
                    break;

                case 4:
                    drawTopLeftLine(grid,s);
                    drawMiddleLine(grid,s);
                    drawTopRightLine(grid,s);
                    drawBottomRightLine(grid,s);
                    break;

                case 5:
                    drawTopLine(grid,s);
                    drawTopLeftLine(grid,s);
                    drawMiddleLine(grid,s);
                    drawBottomRightLine(grid,s);
                    drawBottomLine(grid,s);
                    break;

                case 6:
                    drawTopLine(grid,s);
                    drawTopLeftLine(grid,s);
                    drawMiddleLine(grid,s);
                    drawBottomRightLine(grid,s);
                    drawBottomLine(grid,s);
                    drawBottomLeftLine(grid,s);
                    break;

                case 7:
                    drawTopLine(grid,s);
                    drawTopRightLine(grid,s);
                    drawBottomRightLine(grid,s);
                    break;

                case 8:
                    drawTopLine(grid,s);
                    drawTopLeftLine(grid,s);
                    drawBottomLeftLine(grid,s);
                    drawBottomLine(grid,s);
                    drawBottomRightLine(grid,s);
                    drawTopRightLine(grid,s);
                    drawMiddleLine(grid,s);
                    break;

                case 9:
                    drawTopLine(grid,s);
                    drawTopLeftLine(grid,s);
                    drawMiddleLine(grid,s);
                    drawTopRightLine(grid,s);
                    drawBottomRightLine(grid,s);
                    drawBottomLine(grid,s);
                    break;

                case 0:
                    drawTopLine(grid,s);
                    drawTopLeftLine(grid,s);
                    drawBottomLeftLine(grid,s);
                    drawBottomLine(grid,s);
                    drawBottomRightLine(grid,s);
                    drawTopRightLine(grid,s);
                    break;
            }
            grids[--digits] = grid;
        }

        printGrids(grids);
    }

    private static void printGrids(char [][][] grids) {
        // print grids row by row
        for(int i=0; i<grids[0].length; i++){
            for(int n=0; n<grids.length; n++) {
                for (int j = 0; j < grids[n][i].length; j++)
                    System.out.print(grids[n][i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }


    private static void drawMiddleLine(char [][] grid, int s){
        for (int i=1; i<s+1; i++)
            grid[s+1][i] = '-';
    }
    private static void drawBottomLine(char [][] grid, int s){
        for (int i=1; i<s+1; i++)
            grid[2*s+2][i] = '-';
    }
    private static void drawTopLeftLine(char [][] grid, int s){
        for (int i=1; i<s+1; i++)
            grid[i][0] = '|';

    }
    private static void drawBottomLeftLine(char [][] grid, int s){
        for (int i=1; i<s+1; i++)
            grid[i+s+1][0] = '|';

    }
    private static void drawTopRightLine(char [][] grid, int s){
        for (int i=1; i<s+1; i++)
            grid[i][s+1] = '|';

    }
    private static void drawBottomRightLine(char [][] grid, int s){
        for (int i=1; i<s+1; i++)
            grid[i+s+1][s+1] = '|';
    }
    private static void drawTopLine(char [][] grid, int s){
        for (int i=1; i<s+1; i++)
            grid[0][i] = '-';
    }
}


//        /*
//        The objective of this function is to draw a template to display numbers. Assume s is 3, then the template would
//        look like:
//         ---
//        |   |
//        |   |
//        |   |
//         ---
//        |   |
//        |   |
//        |   |
//         ---
//         */
//    private static char [][] makeTemplate(int s){
//
//        // initiate the grid
//        char [][] grid = new char [2*s+3][s+2];
//
//        // fill the grid with spaces
//        for(int i=0; i<grid.length; i++)
//            for(int j=0; j<grid[i].length; j++)
//                grid[i][j] = ' ';
//
//        // create the left line
//        for (int i=1; i<s+1; i++){
//            grid[i][0] = '|';
//            grid[i+s+1][0] = '|';
//        }
//
//        // create the right line
//        for (int i=1; i<s+1; i++){
//            grid[i][s+1] = '|';
//            grid[i+s+1][s+1] = '|';
//        }
//
//        // create the top line
//        for (int i=1; i<s+1; i++)
//            grid[0][i] = '-';
//
//        // create the middle line
//        for (int i=1; i<s+1; i++)
//            grid[s+1][i] = '-';
//
//        // create the bottom line
//        for (int i=1; i<s+1; i++)
//            grid[2*s+2][i] = '-';
//
//        return grid;
//    }
