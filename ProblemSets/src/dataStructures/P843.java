package dataStructures;

import java.util.*;

public class P843 {
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        int dictionarySize = c.nextInt();
        c.nextLine();

        // get the dictionary from the input stream:
        String[] words = new String[dictionarySize];
        for (int i = 0; i < words.length; i++)
            words[i] = c.nextLine();
        // get encrypted input
        String line = c.nextLine();
        while (c.hasNextLine()) {
            String[] eWords = line.split(" ");
            String[] dWords = decryptWords(eWords, words);
            for (int i = 0; i < dWords.length; i++)
                if (i != dWords.length - 1) {
                    System.out.print(dWords[i] + " ");
                } else {
                    System.out.println(dWords[i]);
                }
            line = c.nextLine();
            if (line.trim().length() == 0)
                System.exit(0);
        }

    }

    private static String[] decryptWords(String[] eWords, String[] words) {

        String[] dWords = new String[eWords.length];

        // get copy of the dWords so that the original version not destroyed
        String[] eWordsCopy = Arrays.copyOf(eWords, eWords.length);
        // sort by length from the greatest to the smallest
        Arrays.sort(eWordsCopy, new Comparator<String>() {
            @Override
            public int compare(String w1, String w2) {
                return Integer.compare(w2.length(), w1.length());
            }
        });

        // initialize a key array to hold decrypted letters:
        char[][] keyArray = new char[2][26];
        for (int i = 0; i < 26; i++) {
            keyArray[0][i] = (char) ('a' + i);
            keyArray[1][i] = '*';
        }


        // restore keyArray original values if there is no solution to all words
        if (!matchWords(words, eWordsCopy, keyArray))
            for (int i = 0; i < 26; i++) {
                keyArray[0][i] = (char) ('a' + i);
                keyArray[1][i] = '*';
            }
        for (int i = 0; i < eWords.length; i++) {
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < eWords[i].length(); j++)
                temp.append(keyArray[1][eWords[i].charAt(j) - 97]);
            dWords[i] = temp.toString();
        }
        return dWords;
    }

    private static boolean matchWords(String[] words, String[] eWords, char[][] keyArray) {
        ArrayList<String> promisingWords = new ArrayList<>();
        String eWord = eWords[0];
        // this is an array of booleans to check whether a letter got updated. This array is useful in the backtracking
        // step where we remove a word from the keyArray
        boolean[] updatePattern = new boolean[eWord.length()];
        // get promising words that may match
        for (String word : words)
            if (word.length() == eWord.length()
                    && wordPattern(word).equals(wordPattern(eWord)))
                promisingWords.add(word);

        for (String word : promisingWords) {
            if (mapWord(eWord, word, keyArray, updatePattern)) {
                if (eWords.length > 1) {
                    // recursive call:
                    if (matchWords(words, Arrays.copyOfRange(eWords, 1, eWords.length), keyArray))
                        return true;
                    else {
                        // remove the word from the dictionary to try another one
                        for (int i = 0; i < eWord.length(); i++)
                            if (keyArray[1][eWord.charAt(i) - 97] == word.charAt(i) && updatePattern[i])
                                keyArray[1][eWord.charAt(i) - 97] = '*';
                    }
                }
                // if there are now decrypted words, then return true
                else
                    return true;
            }
        }
        // if there is no word mapped, then return false
        return false;
    }

    private static boolean mapWord(String eWord, String word, char[][] keyArray, boolean[] updatePattern) {
        // check one-to-one from the decrypted word to the dictionary word:
        for (int i = 0; i < eWord.length(); i++)
            if (keyArray[1][eWord.charAt(i) - 97] != word.charAt(i)
                    && keyArray[1][eWord.charAt(i) - 97] != '*')
                return false;

        // check the other way around
        for(int i=0; i<word.length(); i++)
            if(!isLetterMapped(eWord.charAt(i), word.charAt(i), keyArray))
                return false;

        // update the key array:
        for(int i=0; i<eWord.length(); i++) {
            if (keyArray[1][eWord.charAt(i) - 97] == word.charAt(i))
                updatePattern[i] = false;
            else {
                keyArray[1][eWord.charAt(i) - 97] = word.charAt(i);
                updatePattern[i] = true;
            }
        }

//        System.out.println("eWord: " + eWord);
//        System.out.println("word: " + word);
//        System.out.println("this mapping is: " + isMapped);
//        if (isMapped)
//            for (char[] aKeyArray : keyArray) System.out.println(Arrays.toString(aKeyArray));
        return true;
    }

    private static boolean isLetterMapped(char eLetter, char letter, char[][] keyArray) {
        for (int i = 0; i < 26; i++)
            if (keyArray[1][i] == letter && keyArray[0][i] != eLetter)
                return false;
        return true;
    }

    // generate a word pattern
    private static String wordPattern(String word) {
        if (word.length() > 0) {
            StringBuilder mapped = new StringBuilder();
            int count = 0;
            HashMap<Character, Character> mapping = new HashMap<>();
            for (int i = 0; i < word.length(); i++)
                if (!mapping.containsKey(word.charAt(i)))
                    mapping.put(word.charAt(i), (char) (48 + count++));
            for (int i = 0; i < word.length(); i++)
                mapped.append(mapping.get(word.charAt(i)));
            return mapped.toString();
        } else {
            return "";
        }
    }
}


//public class P843 {
//    public static void main(String[] args) {
//        Scanner c = new Scanner(System.in);
//        // getting the dictionary size
//        int dictionarySize = c.nextInt();
//        c.nextLine();
//        // getting dictionary words
//        String[] words = new String[dictionarySize];
//        for (int i = 0; i < words.length; i++)
//            words[i] = c.nextLine();
//
//        // sort words array by length
////        Arrays.sort(words, new Comparator<String>() {
////            @Override
////            public int compare(String word1, String word2) {
////                return (-1) * Integer.compare(word1.length(), word2.length());
////            }
////        });
//
//        // decrypt text line by line
//        while (c.hasNextLine()) {
//            String line = c.nextLine();
//            String[] encryptedText = line.split(" ");
//            String[] decryptedText = decryptText(words, encryptedText);
//            for (int i=0; i<decryptedText.length; i++)
//                if(i != decryptedText.length -1)
//                    System.out.print(decryptedText[i]+" ");
//                else
//                    System.out.println(decryptedText[i]);
//            if(line.trim().length() == 0)
//                break;
//        }
//
//    }
//
//    private static String[] decryptText(String[] words, String[] eWords) {
//        String[] decryptedText = new String[eWords.length];
//
//        /* some optimizations */
//
//        String [] eWordsCopy = Arrays.copyOf(eWords, eWords.length);
//        // sort encrypted text by length
//        Arrays.sort(eWordsCopy, new Comparator<String>() {
//            @Override
//            public int compare(String word1, String word2) {
//                return (-1) * Integer.compare(word1.length(), word2.length());
//            }
//        });
//
//        // define a HashMap such that for every eWord, assign an ArrayList of
//        // all possible dictionary words that can match
//        HashMap<String, ArrayList<String>> eWordSolutions = new HashMap<>();
//
//        for (String eWord : eWordsCopy) {
//            ArrayList<String> possibleSolutions = new ArrayList<>();
//            for (String word : words)
//                if (word.length() == eWord.length()
//                        && countUniqueChar(word) == countUniqueChar(eWord))
//                    possibleSolutions.add(word);
//            eWordSolutions.put(eWord, possibleSolutions);
//        }
//
//        int index = 0;
//        HashMap<Character, Character> keyArray = new HashMap<>();
//        Stack<String> usedWords = new Stack<>();
//
//        while (index > -1 && index < eWordsCopy.length) {
//            if (mapWord(keyArray, eWordsCopy[index], usedWords, eWordSolutions))
//                index++;
//            else {
//                if (!usedWords.isEmpty())
//                    eWordSolutions.get(eWordsCopy[index]).add(usedWords.pop());
//                index--;
//                if (index >= 0)
//                    for (int i = 0; i < eWordsCopy[index].length(); i++)
//                        keyArray.remove(eWordsCopy[index].charAt(i));
//            }
//        }
//        if (index > 0) {
//            for (int i = 0; i < eWords.length; i++) {
//                StringBuilder decryptedWord = new StringBuilder();
//                for (int j = 0; j < eWords[i].length(); j++)
//                    decryptedWord.append(keyArray.get(eWords[i].charAt(j)));
//                decryptedText[i] = decryptedWord.toString();
//            }
//        } else {
//            for (int i = 0; i < eWords.length; i++) {
//                StringBuilder decryptedWord = new StringBuilder();
//                for (int j = 0; j < eWords[i].length(); j++)
//                    decryptedWord.append("*");
//                decryptedText[i] = decryptedWord.toString();
//            }
//        }
//        return decryptedText;
//    }
//
//    private static boolean mapWord(HashMap<Character, Character> keyArray, String eWord,
//                                   Stack<String> usedWords, HashMap<String, ArrayList<String>> eWordSolutions) {
//
//        // initialize some data structures
//        HashMap<Character, Character> currentMapping = new HashMap<>();
//
//
//        boolean matchedWord;
//        for (String word : eWordSolutions.get(eWord)) {
//            if(!usedWords.contains(word)) {
//                matchedWord = true;
////            System.out.println(word);
//                for (int i = 0; i < eWord.length(); i++) {
////                System.out.println(eWord);
//                    if ((keyArray.containsKey(eWord.charAt(i))
//                            && keyArray.get(eWord.charAt(i)) != word.charAt(i))
//                            || (currentMapping.containsKey(eWord.charAt(i))
//                            && currentMapping.get(eWord.charAt(i)) != word.charAt(i))
//                            || (keyArray.containsValue(word.charAt(i))
//                            && !getKeysByValue(keyArray, word.charAt(i)).contains(eWord.charAt(i)))) {
//                        currentMapping.clear();
//                        matchedWord = false;
//                        break;
//                    } else {
//                        currentMapping.put(eWord.charAt(i), word.charAt(i));
//                    }
//                }
//                if (matchedWord) {
//                    usedWords.push(word);
//                    // remove the current word from the possible solutions since it wont be used again
//                    eWordSolutions.get(eWord).remove(word);
//
//                    // add words to key array:
//                    for (Map.Entry<Character, Character> e : currentMapping.entrySet())
//                        keyArray.put(e.getKey(), e.getValue());
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    private static int countUniqueChar(String word) {
//        Set<Character> wordSet = new HashSet<>();
//        for (Character c : word.toCharArray())
//            wordSet.add(c);
//        return wordSet.size();
//    }
//
//    private static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
//        Set<T> keys = new HashSet<T>();
//        for (Map.Entry<T, E> entry : map.entrySet()) {
//            if (Objects.equals(value, entry.getValue())) {
//                keys.add(entry.getKey());
//            }
//        }
//        return keys;
//    }
//
//}


// code of mapWord second version:
//private static boolean mapWord(String eWord, String word, char[][] keyArray, boolean[] updatePattern) {
//    boolean isMapped = true;
//    for (int i = 0; i < eWord.length(); i++) {
//        if (isLetterMapped(eWord.charAt(i), word.charAt(i), keyArray)
//                || (keyArray[1][eWord.charAt(i) - 97] != word.charAt(i)
//                && keyArray[1][eWord.charAt(i) - 97] != '*')) {
//            isMapped = false;
//            break;
//        } else {
//            if (keyArray[1][eWord.charAt(i) - 97] == word.charAt(i))
//                updatePattern[i] = false;
//            else {
//                keyArray[1][eWord.charAt(i) - 97] = word.charAt(i);
//                updatePattern[i] = true;
//            }
//        }
//    }
//    if (!isMapped) {
//        for (int i = 0; i < eWord.length(); i++)
//            if (keyArray[1][eWord.charAt(i) - 97] == word.charAt(i) && updatePattern[i])
//                keyArray[1][eWord.charAt(i) - 97] = '*';
//    }
////        System.out.println("eWord: " + eWord);
////        System.out.println("word: " + word);
////        System.out.println("this mapping is: " + isMapped);
////        if (isMapped)
////            for (char[] aKeyArray : keyArray) System.out.println(Arrays.toString(aKeyArray));
//    return isMapped;
//}