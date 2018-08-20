package gettingStarted;

import java.util.Scanner;

public class P100 {
    public static void main(String [] args){
        Scanner c = new Scanner(System.in);
        while(c.hasNextLine()) {
            int firstInput = c.nextInt();
            int secondInput = c.nextInt();
            int i;
            int j;
            if(firstInput>secondInput){
                j = firstInput;
                i = secondInput;
            }
            else{
                i = firstInput;
                j = secondInput;
            }
            int max = getSeqCount(i);
            for (int k = i; k <= j; k++) {
                int count = getSeqCount(k);
                if (count > max)
                    max = count;
            }
            System.out.println(firstInput + " " + secondInput+ " " + max);
            c.nextLine();
        }
    }

    private static int getSeqCount(int n){
        int count = 0;
        while(n>1){
            count++;
            if (n%2 == 0)
                n /=2;
            else{
                n *=3;
                n +=1;
            }
        }
        return ++count;
    }
}
