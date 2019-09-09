package sslutils.x509;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Date;


/**
 * X509 Certificate data extraction helper
 * 
 */


@Slf4j
@Data
public class X509CertificateInfo {
	private X509Certificate x509Certificate;

	/**
	 * notBeforeDate
	 */
	private Date notBeforeDate;
	/**
	 * notAfterDate
	 */
	private Date notAfterDate;
	/**
	 * issuer
	 */
	private Issuer issuer;
	/**
	 * subject
	 */
	private Subject subject;

	private String orderedSubjectDN;

	/**
	 * X509CertificateInfo builder
	 * 
	 * @param x509Certificate
	 */
	public X509CertificateInfo(X509Certificate x509Certificate) throws CertificateEncodingException {
		this.x509Certificate = x509Certificate;
		issuer = new Issuer();
		subject = new Subject();
		init(x509Certificate);
		log.debug(this.toString());
	}



	/**
	 * Extracts {@link X509Certificate} data to build  {@link X509CertificateInfo}
	 * 
	 * @param certificate
	 *            input {@link X509Certificate}
	 */

	public void init(X509Certificate certificate) throws CertificateEncodingException {
		// Instantiation de l'objet X509CertificateInfo contenant les
		// informations recueillies dans le certificat
		setNotAfterDate(certificate.getNotAfter());
		setNotBeforeDate(certificate.getNotBefore());

		X500Name x500NameSub = new JcaX509CertificateHolder(certificate).getSubject();
		subject.setCN(parseInfo(x500NameSub, BCStyle.CN));
 		subject.setGivenName(parseInfo(x500NameSub, BCStyle.GIVENNAME));
		subject.setOU(parseInfo(x500NameSub, BCStyle.OU));
		subject.setO(parseInfo(x500NameSub, BCStyle.O));
		subject.setL(parseInfo(x500NameSub, BCStyle.L));
		subject.setST(parseInfo(x500NameSub, BCStyle.ST));
		subject.setSurName(parseInfo(x500NameSub, BCStyle.SURNAME));
		subject.setC(parseInfo(x500NameSub, BCStyle.C));

		X500Name x500NameIssuer = new JcaX509CertificateHolder(certificate).getIssuer();
		issuer.setCN(parseInfo(x500NameIssuer, BCStyle.CN));
		issuer.setC(parseInfo(x500NameIssuer, BCStyle.C));
		issuer.setO(parseInfo(x500NameIssuer, BCStyle.O));
		issuer.setOU(parseInfo(x500NameIssuer, BCStyle.OU));


		orderedSubjectDN = new StringBuilder("CN=").append(subject.getCN())
				.append(",OU=").append(subject.getOU())
				.append(",").append(!StringUtils.isBlank(subject.getL()) ? "L=" + subject.getL() : "O=" + subject.getO())
				.append(",").append(!StringUtils.isBlank(subject.getST()) ? "ST=" + subject.getST() : "O=" + subject.getO())
				.append(",C=").append(subject.getC()).toString();
	}

	/**
	 * Get identifier in {@link X509Certificate}
	 * 
	 * @param x500Name
	 *            {@link X500Name} part of certificate
	 * @param search
	 *            attribute to extract
	 * @return data of {@link X509Certificate} attribute
	 */

	private String parseInfo(X500Name x500Name, ASN1ObjectIdentifier search) {
		String value = StringUtils.EMPTY;
		if(x500Name.getRDNs(search) != null && x500Name.getRDNs(search).length > 0) {
			RDN rdn = x500Name.getRDNs(search)[0];
			value = IETFUtils.valueToString(rdn.getFirst().getValue());
		}
		return value;
	}


	@Override
	public String toString() {
		return "X509CertificateInfo{" +
				"x509Certificate=" + x509Certificate +
				", notBeforeDate=" + notBeforeDate +
				", notAfterDate=" + notAfterDate +
				", issuer=" + issuer +
				", subject=" + subject +
				", orderedSubjectDN='" + orderedSubjectDN + '\'' +
				'}';
	}
}
