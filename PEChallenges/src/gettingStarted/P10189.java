import java.util.Scanner;

public class P10189 {
    public static void main(String [] args){
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        int m = c.nextInt();
        int counter = 0;
        while(n != 0 && m !=0){
            char [][] field = new char [n][m];
            c.nextLine();
            System.out.println();
            for(int i=0; i<n; i++){
                String line = c.nextLine();
                for(int j=0; j<m; j++)
                    field[i][j] = line.charAt(j);
            }
            char [] [] weptField = weepMines(field);
            System.out.println("Field #"+ ++counter);
            for (char[] aWeptField : weptField) {
                for (char anAWeptField : aWeptField)
                    System.out.print(anAWeptField);
                System.out.println();
            }
            n = c.nextInt();
            m = c.nextInt();
        }
    }

    private static char[][] weepMines(char[][] field) {
        char [][] weptField = new char [field.length][field[0].length];
        for (int i=0; i<field.length; i++){
            for(int j=0; j<field[i].length; j++)
                weptField[i][j] = '0';
        }
        for (int i=0; i<field.length; i++){
            for (int j=0; j<field[i].length; j++){
                if(field[i][j] == '*'){
                    if (i+1<field.length)
                        weptField[i+1][j]++;
                    if (i-1>-1)
                        weptField[i-1][j]++;
                    if(j+1 < field[i].length)
                        weptField[i][j+1]++;
                    if(j-1 > -1)
                        weptField[i][j-1]++;
                    if(i+1<field.length && j+1<field[i].length)
                        weptField[i+1][j+1]++;
                    if(i-1>-1 && j-1>-1)
                        weptField[i-1][j-1]++;
                    if(i-1>-1 && j+1<field[i].length)
                        weptField[i-1][j+1]++;
                    if(i+1<field.length && j-1>-1)
                        weptField[i+1][j-1]++;

                }
            }
        }
        for (int i=0; i<field.length; i++){
            for(int j=0; j<field[i].length; j++)
                if(field[i][j] == '*')
                weptField[i][j] = '*';
        }
        return weptField;
    }
}
