import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for User.
 * Provides methods to interact with the User database table.
 */
public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gemini";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public User findById(int userId) throws SQLException {
        String query = "SELECT * FROM User WHERE user_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("user_id"), rs.getString("username"),
                                    rs.getString("email"), rs.getString("password"), rs.getString("role"));
                }
            }
        }
        return null;
    }

    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(rs.getInt("user_id"), rs.getString("username"),
                                   rs.getString("email"), rs.getString("password"), rs.getString("role")));
            }
        }
        return users;
    }

    public void createUser(User user) throws SQLException {
        String query = "INSERT INTO User (username, email, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
        }
    }
}
