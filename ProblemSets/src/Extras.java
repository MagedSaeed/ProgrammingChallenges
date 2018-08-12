
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class Extras {
    public static void main(String[] args) {
//        int m = 7;
//        System.out.println(++m < 8);
        Integer [] arr = {32, 5, 48, 0, 4};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int [] arr1 = {1, 5, 6};
        int [] arr2 = {1, 2, 5, 6};
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(IntStream.of(arr1).boxed().toArray(Integer[]::new)));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(IntStream.of(arr2).boxed().toArray(Integer[]::new)));
//        System.out.println(compareArrayLists(list1, list2));
    }

//    private static int compareArrayLists(ArrayList<Integer> list1, ArrayList<Integer> list2){
//        if(list1.size()>list2.size())
//            return 1;
//        else if (list1.size() < list2.size())
//            return -1;
//        else{
//            Collections.sort(list1);
//            Collections.sort(list2);
//            for (int i = 0; i < list1.size(); i++)
//                if(list1.get(i) > list2.get(i))
//                    return 1;
//                else if (list1.get(i) < list2.get(i))
//                    return -1;
//            return 0;
//        }
//    }
}
