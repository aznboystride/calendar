package helper;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * To be used by CalendarViewController class.
 */
public class CalendarHelper {

    /**
     *
     * @param month month in string
     * @return Integer corresponding to the month
     */
    public static int getMonthFromString(String month) {
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new Date(new SimpleDateFormat("MMMM").parse(month).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }
    
    /**
     * Returns Date Label
     */
    public static Label getDateLabel(Pane p) {
        for(Node n : p.getChildren()) {
            if(n instanceof Label && ((Label)n).getId().equals("date")) {
                return (Label) n;
            }
        }
        return null;
    }
    
    /**
     * Returns App Label
     */
    public static Label getAppLabel(Pane p) {
        for(Node n : p.getChildren()) {
            if(n instanceof Label && ((Label)n).getId().equals("app")) {
                return (Label) n;
            }
        }
        return null;
    }

    /**
     *
     * @param month month in string
     * @param year year in integer
     * @return first day of the week of the month as an int. (0 is Sunday)
     */
    public static int getFirstDayOfWeek(String month, int year) {
        int monthNum = getMonthFromString(month);
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

    /**
     *
     * @param month month in string
     * @param year year in integer
     * @return last day corresponding to the month and year
     */
    public static int getLastDayOfMonth(String month, int year) {
        int monthNum = getMonthFromString(month);
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

    /**
     *
     * @param month Current month
     * @param year Current year
     * @return last day of the previous month
     */
    public static int getLastDayOfLastMonth(String month, int year) {
        int monthNum = getMonthFromString(month) - 1;
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
