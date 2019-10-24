package generics;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPair {
    @Test
    public void testPair() {
        Pair<Integer, String> pairIntStr = new Pair(0, "foobar");
        pairIntStr.toString();
        Pair<String, Integer> pairStringInt = new Pair("plop", 1);
        pairStringInt.toString();

        Assertions.assertEquals(0, pairIntStr.getKey().intValue());
        Assertions.assertEquals("foobar", pairIntStr.getValue());
        Assertions.assertEquals("plop", pairStringInt.getKey());
        Assertions.assertEquals(1, pairStringInt.getValue().intValue());
    }
}
