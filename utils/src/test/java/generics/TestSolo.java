package generics;


import org.junit.Assert;
import org.junit.Test;

public class TestSolo {
    @Test
    public void testSolo() {
        Solo<Integer> soloInt = new Solo(0);
        Solo<String> soloString = new Solo("foobar");

        Assert.assertEquals(0, soloInt.getValue().intValue());
        Assert.assertEquals("foobar", soloString.getValue());

    }
}
