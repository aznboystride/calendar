package helper;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeParser {

    public static Time parseTimeFromString(String t, String f) {
        SimpleDateFormat sdf = new SimpleDateFormat(f);
        try {
            return new Time(sdf.parse(t).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDateFromString(String t, String f) {
        SimpleDateFormat sdf = new SimpleDateFormat(f);
        try {
            return new Date(sdf.parse(t).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
