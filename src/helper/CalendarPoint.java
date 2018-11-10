package helper;

public class CalendarPoint {

    private int x;
    private int y;

    public CalendarPoint() {
        x = 0;
        y = 0;
    }

    public int GetDayNumber() {
        return 1 + x + y * 7;
    }

    public int compare(int num) {
        return GetDayNumber() == num ? 0 : GetDayNumber() < num ? -1 : 1;
    }
}
