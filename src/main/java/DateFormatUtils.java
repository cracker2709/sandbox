import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by a204043 on 22/05/2014.
 * Classe permettant de ne pas avoir d'acces concurrent sur les DateFormat qui n'est pas Thread Safe
 */
public class DateFormatUtils {
    private static final String FILE_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String TIMESTAMP_BATCH = "yyyy-MM-dd";

    private static DateFormat fileFormat = new SimpleDateFormat(FILE_DATE_FORMAT);
    private static DateFormat sqlFormat = new SimpleDateFormat(TIMESTAMP_PATTERN);
    private static DateFormat formatBatch = new SimpleDateFormat(TIMESTAMP_BATCH);

    /**
     * Formattage d'une date en String
     * @param date date au format Date et Pattern FILE_DATE_FORMAT
     * @return la chaine de caractere de la date
     */
    public static String formatFileDateToString(Date date) {
        synchronized (fileFormat) {
            return fileFormat.format(date);
        }
    }

    /**
     * Parse la chaine de caractere de la date
     * @param sDate chaine date et Pattern FILE_DATE_FORMAT
     * @return la date au format Date
     * @throws ParseException
     */
    public static Date parseFileStringToDate(String sDate) throws ParseException {
        synchronized (fileFormat) {
            return fileFormat.parse(sDate);
        }
    }

    /**
     * Formattage d'une date en String
     * @param date date au format Date et Pattern TIMESTAMP_PATTERN
     * @return la chaine de caractere de la date
     */
    public static String formatSqlDateToString(Date date) {
        synchronized (sqlFormat) {
            return sqlFormat.format(date);
        }
    }

    /**
     * Parse la chaine de caractere de la date
     * @param sDate chaine date et Pattern TIMESTAMP_PATTERN
     * @return la date au format Date
     * @throws ParseException
     */
    public static Date parseSqlStringToDate(String sDate) throws ParseException {
        synchronized (sqlFormat) {
            return sqlFormat.parse(sDate);
        }
    }

    public static String formatDateToBatchString(Date date) {
        synchronized (formatBatch) {
            return formatBatch.format(date);
        }
    }


    /**
     * Retourne le nombre de jours entre 2 dates
     * @param c1
     * @param c2
     * @return le nombre de jours
     */
    public static long getNbDays(Calendar c1, Calendar c2){
        long HEURE = 60 * 60 *1000L;
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        c2.set(Calendar.HOUR, 0);
        return Math.abs(((c2.getTime().getTime()- c1.getTime().getTime() + HEURE) / (HEURE * 24)));
    }

    /**
     * valide si le format de l'entrée respect la syntaxe [yyyy-MM-dd HH:mm:ss]
     * @param input represente un champ a comparer
     * @return true si le format est validé, sinon false
     */
    public static boolean isValidTimestampPattern(String input) {
        try {
            sqlFormat.parse(input);
            return true;
        }
        catch(ParseException e){
            return false;
        }
    }

    /**
     * valide si le format de l'entrée respect la syntaxe [yyyyMMdd]
     * @param input represente un champ a comparer
     * @return true si le format est validé, sinon false
     */
    public static boolean isValidClassicDate(String input) {
        if(input.length() == 8 && StringUtils.isNumeric(input)) {
            int year = Integer.parseInt(input.substring(0, 4));
            int month = Integer.parseInt(input.substring(4, 6));
            int day = Integer.parseInt(input.substring(6));
            if((month > 0) && (month < 13) && (day > 0) && (day < 32) && (year > 0)){
                return true;
            }
        }
        return false;
    }

    /**
     * valide si le format de l'entrée respect la syntaxe [yyyyMM]
     * @param input represente un champ a comparer
     * @return true si le format est validé, sinon false
     */
    public static boolean isValidPeriod(String input) {
        if(input.length() == 6 && StringUtils.isNumeric(input)) {
            int year = Integer.parseInt(input.substring(0, 4));
            int month = Integer.parseInt(input.substring(4, 6));
            if((month > 0) && (month < 13) && (year > 0)){
                return true;
            }
        }
        return false;
    }
}
