package dataStructures;

import java.util.*;

public class P10038 {
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        boolean stop = false;
        while (c.hasNextInt()) {
            int n = c.nextInt();
            int[] numbers = new int[n];
            Set<Integer> differences = new HashSet<>();
            for(int i=0; i<n; i++)
                numbers[i] = c.nextInt();

            for(int i=0; i+1<numbers.length; i++)
                differences.add(Math.abs(numbers[i]-numbers[i+1]));
            ArrayList<Integer> differencesList= new ArrayList<>(differences);
            Collections.sort(differencesList);
            if(differencesList.size()==0 ||
                    (differencesList.size() == n-1 && differencesList.get(0)==1))
                System.out.println("Jolly");
            else
                System.out.println("Not jolly");

        }
    }
}
