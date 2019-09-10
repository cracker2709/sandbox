package date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarMonthCalc {
    public static void main(String[] args) {
        // Using Calendar - calculating number of months between two dates
        Calendar report = new GregorianCalendar(2019, Calendar.AUGUST, 19);
        Calendar today = new GregorianCalendar();
        today.setTime(new Date());

        int yearsInBetween = today.get(Calendar.YEAR)
                - report.get(Calendar.YEAR);
        int monthsDiff = today.get(Calendar.MONTH)
                - report.get(Calendar.MONTH);
        long ageInMonths = yearsInBetween * 12 + monthsDiff;
        long age = yearsInBetween;

        System.out.println("Number of months since report was generated : "
                + ageInMonths);

        System.out.println("reports's age : " + age);
    }



}
