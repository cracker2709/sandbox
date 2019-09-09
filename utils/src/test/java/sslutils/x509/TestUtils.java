package sslutils.x509;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class TestUtils {
    public static String fileToString(String path) throws IOException, URISyntaxException {
        return new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(path).toURI())), StandardCharsets.UTF_8).replace("\r", "");
    }


    public static X509Certificate loadCertFromFile(Path certFile) throws Exception{
        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        FileInputStream is = new FileInputStream(certFile.toFile());
        X509Certificate cer = (X509Certificate) fact.generateCertificate(is);
        return cer;
    }
}
