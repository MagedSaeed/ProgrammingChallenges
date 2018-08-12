package dataStructures;

import java.util.*;

public class P10315 {
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        Integer[] blackCards = new Integer[5];
        Character[] blackSuits = new Character[5];
        Integer[] whiteCards = new Integer[5];
        Character[] whiteSuits = new Character[5];

        // read black and white hands.
        while (c.hasNextLine()) {
            String line = c.nextLine();
            String[] splitLine = line.split(" ");
            for (int i = 0; i < splitLine.length; i++) {
                char myChar = splitLine[i].charAt(0);
                // convert alphabetic values to integral values:
                if (i < 5) {
                    switch (myChar) {
                        case 'T':
                            blackCards[i] = 10;
                            break;
                        case 'J':
                            blackCards[i] = 11;
                            break;
                        case 'Q':
                            blackCards[i] = 12;
                            break;
                        case 'K':
                            blackCards[i] = 13;
                            break;
                        case 'A':
                            blackCards[i] = 14;
                            break;
                        default:
                            blackCards[i] = myChar - 48;
                    }
                    blackSuits[i] = splitLine[i].charAt(1);
                } else {
                    switch (myChar) {
                        case 'T':
                            whiteCards[i - 5] = 10;
                            break;
                        case 'J':
                            whiteCards[i - 5] = 11;
                            break;
                        case 'Q':
                            whiteCards[i - 5] = 12;
                            break;
                        case 'K':
                            whiteCards[i - 5] = 13;
                            break;
                        case 'A':
                            whiteCards[i - 5] = 14;
                            break;
                        default:
                            whiteCards[i - 5] = myChar - 48;
                    }
                    whiteSuits[i - 5] = splitLine[i].charAt(1);
                }
            }

            // sort the arrays
            Arrays.sort(blackCards);
            Arrays.sort(whiteCards);

            // compute and compare
            int[] results = {0, 0};

            if (isPair(blackCards))
                results[0] = 1;
            if (isPair(whiteCards))
                results[1] = 1;

            if (isTwoPair(blackCards))
                results[0] = 2;
            if (isTwoPair(whiteCards))
                results[1] = 2;

            if (isThreeOfKind(blackCards))
                results[0] = 3;
            if (isThreeOfKind(whiteCards))
                results[1] = 3;

            if (isStraight(blackCards))
                results[0] = 4;
            if (isStraight(whiteCards))
                results[1] = 4;

            if (isFlush(blackSuits))
                results[0] = 5;
            if (isFlush(whiteSuits))
                results[1] = 5;

            if (isFullHouse(blackCards))
                results[0] = 6;
            if (isFullHouse(whiteCards))
                results[1] = 6;

            if (isFourKind(blackCards))
                results[0] = 7;
            if (isFourKind(whiteCards))
                results[1] = 7;

            if (isStraightFlush(blackCards, blackSuits))
                results[0] = 8;
            if (isStraightFlush(whiteCards, whiteSuits))
                results[1] = 8;


            // printing the results
            if (results[0] != results[1])
                System.out.println((results[0] > results[1] ? "Black " : "White ") + "wins.");
            else {
                String winner = "";
                int res = 0;
                switch (results[0]) {
                    case 0:
                        res = compareHighCard(blackCards, whiteCards);
                        break;
                    case 1:
                        res = comparePair(blackCards, whiteCards);
                        break;
                    case 2:
                        res = compareTwoPairs(blackCards, whiteCards);
                        break;
                    case 3:
                        res = compareThreeOfKind(blackCards, whiteCards);
                        break;
                    case 4:
                        res = compareStraight(blackCards, whiteCards);
                        break;
                    case 5:
                        res = compareHighCard(blackCards, whiteCards);
                        break;
                    case 6:
                        res = compareThreeOfKind(blackCards, whiteCards);
                        break;
                    case 7:
                        res = compareFourKind(blackCards, whiteCards);
                        break;
                    case 8:
                        res = compareStraight(blackCards, whiteCards);
                        break;
                }
                if (res == 1)
                    winner = "Black ";
                else if (res == -1)
                    winner = "White ";
                System.out.println(winner.isEmpty() ? "Tie." : winner + "wins.");
            }
        }
    }


    static boolean isPair(Integer[] cards) {
        for (int i = 0; i < cards.length - 1; i++)
            if (cards[i].equals(cards[i + 1]))
                return true;
        return false;
    }

    static boolean isTwoPair(Integer[] cards) {
        return new HashSet<>(Arrays.asList(cards)).size() == 3 && !isThreeOfKind(cards);
    }

    static boolean isThreeOfKind(Integer[] cards) {
        List<Integer> cardsList = Arrays.asList(cards);
        for (Integer value : cards)
            if (Collections.frequency(cardsList, value) == 3)
                return true;
        return false;
    }

     static boolean isStraight(Integer[] cards) {
        for (int i = 0; i < cards.length - 1; i++)
            if (cards[i + 1] != cards[i] + 1)
                return false;
        return true;
    }

     static boolean isFlush(Character[] suits) {
        return new HashSet<>(Arrays.asList(suits)).size() == 1;
    }

     static boolean isFullHouse(Integer[] cards) {
        return new HashSet<>(Arrays.asList(cards)).size() == 2 && isThreeOfKind(cards);

    }

     static boolean isFourKind(Integer[] cards) {
        return new HashSet<>(Arrays.asList(cards)).size() == 2 && !isThreeOfKind(cards);
    }

     static boolean isStraightFlush(Integer[] cards, Character[] suits) {
        return isFlush(suits) && isStraight(cards);
    }

     static int compareHighCard(Integer[] blackCards, Integer[] whiteCards) {
        blackCards = reverseArray(blackCards);
        whiteCards = reverseArray(whiteCards);
        return compareArrays(blackCards, whiteCards);
    }

     static int comparePair(Integer[] l1, Integer[] l2) {
        // size of the two lists should be identical.
        int l1Pair = 0;
        int l2Pair = 0;

        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(l1));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(l2));

        // get the pair from the first list:
        for (Integer value : list1) {
            if (Collections.frequency(list1, value) == 2) {
                l1Pair = value;
                list1.remove(value);
                break;
            }
        }

        // get the pair from the second list:
        for (Integer value : l2) {
            if (Collections.frequency(list2, value) == 2) {
                l2Pair = value;
                list2.remove(value);
                break;
            }
        }

        if (l1Pair > l2Pair)
            return 1;
        else if (l1Pair < l2Pair)
            return -1;

        else {
            Collections.reverse(list1);
            Collections.reverse(list2);
            return compareArrayLists(list1, list2);
        }
    }

     static int compareTwoPairs(Integer[] l1, Integer[] l2) {
//        System.out.println(Arrays.toString(l1));
//        System.out.println(Arrays.toString(l2));
        ArrayList<Integer> l1List = new ArrayList<>(Arrays.asList(l1));
        ArrayList<Integer> l2List = new ArrayList<>(Arrays.asList(l2));
        int l1nonPair = 0;
        int l2nonPair = 0;

        // retrieve and remove l1 non-pair element:
        for (Integer value : l1List) {
            if (Collections.frequency(l1List, value) == 1) {
                l1nonPair = value;
                l1List.remove(value);
                break;
            }
        }

        // retrieve and remove l2 non-pair element:
        for (Integer value : l2List) {
            if (Collections.frequency(l2List, value) == 1) {
                l2nonPair = value;
                l2List.remove(value);
                break;
            }
        }
//        2H 3H 4H 5H 6H
//        AC AS AD AH KH
//        System.out.println(l1List.toString());
//        System.out.println(l2List.toString());
        if (compareArrayLists(l1List, l2List) == 0) {
            return Integer.compare(l1nonPair, l2nonPair);
        } else {
            Collections.sort(l1List);
            Collections.sort(l2List);
            Collections.reverse(l1List);
            Collections.reverse(l2List);
            return compareArrays(l1List.toArray(l1), l2List.toArray(l2));
        }

    }

     static int compareThreeOfKind(Integer[] blackCards, Integer[] whiteCards) {
        int blackVal = 0;
        int whiteVal = 0;
        for (Integer value : blackCards) {
            if (Collections.frequency(Arrays.asList(blackCards), value) == 3) {
                blackVal = value;
                break;
            }
        }
        for (Integer value : whiteCards) {
            if (Collections.frequency(Arrays.asList(whiteCards), value) == 3) {
                whiteVal = value;
                break;
            }
        }
        return Integer.compare(blackVal, whiteVal);
    }

     static int compareStraight(Integer[] blackCards, Integer[] whiteCards) {
        return Integer.compare(blackCards[4], whiteCards[4]);
    }

     static int compareFourKind(Integer[] blackCards, Integer[] whiteCards) {
        int blackVal = 0;
        for (Integer value : blackCards) {
            if (Collections.frequency(Arrays.asList(blackCards), value) == 4) {
                blackVal = value;
                break;
            }
        }

        int whiteVal = 0;
        for (Integer value : whiteCards) {
            if (Collections.frequency(Arrays.asList(whiteCards), value) == 4) {
                whiteVal = value;
                break;
            }
        }
        return Integer.compare(blackVal, whiteVal);
    }

     static Integer[] reverseArray(Integer[] array) {
        List<Integer> arrayList = Arrays.asList(array);
        Collections.reverse(arrayList);
        return arrayList.toArray(array);
    }

     static int compareArrays(Integer[] arr1, Integer[] arr2) {
        for (int i = 0; i < arr1.length; i++)
            if (arr1[i] > arr2[i])
                return 1;
            else if (arr1[i] < arr2[i])
                return -1;
        return 0;
    }

     static int compareArrayLists(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        // size of the lists shall be identical
        Collections.sort(list1);
        Collections.sort(list2);
        for (int i = 0; i < list1.size(); i++)
            if (list1.get(i) > list2.get(i))
                return 1;
            else if (list1.get(i) < list2.get(i))
                return -1;
        return 0;
    }
}
