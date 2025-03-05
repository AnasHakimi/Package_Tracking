package tracking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tracking.db.ConnectionManager;
import tracking.model.Courier;


public class CourierDAO {
     
	
	//list for available couriers
    public List<String> getAllAvailableCouriers() {
        String query = "SELECT courier_name FROM courier WHERE courier_availability = 'Available'";
        List<String> couriers = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                couriers.add(rs.getString("courier_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return couriers;
    }
    
    //list for all courier
    public List<Courier> getAllCouriers() {
        List<Courier> couriers = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String query = "SELECT * FROM courier";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Courier courier = new Courier();
                courier.setCourier_id(resultSet.getInt("CourierID"));
                courier.setAdmin_id(resultSet.getInt("AdminID"));
                courier.setCourier_name(resultSet.getString("courier_name"));
                courier.setCourier_logo(resultSet.getString("courier_logo"));
                courier.setCourier_availability(resultSet.getString("courier_availability"));
                couriers.add(courier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return couriers;
    }
    
 // Get courier by ID
    public static Courier getCourierById(int id) {
        Courier c = null;
        try (Connection connection = ConnectionManager.getConnection()) {
            String query = "SELECT * FROM courier WHERE courierID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                c = new Courier();
                c.setCourier_id(resultSet.getInt("CourierID"));
                c.setAdmin_id(resultSet.getInt("AdminID"));
                c.setCourier_name(resultSet.getString("courier_name"));
                c.setCourier_logo(resultSet.getString("courier_logo"));
                c.setCourier_availability(resultSet.getString("courier_availability"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    
 // Update courier details
    public void updateCourier(Courier courier) {
        String updateCourierSql = "UPDATE courier SET courier_name=?, courier_logo=?, courier_availability=? WHERE courierID = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(updateCourierSql)) {

            ps.setString(1, courier.getCourier_name());
            ps.setString(2, courier.getCourier_logo());
            ps.setString(3, courier.getCourier_availability());
            ps.setInt(4, courier.getCourier_id());

            // Execute query
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 // Delete courier
    public void deleteCourier(int id) {
        String deleteCourierSql = "DELETE FROM courier WHERE courierID = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(deleteCourierSql)) {

            ps.setInt(1, id);

            // Execute query
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public boolean addCourierInfo(String username, Courier nc) {
        Integer adminID = getAdminIDByUsername(username);
        if (adminID == null) {
            return false;
        }
        
        String name = nc.getCourier_name();
        String logo = nc.getCourier_logo();
        String availability = nc.getCourier_availability();
        

        String insertSql = "INSERT INTO courier (adminID, courier_name, courier_logo, courier_availability) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {

            insertStmt.setInt(1, adminID);
            insertStmt.setString(2, name);
            insertStmt.setString(3, logo);
            insertStmt.setString(4, availability);

            int rows = insertStmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        
    }
    
    public static Integer getAdminIDByUsername(String username) {
        String getUserIdSql = "SELECT adminID FROM admin WHERE username = ?";
        Integer adminID = null;
        
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement getUserIdStmt = connection.prepareStatement(getUserIdSql)) {

            getUserIdStmt.setString(1, username);
            ResultSet resultSet = getUserIdStmt.executeQuery();

            if (resultSet.next()) {
                adminID = resultSet.getInt("adminID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adminID;
    }
}
