package sslutils.x509;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.file.Paths;
import java.security.cert.X509Certificate;


/**
 * Junit Tests of {@link X509CertificateInfo}
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class X509CertificateInfoTester {

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
            Assert.assertNotNull(x509CertificateInfo);
            Assert.assertEquals("CN=github.com,OU=,L=San Francisco,ST=California,C=US", x509CertificateInfo.getOrderedSubjectDN());
        }
        catch(Exception e) {
            Assert.fail(e.getMessage());
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
            Assert.assertNotNull(x509CertificateInfo);
            Assert.assertEquals("CN=*.docker.com,OU=,O=,O=,C=", x509CertificateInfo.getOrderedSubjectDN());
        }
        catch(Exception e) {
            Assert.fail(e.getMessage());
        }
    }



}
