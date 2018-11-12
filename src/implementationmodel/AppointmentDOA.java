package implementationmodel;

import interfacemodel.DelegationOfAuthority;
import object.Appointment;
import object.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        String query = "insert into UserAccount VALUES (?,?,?,?,?,?)";
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

    }

    /**
     * @return List of All the Rows In Table
     */
    @Override
    public List GetList() {
        return null;
    }

    /**
     * @param o row to check in table
     * @return true if exist false if doesn't
     */
    @Override
    public boolean Exists(Object o) {
        return false;
    }
}
