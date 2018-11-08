package info;

public enum DBConstants {

    URL("jdbc:mysql://localhost:3306/Calendar"),
    USER("root"),
    PASS("s6flipto"),
    DRIVER("com.mysql.jdbc.Driver");

    private String info;

    DBConstants(String info) {
        this.info = info;
    }

    public String GetValue() {
        return info;
    }
}
