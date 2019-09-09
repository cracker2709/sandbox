package sslutils.x509;

import lombok.Data;

@Data
public class Issuer {
	private String OU;
	private String O;
	private String C;
	private String CN;
}
