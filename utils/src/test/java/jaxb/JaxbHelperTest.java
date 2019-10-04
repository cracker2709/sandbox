package jaxb;

import com.google.common.io.ByteSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public class JaxbHelperTest {

    public static final String XML_FILE = "test.xml";

    @Test
    public void testUnmarchall_should_success() {
        try {
            byte[] lbContent = FileToString.fileToBytes(XML_FILE);
            GlobalList globalList = JaxbHelper.unmarshall(GlobalList.class, ByteSource.wrap(lbContent).openStream());
            Assert.assertNotNull(globalList);

        }catch(Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
