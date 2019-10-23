package generics;

import org.junit.Assert;
import org.junit.Test;

public class TestDuo {
    @Test
    public void testDuo() {
        Duo<Integer, String> duoIntStr = new Duo(0, "foobar");
        Duo<String, Integer> duoStringInt = new Duo("plop", 1);

        Assert.assertEquals(0, duoIntStr.getValue1().intValue());
        Assert.assertEquals("foobar", duoIntStr.getValue2());
        Assert.assertEquals("plop", duoStringInt.getValue1());
        Assert.assertEquals(1, duoStringInt.getValue2().intValue());
    }
}
