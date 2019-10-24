package generics;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDuo {
    @Test
    public void testDuo() {
        Duo<Integer, String> duoIntStr = new Duo(0, "foobar");
        Duo<String, Integer> duoStringInt = new Duo("plop", 1);

        Assertions.assertEquals(0, duoIntStr.getValue1().intValue());
        Assertions.assertEquals("foobar", duoIntStr.getValue2());
        Assertions.assertEquals("plop", duoStringInt.getValue1());
        Assertions.assertEquals(1, duoStringInt.getValue2().intValue());
    }
}
