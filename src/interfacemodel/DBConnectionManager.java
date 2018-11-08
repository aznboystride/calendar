package interfacemodel;

import java.sql.Connection;

public interface DBConnectionManager {

    Connection GetConnection();

    void CloseConnection();
}
