package helper;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * Determines value of the day corresponding to the coordinate of
 * Gridpane Calendar.
 */
public class CalendarPoint {

    private static int x;
    private static int y;

    /**
     * Sets x and y to the coordinate of
     * @param node node object of grid pane
     */
    public static void setPoint(Node node) {
        x = GridPane.getColumnIndex(node);
        y = GridPane.getRowIndex(node);
    }

    /**
     *
     * @return Value of day corresponding to the coordinate
     */
    public static int GetDayNumber() {
        return 1 + x + y * 7;
    }

    /**
     *
     * @param num day to compare to
     * @return -1 if less, 0 if equal, 1 if greater
     */
    public static int compare(int num) {
        return GetDayNumber() == num ? 0 : GetDayNumber() < num ? -1 : 1;
    }
}
