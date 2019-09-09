import org.apache.commons.lang.StringUtils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CertParser {


    /**
     * Identifiant du referentiel FINESS de la carte PS des pharmaciens
     * remplacants.
     */
    private static final String FINESS = "3";

    /**
     * Nom commun du certificat X509.
     */
    private static final String COMMON_NAME = "CN";
    
    public static void main(String [] args) {
        String value = "GN=HONORE+SN=MASSEUR1044+CN=00B7010446,OU=Masseur-Kinésithérapeute,O=TEST,C=FR";
        System.out.println("value = " + value);
        String idNat = getIdNat4CPS(value);
        System.out.println("idNat = " + idNat);
        System.out.println("\n");

        value ="SN=PHARMACH0007512+CN=899700075122+GN=CAROLE,title=Pharmacien,C=FR";
        System.out.println("value = " + value);
        idNat = getIdNat4CPS(value);
        System.out.println("idNat = " + idNat);
        System.out.println("\n");

        value ="SN=PHARMACH0007512+CN=899700075122+GN=CAROLE,title=Pharmacien,C=FR";
        System.out.println("value = " + value);
        idNat = getIdNat4CPA(value);
        System.out.println("idNat = " + idNat);
        System.out.println("\n");

        value ="GN=PATRICE+SN=MAITRE0000029750001+CN=500000000029751/CPAT0001,OU=300000000029751,L=Paris (75),O=TEST,C=FR";
        System.out.println("value = " + value);
        idNat = getIdNat4CPA(value);
        System.out.println("idNat = " + idNat);
        System.out.println("\n");

        value = "/C=FR/O=ASIP-SANTE/OU=0002 187512751/OU=IGC-SANTE/CN=AC IGC-SANTE FORT PERSONNES";
        System.out.println("value = " + value);
        idNat = getIdNat4CPS(value);
        System.out.println("idNat = " + idNat);
        System.out.println("\n");

        value = "CN=810002813839+SN=DONNAT-GADY+GN=ISABELLE,title=MÃ©decin,C=FR";
        System.out.println("value = " + value);
        idNat = getIdNat4CPS(value);
        System.out.println("idNat = " + idNat);
        System.out.println("\n");

        value = "/C=FR/ST=Hauts-de-Seine (92)/O=SANTEOS SA/OU=341930067800069/CN=sonde1.mssante.fr";
        System.out.println("value = " + value);
        idNat = getIdNat4CPS(value);
        System.out.println("idNat = " + idNat);
        System.out.println("\n");

        value = "/C=FR/ST=Seine-Maritime (76)/O=PHARMACIE CRISTOL - LOUIN/OU=1760021832/title=Carte de service sant\\xC3\\xA9 ou social/pseudonym=EMPLOYE000000018/CN=3760021832/000000018";
        System.out.println("value = " + value);
        idNat = getIdNat4CPS(value);
        System.out.println("idNat = " + idNat);
        System.out.println("\n");

        value = "CN=sonde1.iso-production.mssante.fr,OU=300000000029751,O=MINISTERE DE TEST2975,ST=Paris (75),C=FR";
        System.out.println("value = " + value);
        idNat = getIdNat4CPS(value);
        System.out.println("idNat = " + idNat);
        System.out.println("\n");

    }

    public static final String getIdNat4CPS(final String subject) {

        String cnIssuer, cnSubject = null;
        cnSubject = parse(subject);

        System.out.println("getIdNat4CPS : idNAt parse vaut : " + cnSubject);

        // On annule l'extraction de l'issuer
        // cnIssuer = parse(issuer);
        //
        // if(StringUtils.isBlank(cnIssuer)) {
        // log.error("Issuer non extrait");
        // }else {
        // if(cnIssuer.contains(IssuerEnum.CLASSE_1.getValue()) ||
        // cnIssuer.contains(IssuerEnum.ACI_FO.getValue()) ||
        // cnIssuer.contains(IssuerEnum.ACI_ELEM_ORG.getValue())) {

        // }else {
        // log.error("Issuer non valide");
        // cnSubject = null;
        // }
        // }
        return cnSubject;
    }


    public static final String getIdNat4CPA(final String subject) {

        String cnIssuer, cnSubject = null;
        cnSubject = parse(subject);

        System.out.println("getIdNat4CPA : idNAt parse vaut : " + cnSubject);

        // cnIssuer = parse(issuer);
        //
        // if(StringUtils.isBlank(cnIssuer)) {
        // log.error("Issuer non extrait");
        // }else {
        // if(cnIssuer.contains(IssuerEnum.CLASSE_2.getValue()) ||
        // cnIssuer.contains(IssuerEnum.STD_PER.getValue())) {
        //
        // }else {
        // log.error("Issuer non valide");
        // cnSubject = null;
        // }
        // }
        return cnSubject;
    }

    private static String parse(String value) {
        String result = null;
        //Pattern pattern = Pattern.compile("[\\s\\S]*CN=[0-9]*/[0-9]+[\\s\\S]*");
        Pattern pattern = Pattern.compile("CN=[0-9]*/[0-9]+");
        Matcher matcher = pattern.matcher(value);

        try {
            if (value.contains("+")) {
                String[] values = value.split("\\+");
                List<String> list = Arrays.asList(values);
                Iterator<String> iterator = list.iterator();
                while (iterator.hasNext()) {
                    String item = iterator.next();
                    String[] items = item.split("=");
                    if (COMMON_NAME.equals(items[0])) {
                        if (!StringUtils.isBlank(items[1])) {
                            if (items[1].contains(",")) {
                                result = items[1].split(",")[0];
                            } else {
                                result = items[1];
                            }
                        }
                    }
                }
            } else if ('/' == value.charAt(0)) {
                if (matcher.find()) {
                    String item = matcher.group(0);
                    String[] items = item.split("=");
                    if (COMMON_NAME.equals(items[0])) {
                        result = items[1];
                    }
                } else {
                    String[] values = value.split("/");
                    List<String> list = Arrays.asList(values);
                    Iterator<String> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        String item = iterator.next();
                        String[] items = item.split("=");
                        if (COMMON_NAME.equals(items[0])) {
                            result = items[1];
                        }
                    }
                }
            } else if(value.contains(COMMON_NAME + "=sonde")){
                String[] values = value.split(",");
                List<String> list = Arrays.asList(values);
                Iterator<String> iterator = list.iterator();
                while (iterator.hasNext()) {
                    String item = iterator.next();
                    String[] items = item.split("=");
                    if (COMMON_NAME.equals(items[0])) {
                        result = items[1];
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'extraction du CN " + e.getMessage());
        }
        return result;
    }
    
}
