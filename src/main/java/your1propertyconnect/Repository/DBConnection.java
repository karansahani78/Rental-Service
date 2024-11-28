//Your1PropertyConnect\src\main\java\your1propertyconnect\Dao\DBConnection.java

package your1propertyconnect.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/your1propertyconnect";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1988";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
