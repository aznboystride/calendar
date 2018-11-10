package interfacemodel;

import java.sql.Connection;

public interface DBConnectionManager {

    /**
     * Determines how database connection is established
     * @return Connection to database
     */
    Connection GetConnection();

    /**
     * Closes the current connection to database
     */
    void CloseConnection();
}
