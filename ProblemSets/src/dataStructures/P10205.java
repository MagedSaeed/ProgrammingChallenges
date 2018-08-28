package dataStructures;

import java.util.Scanner;

public class P10205 {
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);

        // initialize mapping:
        String [] mappings = new String [52];
        initializeMappings(mappings);


        // get the number of test cases:
        int testCases = c.nextInt();
        c.nextLine();
        c.nextLine();

        while(testCases --> 0){

            // initialize dick
            int [] dick = new int[52];
            for(int i=0; i<dick.length; i++){
                dick[i] = i+1;
            }

            int numberOfShuffles = c.nextInt();
            int [][] shuffles = new int [numberOfShuffles][52];
            for(int i=0; i<shuffles.length; i++) {
                for (int j = 0; j < shuffles[i].length; j++) {
                    shuffles[i][j] = c.nextInt();
                }
            }

            // this line also flushes the input
            c.nextLine();

            String line;
            while(c.hasNextLine()){
                line = c.nextLine();
                if(line.trim().length() == 0) {
                    break;
                }
                int shuffleNumber = Integer.parseInt(line);
                dick = performShuffle(shuffles[shuffleNumber-1], dick);
            }

            // print the results
            for (int card : dick) {
                System.out.println(mappings[card-1]);
            }
            // this condition to prevent printing a new line after the last input.
            // if this line got printed, the judge will return a presentational error.
            // "submitted 'without this condition' and returned this error on 28/8/2018"
            if(testCases != 0)
                System.out.println();
        }

    }

    private static int[] performShuffle(int[] shuffle, int[] dick) {
        int [] newDick = new int [52];
        for(int i=0; i<dick.length; i++){
            newDick[i] = dick[shuffle[i]-1];
        }
        return newDick;
    }

    private static void initializeMappings(String[] mappings) {
        for(int i=0; i<4; i++){
            String suit;
            switch (i){
                case 0:
                    suit = "Clubs";
                    break;
                case 1:
                    suit = "Diamonds";
                    break;
                case 2:
                    suit = "Hearts";
                    break;
                case 3:
                    suit = "Spades";
                    break;
                default:
                    suit = "";
            }
            for(int j=2; j<=14; j++){
                String value;
                switch (j){
                    case 11:
                        value = "Jack";
                        break;
                    case 12:
                        value = "Queen";
                        break;
                    case 13:
                        value = "King";
                        break;
                    case 14:
                        value = "Ace";
                        break;
                    default:
                        value = j+"";
                }
                mappings[(j-2)+(i*13)] = value+" of "+suit;
            }
        }
    }
}
