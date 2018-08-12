import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class P10137 {
    public static void main(String [] args){
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        System.out.println();
        while(n!=0){
            double [] costs = new double [n];
            double totalCosts = 0;
            for(int i=0; i<n; i++) {
                costs[i] = c.nextDouble();
                totalCosts += costs[i];
            }
            double costByPerson = totalCosts/n;
            double moneyExchanged = 0.0;
            for (double cost : costs)
                if (cost < costByPerson)
                    moneyExchanged += (costByPerson - cost);

            System.out.printf("%.2f\n",moneyExchanged);
            n = c.nextInt();
        }
    }
}
