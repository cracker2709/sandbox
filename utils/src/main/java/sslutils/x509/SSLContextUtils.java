package sslutils.x509;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

@Slf4j
public class SSLContextUtils {


    public static final String TLS = "TLS";
    private SSLContext sslContext;
    
    private String uri;

    private String pwd;

    public SSLContextUtils(String uri, String pwd) {
        this.uri = uri;
        this.pwd = pwd;

    }

    
    /**
     * @param keystore
     * @return
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     */
    private TrustManager[] createTrustManagers(final KeyStore keystore)
            throws KeyStoreException, NoSuchAlgorithmException {
        if (keystore == null) {
            throw new IllegalArgumentException("Keystore may not be null");
        }
        log.debug("Initializing trust manager");
        TrustManagerFactory tmfactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

        tmfactory.init(keystore);
        TrustManager[] trustmanagers = tmfactory.getTrustManagers();
        return trustmanagers;
    }

    /**
     * @return
     * @throws
     */
    private SSLContext createSSLContext()  {
        try {
            TrustManager[] trustmanagers = null;

            KeyStore keystore = createKeyStore();

                Enumeration<String> aliases = keystore.aliases();
                while (aliases.hasMoreElements()) {
                    String alias = aliases.nextElement();
                    log.debug("Trusted certificate '" + alias + "':");
                    Certificate trustedcert = keystore.getCertificate(alias);
                    if (trustedcert != null && trustedcert instanceof X509Certificate) {
                        X509Certificate cert = (X509Certificate) trustedcert;
                        log.debug("  Subject DN: " + cert.getSubjectDN());
                        log.debug("  Signature Algorithm: " + cert.getSigAlgName());
                        log.debug("  Valid from: " + cert.getNotBefore());
                        log.debug("  Valid until: " + cert.getNotAfter());
                        log.debug("  Issuer: " + cert.getIssuerDN());
                    }
                }

                trustmanagers = createTrustManagers(keystore);
                SSLContext sslcontext2 = SSLContext.getInstance("TLSv1");
                sslcontext2.init(null, trustmanagers, null);
            return sslcontext2;
        } catch (CertificateException e) {
            log.error("Key management exception", e);
        } catch (NoSuchAlgorithmException e) {
            log.error("Key management exception", e);
        } catch (KeyStoreException e) {
            log.error("Key management exception", e);
        } catch (GeneralSecurityException e) {
            log.error("Key management exception", e);
        } catch (IOException e) {
            log.error("Key management exception", e);
        }
        return null;
    }

    /**
     * @return Keystore
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws IOException
     */
    private KeyStore createKeyStore()
            throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        if (StringUtils.isBlank(uri)) {
            throw new IllegalArgumentException("Keystore url may not be null");
        }
        KeyStore keystore = null;
        if (uri.endsWith(".p12")) {
            keystore = KeyStore.getInstance("PKCS12");
        } else {
            keystore = KeyStore.getInstance("jks");
        }
        InputStream is = null;
        try {
            is = new URL(uri).openStream();
            keystore.load(is, pwd != null ? pwd.toCharArray() : null);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return keystore;
    }

    /**
     * @return
     */
    public SSLContext getSSLContext() {
        if (this.sslContext == null) {
            try {
                this.sslContext = createSSLContext();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return this.sslContext;
    }
}

