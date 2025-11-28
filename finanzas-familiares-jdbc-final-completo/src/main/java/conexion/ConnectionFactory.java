package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionFactory: centraliza la conexión JDBC a PostgreSQL.
 * Modifica URL, USER y PASSWORD según tu entorno.
 */
public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://localhost:5432/finanzas_familiares";
    private static final String USER = "postgres";
    private static final String PASSWORD = "2424";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
