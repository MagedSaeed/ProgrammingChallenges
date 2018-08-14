package dataStructures;

import java.util.Scanner;
import java.util.Stack;

public class P10050{
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        int cases = c.nextInt();
        while (cases --> 0) {
            int period = c.nextInt();
            int parties = c.nextInt();
            int[] parameters = new int[parties];
            for (int i = 0; i < parameters.length; i++)
                parameters[i] = c.nextInt();
            int lostWorkingDays = calculateLostWorkingDays(parameters, period);
            System.out.println(lostWorkingDays);
        }
    }

    private static int calculateLostWorkingDays(int[] parameters, int period) {
        int lostWorkingDays = 0;
        Stack<Integer> hartals = new Stack<>();
        for (int parameter : parameters) {
            int counter = parameter;
            while(counter <= period){
                if(!hartals.contains(counter)) {
                    hartals.add(counter);
                    if (counter % 7 != 0 && counter % 7 != 6) {
                        lostWorkingDays++;
                    }
                }
                counter += parameter;
            }
        }
        return lostWorkingDays;
    }
}