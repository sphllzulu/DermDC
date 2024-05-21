import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for AIModel.
 * Provides methods to interact with the AI_Model database table.
 */
public class AIModelDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gemini";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public AIModel findById(int modelId) throws SQLException {
        String query = "SELECT * FROM AI_Model WHERE model_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, modelId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.findById(rs.getInt("created_by"));
                    return new AIModel(rs.getInt("model_id"), rs.getString("name"),
                                       rs.getString("description"), user);
                }
            }
        }
        return null;
    }

    public List<AIModel> findAll() throws SQLException {
        List<AIModel> models = new ArrayList<>();
        String query = "SELECT * FROM AI_Model";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                UserDAO userDAO = new UserDAO();
                User user = userDAO.findById(rs.getInt("created_by"));
                models.add(new AIModel(rs.getInt("model_id"), rs.getString("name"),
                                       rs.getString("description"), user));
            }
        }
        return models;
    }

    public void createModel(AIModel model) throws SQLException {
        String query = "INSERT INTO AI_Model (name, description, created_by) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, model.getName());
            stmt.setString(2, model.getDescription());
            stmt.setInt(3, model.getCreatedBy().getUserId());
            stmt.executeUpdate();
        }
    }
}

