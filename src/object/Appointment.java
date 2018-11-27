package object;

import java.sql.Time;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class Appointment implements Comparable<Appointment> {

    private Date date;

    private Time time;

    private String place;

    private String eventName;

    private String username;

    private String withperson;

    public Appointment() {

    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time);
    }

    @Override
    public boolean equals(Object o) {
        Appointment app = (Appointment) o;
        if(o == this) {
            return true;
        }
        if(!(o instanceof Appointment)) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        if(sdf.format(app.getDate()).equals(sdf.format(date))) {
            sdf = new SimpleDateFormat("hh");
            return sdf.format(app.getTime()).equals(sdf.format(time));
        }
        return false;
    }

    public String getWithperson() {
        return withperson;
    }

    public void setWithperson(String withperson) {
        this.withperson = withperson;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Appointment(Date date, Time time, String place, String eventName, String username, String withperson)
    {
        this.date = date;
        this.time = time;
        this.place = place;
        this.eventName = eventName;
        this.username = username;
        this.withperson = withperson;
    }

    @Override
    public String toString() {
        return "Username: " + username + "\n" +
                "Withuser: " + withperson + "\n" +
                "Event: " + eventName + "\n" +
                "Place: " + place + "\n" +
                "Date: " + date + "\n" +
                "Time: " + time;
    }

    public String getDateUser() {
        return date.toString() + " " + withperson;
    }

    @Override
    public int compareTo(Appointment o) {
        return date.compareTo(o.getDate());
    }
}
