package db;

import java.sql.Connection;
import java.sql.SQLException;

public interface databaseConnection {
	Connection getConnection() throws SQLException;
}
