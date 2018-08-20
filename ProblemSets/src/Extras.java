
import dataStructures.P843;

import java.util.*;
import java.util.stream.IntStream;

public class Extras {
    public static void main(String[] args) {
//        int m = 7;
//        System.out.println(++m < 8);
        Integer[] arr = {32, 5, 48, 0, 4};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int[] arr1 = {1, 5, 6};
        int[] arr2 = {1, 2, 5, 6};
//        HashMap<Character, Character> keyArray = new HashMap<>();
        String[] words = {"hello", "now", "hi"};
        String[] eText = {"diqip", "zpr", "dk"};
        Integer[] numbers = {2, 2, 4, 5};
        Set<Integer> numbersSet = new HashSet<>(Arrays.asList(numbers));
        Integer[] uniqueNumbers = numbersSet.toArray(new Integer[0]);
        System.out.println(Arrays.toString(uniqueNumbers));
//        matchWord(keyArray, words, eText, 0);
//        System.out.println(keyArray.toString());
//        System.out.println(wordPattern("mama"));
        Integer [] newArray = Arrays.copyOfRange(numbers, 2, numbers.length);
        System.out.println(Arrays.toString(newArray));
    }

    //    private static void matchWord(HashMap<Character, Character> keyArray, String[] words, String[] encryptedText, int index) {
//        // check if the first can be fit to the first word in the dictionary
//        while (!(Set.of(words[index]).size() == Set.of(encryptedText[index]).size()))
//            if (encryptedText[index].length() != words[index].length())
//                return;
//            else
//                index++;
//
//        boolean isFit = false;
//        while (!isFit && encryptedText[index].length() == words[index].length()) {
//            isFit = true;
//            for(int i=0; i<encryptedText[index].length(); i++){
//                if(keyArray.keySet().contains(encryptedText[index].charAt(i)) &&
//                        keyArray.get(encryptedText[index].charAt(i)) != words[index].charAt(i)){
//                    index++;
//                    isFit = false;
//                    keyArray.clear();
//                    break;
//                }
//                else
//                    keyArray.put(encryptedText[index].charAt(i), words[index].charAt(i));
//            }
//        }
//    }
//    private static String wordPattern(String word) {
//        if (word.length() > 0) {
//            StringBuilder mapped = new StringBuilder();
//            int count = 0;
//            HashMap<Character, Character> mapping = new HashMap<>();
//            for (int i = 0; i < word.length(); i++)
//                if (!mapping.containsKey(word.charAt(i)))
//                    mapping.put(word.charAt(i), (char) (48 + count++));
//            for (int i = 0; i < word.length(); i++)
//                mapped.append(mapping.get(word.charAt(i)));
//            return mapped.toString();
//        } else {
//            return "";
//        }
//    }
}
