package implementationmodel;

import info.DBConstants;
import interfacemodel.DBConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager implements DBConnectionManager {

    private static final String URL_UserAccount = DBConstants.URL_UserAccount.GetValue();

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
        if(o instanceof UserAccountDOA) {
            try {
                System.out.println("Driver: " + DRIVER);
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL_UserAccount, USER, PASS);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
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
