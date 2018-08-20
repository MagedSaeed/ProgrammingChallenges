package dataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class P843DataGenerator {
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        int dictionarySize = c.nextInt();
        c.nextLine();
        String[] words = new String[dictionarySize];

        for (int i = 0; i < dictionarySize; i++)
            words[i] = c.nextLine();

        ArrayList<Integer> distinctSizes = new ArrayList<>();
        for (String word : words)
            if (!distinctSizes.contains(word.length()))
                distinctSizes.add(word.length());

        char[][] randomMapping = new char[2][26];
        getRandomMapping(randomMapping);

        System.out.println("Arbitrary Mappings: ");
        for (char[] aRandomMapping : randomMapping) System.out.println(Arrays.toString(aRandomMapping));

        String[][] dLines = new String[10][10];
        String[][] eLines = new String[10][10];
        generateDecryptedText(dLines, eLines, randomMapping, words);

//        System.out.println("Encrypted-Decrypted Text");
//        for (int i=0; i<dLines.length; i++) {
//            System.out.println(Arrays.toString(dLines[i]));
//            System.out.println(Arrays.toString(eLines[i]));
//        }
        System.out.println("Encrypted Text:");
        printLines(eLines);

        System.out.println("Decrypted Text:");
        printLines(dLines);


    }

    private static void printLines(String[][] lines) {
        for (String[] line : lines)
            for (int j = 0; j < line.length; j++)
                if (j == lines.length - 1)
                    System.out.print(line[j] + "\n");
                else
                    System.out.print(line[j] + " ");
    }

    private static void getRandomMapping(char[][] randomMapping) {
        Random rand = new Random();
        ArrayList<Integer> randoms = new ArrayList<>();
//        while (randoms.size() < 26) {
//            int newRand = rand.nextInt(26);
//            if (!randoms.contains(newRand))
//                randoms.add(newRand);
//        }

        for (int i = 0; i < 26; i++) {
            randomMapping[0][i] = (char) ('a' + i);
//            randomMapping[1][i] = (char) ('a' + randoms.get(i));
            randomMapping[1][i] = (char) ('a' + rand.nextInt(26));
        }
    }

    private static void generateDecryptedText(String[][] dLines, String[][] eLines, char[][] randomMapping, String[] words) {
        Random rand = new Random();
        for (int i = 0; i < dLines.length; i++) {
            for (int j = 0; j < dLines[i].length; j++) {
                String randomWord = words[rand.nextInt(words.length)];
                System.out.println("Random Word: "+randomWord);
                for (int k = 0; k < randomWord.length(); k++) {
                    if (k == 0) {
                        dLines[i][j] = "";
                        eLines[i][j] = "";
                    }
                    eLines[i][j] += randomMapping[1][randomWord.charAt(k) - 'a'];
                    dLines[i][j] += randomMapping[0][randomWord.charAt(k) - 'a'];
                }
            }
        }
    }
}
