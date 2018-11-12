package controller;

public abstract class CalendarController extends Controller {

    protected abstract void initializeToggleView();

    protected abstract void initializeGridPaneCells();

    protected abstract void setGridPaneConstraints();
}
