package object;

import java.sql.Time;
import java.sql.Date;

public class Appointment {

    private Date date;

    private Time time;

    private String place;

    private String eventName;

    private String username;

    private String withperson;

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
}
