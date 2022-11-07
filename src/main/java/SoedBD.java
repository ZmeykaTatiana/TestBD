import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SoedBD {
    public static final String URL="jdbc:mysql://localhost:3306/mydbtest";
    public static final String NAME="root";
    public static final String PASSWORD="Tanko1";
    public static Connection  getConnection() {
        Connection connection=null;
        try{
            connection= DriverManager.getConnection(URL,NAME,PASSWORD);
            if (!connection.isClosed()){
                System.out.println("Подключенно к БД");
            }

        }catch  (SQLException e) {
            System.err.println("Не удалось подключиться");;
        }
    return connection;
    }
}
