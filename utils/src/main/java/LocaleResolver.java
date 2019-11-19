import org.apache.commons.lang.LocaleUtils;

import java.text.DateFormat;
import java.util.Currency;
import java.util.Locale;

public class LocaleResolver {

    public static void main(String[] args){
        Locale list[] = DateFormat.getAvailableLocales();
        for (Locale aLocale : list) {
            if(aLocale.getCountry().equals("SN") || aLocale.getCountry().equals("SN") || aLocale.getCountry().equals("UA") || aLocale.getCountry().equals("RO")
               || aLocale.getCountry().equals("LU") || aLocale.getCountry().equals("PT") || aLocale.getCountry().equals("FR") || aLocale.getCountry().equals("PL")) {
                System.out.println(aLocale.toString());
            }
        }

        Locale locale = LocaleUtils.toLocale("us_US");
        Currency currency = Currency.getInstance(locale);
        System.out.println(currency.getCurrencyCode());
        System.out.println(currency.getSymbol());
    }
}
