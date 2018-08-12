import java.util.Scanner;

public class P10196 {
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        char[][] chessboard = new char[8][8];
        int counter = 0;
        boolean empty = false;
        while (!empty) {
            empty = true;
            counter++;
            // read chessboard from the input
            String line = c.nextLine();
            for (int i = 0; i < 8; i++) {
                chessboard[i] = line.toCharArray();
                if (empty)
                    for (int j = 0; j < chessboard[i].length; j++)
                        if (chessboard[i][j] != '.')
                            empty = false;
                line = c.nextLine();
            }
            if(counter==1)// Just to divide output from input
                System.out.println();
            // terminate the loop if empty chessboard found.
            if (empty)
                continue;

            boolean[] checkMate = new boolean[]{false, false};

            // check item's positions:
            for (int i = 0; i < chessboard.length && !checkMate[0]; i++) {
                for (int j = 0; j < chessboard[i].length && !checkMate[0]; j++) {
                    switch (chessboard[i][j]) {
                        case 'p':
                            checkMate = checkPawn(chessboard, i, j, false);
                            break;

                        case 'P':
                            checkMate = checkPawn(chessboard, i, j, true);
                            break;

                        case 'n':
                            checkMate = checkKnight(chessboard, i, j, false);
                            break;

                        case 'N':
                            checkMate = checkKnight(chessboard, i, j, true);
                            break;

                        case 'b':
                            checkMate = checkBishop(chessboard, i, j, false);
                            break;

                        case 'B':
                            checkMate = checkBishop(chessboard, i, j, true);
                            break;

                        case 'r':
                            checkMate = checkRock(chessboard, i, j, false);
                            break;

                        case 'R':
                            checkMate = checkRock(chessboard, i, j, true);
                            break;

                        case 'q':
                            // bishop-like movement
                            checkMate = checkBishop(chessboard, i, j, false);
                            // if still there is no check, do rock-like movement
                            if (!checkMate[0])
                                checkMate = checkBishop(chessboard, i, j, false);
                            break;

                        case 'Q':
                            // bishop-like movement
                            checkMate = checkBishop(chessboard, i, j, true);
                            // if still there is no check, do rock-like movement
                            if (!checkMate[0])
                                checkMate = checkBishop(chessboard, i, j, true);
                            break;
                        case 'k':
                            // Nothing significant here!
                            break;
                        case 'K':
                            // Nothing significant here!
                            break;
                    }
                }
            }
            System.out.println(checkMate[0] ? ("Game #" + counter + ": " + (!checkMate[1] ? "white " : "black ") + "king is in check.")
                    : ("Game #" + counter + ": " + "no king is in check."));


        }
    }


    private static boolean[] checkPawn(char[][] chessboard, int i, int j, boolean color) {
        boolean checkMate = false;
        int c = color ? -1 : 1;
        // if the pawn is white
        if(color){
            if(--i>-1)
                if(j+1<8 && chessboard[i][j+1] == 'k')
                    checkMate = true;
                else if (j-1>-1 && chessboard[i][j-1] == 'k')
                    checkMate = true;

        }
        // else if the pawn if black
        else{
            if(++i<8)
                if(j+1<8 && chessboard[i][j+1] == 'K')
                    checkMate = true;
                else if (j-1>-1 && chessboard[i][j-1] == 'K')
                    checkMate = true;
        }

        return new boolean[]{checkMate, color};
    }


    private static boolean[] checkBishop(char[][] chessboard, int i, int j, boolean color) {
        int m, n;
        boolean checkMate = false;
        char c = color ? 'k' : 'K';
        m = i;
        n = j;
        while (--m > -1 && ++n < 8 && !checkMate)
            if (chessboard[m][n] == c)
                checkMate = true;
            else if (chessboard[m][n] != '.')
                break;
        m = i;
        n = j;
        while (++m < 8 && --n > -1 && !checkMate)
            if (chessboard[m][n] == c)
                checkMate = true;
            else if (chessboard[m][n] != '.')
                break;
        m = i;
        n = j;
        while (--m > -1 && --n > -1 && !checkMate)
            if (chessboard[m][n] == c)
                checkMate = true;
            else if (chessboard[m][n] != '.')
                break;
        m = i;
        n = j;
        while (++m < 8 && ++n < 8 && !checkMate)
            if (chessboard[m][n] == c)
                checkMate = true;

            else if (chessboard[m][n] != '.') {
                break;

            }
        return new boolean[]{checkMate, color};
    }


    private static boolean[] checkRock(char[][] chessboard, int i, int j, boolean color) {
        int m, n;
        boolean checkMate = false;
        char c = color ? 'k' : 'K';

        m = i;
        n = j;
        while (--m > -1 && !checkMate)
            if (chessboard[m][n] == c)
                checkMate = true;
            else if (chessboard[m][n] != '.')
                break;
        m = i;
        n = j;
        while (++m < 8 && !checkMate)
            if (chessboard[m][n] == c)
                checkMate = true;
            else if (chessboard[m][n] != '.')
                break;
        m = i;
        n = j;
        while (--n > -1 && !checkMate)
            if (chessboard[m][n] == c)
                checkMate = true;
            else if (chessboard[m][n] != '.')
                break;
        m = i;
        n = j;
        while (++m < 8 && !checkMate)
            if (chessboard[m][n] == c)
                checkMate = true;
            else if (chessboard[m][n] != '.')
                break;

        return new boolean[]{checkMate, color};
    }

    private static boolean[] checkKnight(char[][] chessboard, int i, int j, boolean color) {
        boolean checkMate = false;
        char c = color ? 'k' : 'K';

         /*
         first move:
         --
           |
           |
           |
           n
          */
        if (i - 2 > -1 && j - 1 > -1 && chessboard[i - 2][j - 1] == c)
            checkMate = true;

         /*
         second move:
            --
           |
           |
           |
           n
          */
        else if (i - 2 > -1 && j + 1 < 8 && chessboard[i - 2][j + 1] == c)
            checkMate = true;
        /*
         third move:
           n
           |
           |
           |
            --
          */
        else if (i + 2 < 8 && j + 1 < 8 && chessboard[i + 2][j + 1] == c)
            checkMate = true;
         /*
         fourth move:
           n
           |
           |
           |
         --
          */
        else if (i + 2 < 8 && j - 1 > -1 && chessboard[i + 2][j - 1] == c)
            checkMate = true;
         /*
         fifth move:
         n-------
                 |
          */
        else if (i + 1 < 8 && j + 2 < 8 && chessboard[i + 1][j + 2] == c)
            checkMate = true;
         /*
         sixth move:
                |
         n -----
          */
        else if (i - 1 > -1 && j + 2 < 8 && chessboard[i - 1][j + 2] == c)
            checkMate = true;
         /*
         seventh move:
           |
            -----n
          */
        else if (i - 1 > -1 && j - 2 > -1 && chessboard[i - 1][j - 2] == c)
            checkMate = true;
         /*
         eighth move:
           -----n
          |
          */
        else if (i + 1 < 8 && j - 2 > -1 && chessboard[i + 1][j - 2] == c)
            checkMate = true;

        return new boolean[]{checkMate, color};
    }

}
