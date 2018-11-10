package implementationmodel;

import info.DBConstants;
import interfacemodel.DBConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager implements DBConnectionManager {

    private static final String URL_UserAccount = DBConstants.URL_UserAccount.GetValue();

    private static final String URL_Appointment = DBConstants.URL_Appointment.GetValue();

    private static final String DRIVER = DBConstants.DRIVER.GetValue();

    private static final String USER = DBConstants.USER.GetValue();

    private static final String PASS = DBConstants.PASS.GetValue();

    private static final ConnectionManager INSTANCE = new ConnectionManager();

    private ConnectionManager() {}

    public static ConnectionManager GetInstance() {
        return INSTANCE;
    }

    private Connection connection;

    @Override
    public Connection GetConnection(Object o) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(o instanceof UserAccountDOA) {
            try {
                connection = DriverManager.getConnection(URL_UserAccount, USER, PASS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(o instanceof AppointmentDOA) {
            try {
                connection = DriverManager.getConnection(URL_Appointment, USER, PASS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    @Override
    public void CloseConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
