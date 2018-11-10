package helper;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class CalendarPoint {

    private int x;
    private int y;

    public CalendarPoint(Node node) {
        x = GridPane.getColumnIndex(node);
        y = GridPane.getRowIndex(node);
    }

    public int GetDayNumber() {
        return 1 + x + y * 7;
    }

    public int compare(int num) {
        return GetDayNumber() == num ? 0 : GetDayNumber() < num ? -1 : 1;
    }
}
