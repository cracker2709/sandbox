package date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Classe permettant de gerer les formats de date
 * 
 * @author Sopra
 * 
 */
public class DateUtils {
    /**
     * Le format de la date dans les noms de fichier
     */
    public static final String DATE_FORMAT_FILE_STR = "yyyyMMdd-HHmmss";

    /**
     * Le format de la date dans les xml
     */
    public static final String DATE_FORMAT_XML_STR = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private static DateFormat getDateFormat(final String format) {
        return new SimpleDateFormat(format, Locale.FRANCE);
    }

    public static Date strToDateFile(final String dateStr)
        throws ParseException {
        return getDateFormat(DATE_FORMAT_FILE_STR).parse(dateStr);
    }

    public static String dateToStrFile(final Date date) {
        return getDateFormat(DATE_FORMAT_FILE_STR).format(date);
    }

    public static Date strToDatXML(final String dateStr) throws ParseException {
        return getDateFormat(DATE_FORMAT_XML_STR).parse(dateStr);
    }

    public static String dateToStrXML(final Date date) {
        return getDateFormat(DATE_FORMAT_XML_STR).format(date);
    }
}
