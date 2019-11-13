package flatmaps;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;


public class FlatMapExampleTest {
    @Test
    public void testgetListOfAllInteger() {
        //GIVEN
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(4,5,6);
        List<Integer> list3 = Arrays.asList(7,8,9);
        List<Integer> expected = Arrays.asList(1,2,3,4,5,6,7,8,9);

        //WHEN
        List<Integer> res =  FlatMapExample.getListOfAllInteger(list1, list2, list3);

        //THEN
        Assertions.assertEquals(expected, res);
    }



    @Test
    public void testgetListOfAllChars() {
        //GIVEN
        String[][] dataArray = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}, {"g", "h"}};
        List<String> expected = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");

        //WHEN
        List<String> res = FlatMapExample.getListOfAllChars(dataArray);

        //THEN
        Assertions.assertEquals(expected, res);
    }
}
