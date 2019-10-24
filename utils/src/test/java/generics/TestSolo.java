package generics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSolo {
    @Test
    public void testSolo() {
        Solo<Integer> soloInt = new Solo(0);
        Solo<String> soloString = new Solo("foobar");

        Assertions.assertEquals(0, soloInt.getValue().intValue());
        Assertions.assertEquals("foobar", soloString.getValue());

    }
}
