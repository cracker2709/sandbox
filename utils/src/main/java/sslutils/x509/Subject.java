package sslutils.x509;

import lombok.Data;

@Data
public class Subject {
	private String CN;
	private String GivenName;
	private String SurName;
	private String OU;
	private String L;
	private String ST;
	private String O;
	private String C;
}
