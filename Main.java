import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            UserDAO userDAO = new UserDAO();
            AIModelDAO aiModelDAO = new AIModelDAO();

            // Create a new user
            User user = new User(0, "johndoe", "johndoe@example.com", "pwd1", "data_scientist");
            userDAO.createUser(user);

            // Retrieve the user
            User retrievedUser = userDAO.findById(1);
            System.out.println("User: " + retrievedUser.getUsername());

            // Create a new AI model
            AIModel model = new AIModel(0, "Sentiment Analysis Model", "A model to analyze sentiment.", retrievedUser);
            aiModelDAO.createModel(model);

            // Retrieve the AI model
            AIModel retrievedModel = aiModelDAO.findById(1);
            System.out.println("Model: " + retrievedModel.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

