package flatmaps;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapExample {

    public static List<Integer> getListOfAllInteger(List<Integer> list1, List<Integer> list2, List<Integer> list3) {
        List<List<Integer>> listOfLists = Arrays.asList(list1, list2, list3);

        List<Integer> listOfAllIntegers = listOfLists.stream()
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
        return listOfAllIntegers;
    }

    public static List<String> getListOfAllChars(String[][] dataArray) {
        List<String> listOfAllChars = Arrays.stream(dataArray)
                .flatMap(x -> Arrays.stream(x))
                .collect(Collectors.toList());
        return listOfAllChars;
    }
}
