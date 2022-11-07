import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudUtil {
    public static String INSERT = "INSERT INTO users(name,age,email) Value(?,?,?)";
    public static String UPDATE = "UPDATE users Set name=? where id=?";
    public static String DELETE = "Delete from users where id=?";

    public static List<User> getUser(String query) {
        List<User> user = new ArrayList<>();

        try (Connection connection = SoedBD.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");

                user.add(new User(id, name, age, email));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static List<User> createUser(User user) {
        List<User> users = new ArrayList<>();

        try (Connection connection = SoedBD.getConnection(); PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();

            PreparedStatement prep = connection.prepareStatement("Select*From users");
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");

                users.add(new User(id, name, age, email));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static List<User> updateUser(int userid, String username) {
        List<User> updateuser = new ArrayList<>();
        try (Connection connection = SoedBD.getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, username);
            ps.setInt(2, userid);
            ps.executeUpdate();
            PreparedStatement prep = connection.prepareStatement("SELECT*FROM users");
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                updateuser.add(new User(id, name, age, email));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateuser;
    }

    public static void deleteUser(int userid) {
            List<User> delUser=new ArrayList<>();
        try (Connection connection = SoedBD.getConnection(); PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1,userid);
            ps.executeUpdate();
            PreparedStatement prep = connection.prepareStatement("SELECT*FROM users");
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                delUser.add(new User(id, name, age, email));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
