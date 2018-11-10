package implementationmodel;

import interfacemodel.DelegationOfAuthority;

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes Table Rows
     */
    @Override
    public void DropTable() {

    }

    /**
     * Insert Row Into Table
     *
     * @param t Row To Insert
     */
    @Override
    public void Insert(Object t) {

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
