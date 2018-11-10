package helper;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class CalendarPoint {

    private static int x;
    private static int y;

    public static void setPoint(Node node) {
        x = GridPane.getColumnIndex(node);
        y = GridPane.getRowIndex(node);
    }


    private static int GetDayNumber() {
        return 1 + x + y * 7;
    }

    public static int compare(int num) {
        return GetDayNumber() == num ? 0 : GetDayNumber() < num ? -1 : 1;
    }
}
