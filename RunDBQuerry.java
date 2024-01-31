
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RunDBQuerry {

    // JDBC URL, username, and password of PostgreSQL server
    private static final String URL = "url";
    private static final String USER = "admin";
    private static final String PASSWORD = "pwd";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Register the JDBC driver
            Class.forName("org.postgresql.Driver");

            // Open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Execute a query
            String query = "SELECT * FROM public.message";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();

                // Process the result set
                while (resultSet.next()) {
                    // Assuming your_table_name has a column named "column_name"
                    String columnValue = resultSet.getString("message_id");
                    System.out.println("Column Value: " + columnValue);
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
