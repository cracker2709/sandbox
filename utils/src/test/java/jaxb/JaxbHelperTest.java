package jaxb;

import com.google.common.io.ByteSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
public class JaxbHelperTest {

    public static final String XML_FILE = "test.xml";

    @Test
    public void testUnmarchall_should_success() {
        try {
            byte[] lbContent = FileToString.fileToBytes(XML_FILE);
            GlobalList globalList = JaxbHelper.unmarshall(GlobalList.class, ByteSource.wrap(lbContent).openStream());
            Assertions.assertNotNull(globalList);

        }catch(Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
