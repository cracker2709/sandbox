package generics;

import org.junit.Assert;
import org.junit.Test;

public class TestPair {
    @Test
    public void testPair() {
        Pair<Integer, String> pairIntStr = new Pair(0, "foobar");
        pairIntStr.toString();
        Pair<String, Integer> pairStringInt = new Pair("plop", 1);
        pairStringInt.toString();

        Assert.assertEquals(0, pairIntStr.getKey().intValue());
        Assert.assertEquals("foobar", pairIntStr.getValue());
        Assert.assertEquals("plop", pairStringInt.getKey());
        Assert.assertEquals(1, pairStringInt.getValue().intValue());
    }
}
