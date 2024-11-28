package your1propertyconnect.Repository;
/*
 * Class was commented out because it is not in spring boot format.
 * package your1propertyconnect.Dao;
 * import your1propertyconnect.Model.User;
 * import java.sql.Connection;
 * import java.sql.PreparedStatement;
 * import java.sql.ResultSet;
 * import java.sql.SQLException;
 * 
 * public class UserDAO {
 * public boolean registerUser(User user) {
 * String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
 * try (Connection conn = DBConnection.getConnection();
 * PreparedStatement stmt = conn.prepareStatement(sql)) {
 * stmt.setString(1, user.getName());
 * stmt.setString(2, user.getEmail());
 * stmt.setString(3, user.getPassword());
 * return stmt.executeUpdate() > 0;
 * } catch (SQLException e) {
 * e.printStackTrace();
 * return false;
 * }
 * }
 * 
 * public User loginUser(String email, String password) {
 * String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
 * try (Connection conn = DBConnection.getConnection();
 * PreparedStatement stmt = conn.prepareStatement(sql)) {
 * stmt.setString(1, email);
 * stmt.setString(2, password);
 * ResultSet rs = stmt.executeQuery();
 * if (rs.next()) {
 * User user = new User();
 * user.setId(rs.getInt("id"));
 * user.setName(rs.getString("name"));
 * user.setEmail(rs.getString("email"));
 * return user;
 * }
 * } catch (SQLException e) {
 * e.printStackTrace();
 * }
 * return null;
 * }
 * }
 */
