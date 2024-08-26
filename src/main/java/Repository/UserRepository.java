package Repository;

import Model.User;
import Model.dto.CreateUserDto;
import Service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public static boolean create(CreateUserDto userData) throws SQLException {
        String query = """
            INSERT INTO User (firstName, lastName, Email, Salt, passwordHash)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, userData.getFirstName());
            pst.setString(2, userData.getLastName());
            pst.setString(3, userData.getEmail());
            pst.setString(4, userData.getSalt());
            pst.setString(5, userData.getPasswordHash());

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static User getByEmail(String email){
        String query = "SELECT * FROM USER WHERE Email = ? LIMIT 1";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, email);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
private static User getFromResultSet(ResultSet result){
    try{
        int Id = result.getInt("Id");
        String firstName = result.getString("firstName");
        String lastName = result.getString("lastName");
        String Email = result.getString("Email");
        String Salt = result.getString("Salt");
        String passwordHash = result.getString("passwordHash");
        return new User(
                Id,  firstName,lastName, Email, Salt, passwordHash
        );
    }catch (Exception e){
        System.out.println("Error");
        return null;
    }
}


}
