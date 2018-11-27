package implementationmodel;

import interfacemodel.DelegationOfAuthority;
import object.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDOA implements DelegationOfAuthority {

    private static final AppointmentDOA INSTANCE = new AppointmentDOA();

    private AppointmentDOA() {}

    public static AppointmentDOA GetInstance() {
        return INSTANCE;
    }

    private final Connection connection = ConnectionManager.GetInstance().GetConnection(this);

    /**
     * Creates Table
     */
    @Override
    public void CreateTable() {
        String createInsr = "" +
                "CREATE TABLE Appointment(" +
                "username varchar(50)," +
                "withperson varchar(50)," +
                "event varchar(50)," +
                "place varchar(50)," +
                "date_meeting DATE," +
                "time_meeting TIME" +
                ");";
        try {
            PreparedStatement statement = connection.prepareStatement(createInsr);
            statement.executeUpdate();
            System.out.println("Successfully Created Table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes Table Rows
     */
    @Override
    public void DropTable() {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM Appointment";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert Row Into Table
     *
     * @param t Row To Insert
     */
    @Override
    public void Insert(Object t) {
        Appointment app = (Appointment) t;
        PreparedStatement preparedStatement = null;
        String query = "insert into Appointment  VALUES (?,?,?,?,?,?)";
        if(connection != null)
        {
            try
            {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, app.getUsername());
                preparedStatement.setString(2, app.getWithperson());
                preparedStatement.setString(3, app.getEventName());
                preparedStatement.setString(4, app.getPlace());
                preparedStatement.setDate(5, app.getDate());
                preparedStatement.setTime(6, app.getTime());
                preparedStatement.executeUpdate();
                System.out.println("Successfully inserted Appointment: ");
                System.out.println(app);
            }
            catch(SQLException e)
            {
                System.out.println("Fail to insert Appointment");
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    /**
     * Update Row In Table
     *
     * @param o Row To Update
     */
    @Override
    public void Update(Object o) {
        Appointment app = (Appointment) o;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Appointment " +
                            "SET withperson = ?, event = ?, place = ?, date_meeting = ?, " +
                            "time_meeting = ? " +
                            "WHERE Appointment.username = ?"
            );
            preparedStatement.setString(1, app.getWithperson());
            preparedStatement.setString(2, app.getEventName());
            preparedStatement.setString(3, app.getPlace());
            preparedStatement.setDate(4, app.getDate());
            preparedStatement.setTime(5, app.getTime());
            preparedStatement.setString(6, app.getUsername());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete Row In Table
     *
     * @param o
     */
    @Override
    public void Delete(Object o) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format("DELETE FROM Appointment " +
                    "WHERE Appointment.withperson = ", ((Appointment)o).getWithperson()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return List of All the Rows In Table
     */
    @Override
    public List GetList() {
        List<Appointment> list = new ArrayList<>();
        try {
            String username, withperson, eventname, place;
            Date date_meeting;
            Time time_meeting;
            PreparedStatement preparedStatement = connection.prepareStatement(String.format("SELECT * FROM " +
                    "Appointment")
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                username = resultSet.getString("username");
                withperson = resultSet.getString("withperson");
                eventname = resultSet.getString("event");
                place = resultSet.getString("place");
                date_meeting = resultSet.getDate("date_meeting");
                time_meeting = resultSet.getTime("time_meeting");
                list.add(new Appointment(date_meeting, time_meeting, place, eventname, username, withperson));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param o row to check in table
     * @return true if exist false if doesn't
     */
    @Override
    public boolean Exists(Object o) {
        Appointment appointment = (Appointment) o;
        for(Appointment app: (List<Appointment>) GetList()) {
            if(app.getUsername().equalsIgnoreCase(app.getUsername())) {
                return true;
            }
        }
        return false;
    }
}
