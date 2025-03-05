package tracking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tracking.db.ConnectionManager;
import tracking.model.User;

public class UserDAO {
    //sign up
    public boolean registerUser(User bean) {
        String username = bean.getUsername();
        String email = bean.getEmail();
        String password = bean.getPassword();
        String gender = bean.getGender();
        String phonenumber = bean.getPhonenumber();
        
        String sql = "INSERT INTO user (username, email, password, gender, phonenumber) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, gender);
            ps.setString(5, phonenumber);

            ps.executeUpdate();
            System.out.println("Successfully Registered");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //login user
    public boolean validateUser(User bean) {
        String username = bean.getUsername();
        String password = bean.getPassword();

        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //login admin
    public boolean validateAdmin(User bean) {
        String username = bean.getUsername();
        String password = bean.getPassword();

        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //get user detail
    public User getUserDetails(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, username);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setGender(rs.getString("gender"));
                    user.setPhonenumber(rs.getString("phonenumber"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    //update user detail
    public boolean updateUserDetails(User user) {
        String sql = "UPDATE user SET email = ?, gender = ?, phonenumber = ? WHERE username = ?";
        
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getGender());
            ps.setString(3, user.getPhonenumber());
            ps.setString(4, user.getUsername());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
