package date;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by GAP on 22/05/2014.
 * Class allowing of thread safe of of {@link DateFormat}
 */
public class DateFormatUtils {
    private static final String FILE_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String TIMESTAMP_BATCH = "yyyy-MM-dd";

    private static DateFormat fileFormat = new SimpleDateFormat(FILE_DATE_FORMAT);
    private static DateFormat sqlFormat = new SimpleDateFormat(TIMESTAMP_PATTERN);
    private static DateFormat formatBatch = new SimpleDateFormat(TIMESTAMP_BATCH);

    /**
     * date to String conversion
     * @param date with FILE_DATE_FORMAT pattern
     * @return string formated date
     */
    public static String formatFileDateToString(Date date) {
        synchronized (fileFormat) {
            return fileFormat.format(date);
        }
    }

    /**
     * String parser
     * @param sDate date string with  FILE_DATE_FORMAT pattern
     * @return date
     * @throws ParseException
     */
    public static Date parseFileStringToDate(String sDate) throws ParseException {
        synchronized (fileFormat) {
            return fileFormat.parse(sDate);
        }
    }

    /**
     * date to String conversion
     * @param date with TIMESTAMP_PATTERN pattern
     * @return string formated date
     */
    public static String formatSqlDateToString(Date date) {
        synchronized (sqlFormat) {
            return sqlFormat.format(date);
        }
    }

    /**
     * String parser
     * @param sDate date string with TIMESTAMP_PATTERN pattern
     * @return date
     * @throws ParseException
     */
    public static Date parseSqlStringToDate(String sDate) throws ParseException {
        synchronized (sqlFormat) {
            return sqlFormat.parse(sDate);
        }
    }

    /**
     * date to String conversion
     * @param date with TIMESTAMP_BATCH pattern
     * @return string formated date
     */
    public static String formatDateToBatchString(Date date) {
        synchronized (formatBatch) {
            return formatBatch.format(date);
        }
    }


    /**
     * Returns number of days nbetween dates
     * @param c1 date start
     * @param c2 date end
     * @return the number of days between these dates
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
     * Checks if input date has [yyyy-MM-dd HH:mm:ss] format
     * @param input data to check
     * @return true if valid else false
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
     * Checks if input date has [yyyyMMdd] format
     * @param input data to check
     * @return true if valid else false
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
     * Checks if input date has [yyyyMM] format
     * @param input data to check
     * @return true if valid else false
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
