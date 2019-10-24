package sslutils.x509;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Paths;
import java.security.cert.X509Certificate;


/**
 * Junit Tests of {@link X509CertificateInfo}
 *
 */

@ExtendWith(SpringExtension.class)
public class X509CertificateInfoTest {

    private static String VALID_CERT = "github.crt";
    private static String VALID_CERT2 = "dockerhub.crt";



    @Test
    public void testInit_Cert_should_success_1(){
        X509Certificate x509Certificate;
        try {
            // GIVEN
            x509Certificate = TestUtils.loadCertFromFile(Paths.get(ClassLoader.getSystemResource(VALID_CERT).toURI()));

            // WHEN
            X509CertificateInfo x509CertificateInfo = new X509CertificateInfo(x509Certificate);

            // THEN
            Assertions.assertNotNull(x509CertificateInfo);
            Assertions.assertEquals("CN=github.com,OU=,L=San Francisco,ST=California,C=US", x509CertificateInfo.getOrderedSubjectDN());
        }
        catch(Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void testInit_Cert_should_success_2(){
        X509Certificate x509Certificate;
        try {
            // GIVEN
            x509Certificate = TestUtils.loadCertFromFile(Paths.get(ClassLoader.getSystemResource(VALID_CERT2).toURI()));

            // WHEN
            X509CertificateInfo x509CertificateInfo = new X509CertificateInfo(x509Certificate);

            // THEN
            Assertions.assertNotNull(x509CertificateInfo);
            Assertions.assertEquals("CN=*.docker.com,OU=,O=,O=,C=", x509CertificateInfo.getOrderedSubjectDN());
        }
        catch(Exception e) {
            Assertions.fail(e.getMessage());
        }
    }



}
