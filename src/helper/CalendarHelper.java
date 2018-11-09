package helper;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarHelper {

    public static int getMonthFromString(String month) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(new SimpleDateFormat("MMMM").parse(month).getTime());
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getFirstDayOfWeek(String month, int year) {
        int monthNum = 0;
        try {
            monthNum = getMonthFromString(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(Integer.toString(year) + "-" + Integer.toString(monthNum) + "-01").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getLastDayOfMonth(String month, int year) {
        int monthNum = 0;
        try {
            monthNum = getMonthFromString(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new Date(new SimpleDateFormat("yyyy-M-dd").parse(Integer.toString(year) + "-" + Integer.toString(monthNum) + "-01").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
