package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTester {
    private static String FIC1_REGEX="^([\\S])+_([0-9]){6}_FIC1_([\\S])+\\.foobar\\.fr\\.csv$";



    public static void main(String[] args) {


        String fic1="test.dev.fr_201907_fic1_sub.foobar.fr.csv";
        String fic2 = "test.d$ev.fr_2019ds07_fic_sub.foobar.fr.csv";

        Pattern pCon = Pattern.compile(FIC1_REGEX);
        Matcher mCon = pCon.matcher(fic1);
        if (mCon.matches()) {
            System.out.println("fic1 OK");
        } else {
            System.out.println("fic1 KO");
        }

        Pattern pEch = Pattern.compile(FIC1_REGEX);
        Matcher mEch = pEch.matcher(fic2);
        if (mEch.matches()) {
            System.out.println("fic2 OK");
        } else {
            System.out.println("fic2 KO");
        }
    }
}
