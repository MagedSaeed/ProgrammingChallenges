package gettingStarted;

import java.util.*;

public class P10142 {
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        int cases = c.nextInt();
        c.nextLine();
        while (cases-- > 0) {
            int n = c.nextInt();
            c.nextLine();
            // initialize array of candidate names
            String[] candidates = new String[n];
            for (int i = 0; i < n; i++)
                candidates[i] = c.nextLine();
            // initialize array of votes " ballots "
            int[][] ballots = new int[1000][n];
            int i = 0;
            String line = c.nextLine();
            int totalBallots = 0;
            while (!line.trim().isEmpty()) {
                String[] splitLine = line.split(" ");
                for (int j = 0; j < splitLine.length; j++)
                    ballots[i][j] = Integer.parseInt(splitLine[j]);
                i++;
                line = c.nextLine();
            }
            totalBallots = i;
            double[] percentages;
            int winner = -1;

            //sum votes for each candidate. returns array of candidate percentage
            percentages = calculateVotes(ballots, totalBallots);
            int round = 0;
            do {
                // if person k > 50% mark him as winner. else :
                for (i = 0; i < percentages.length; i++)
                    if (percentages[i] > 0.5)
                        winner = i;

                // eliminate those with the lowest vote and add their votes to the other non-eliminated candidates
                eliminateCandidates(ballots, percentages, totalBallots);
                // recalculate.
            } while (winner < 0 && ++round < n);

            if (winner > 0)
                System.out.println(candidates[winner]);
            else {
                for (i = 0; i < n; i++)
                    if (percentages[i] > 0)
                        System.out.println(candidates[i]);
                System.out.println();
            }

        }
    }

    private static double[] calculateVotes(int[][] ballots, int totalBallots) {
        int n = ballots[0].length;
        double[] percentages = new double[n];
        for (int i = 0; i < ballots.length && ballots[i][0] > 0; i++) {
            percentages[ballots[i][0] - 1]++;
        }

        // calculate percentages:
        for (int i = 0; i < percentages.length; i++)
            percentages[i] /= (double) totalBallots;
        return percentages;
    }

    private static void eliminateCandidates(int[][] ballots, double[] percentages, int totalBallots) {
        // finding the minimum percentage
        double min = 1.0;
        for (int i = 0; i < percentages.length; i++)
            if (percentages[i] < min && percentages[i] >= 0)
                min = percentages[i];

        // excluding candidates with minimum percentage from percentage and ballots lists. i.e. convert their values to -1.
        for (int i = 0; i < percentages.length; i++) {
            if (percentages[i] == min) {
                percentages[i] = -1;
                // change in the ballot list:
                for (int j = 0; j < ballots.length; j++)
                    for (int k = 0; k < ballots[i].length; k++)
                        if (ballots[j][k] == i)
                            ballots[j][k] = -1;
            }
        }
        // add votes for eliminated candidates to non-eliminated ones
        for (int i = 0; i < ballots.length; i++) {
            if (ballots[i][0] == -1) {
                // negate more so that it would not be calculated in the next rounds.
                ballots[i][0]--;
                for (int j = 1; j < ballots[i].length; j++)
                    if (ballots[i][j] > 0) {
                        // add to the current percentage
                        percentages[ballots[i][j] - 1] += (1.0 / totalBallots);
                        break;
                    }
            }
        }
    }
}