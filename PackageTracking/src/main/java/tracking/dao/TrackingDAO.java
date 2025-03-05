package tracking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tracking.db.ConnectionManager;
import tracking.model.Shipment;

public class TrackingDAO {

	public boolean addTrackingInfo(String username, Shipment bean) {
        Integer userID = getUserIDByUsername(username);
        if (userID == null) {
            return false;
        }
        
        String trackingNum = bean.getTracking_num();
        String title = bean.getTitle();
        String courierName = bean.getCourier_name();
        Integer courierID = getCourierID(courierName);
        
        if (courierID == null) {
            return false;
        }

        String insertSql = "INSERT INTO shipment (userID, tracking_num, courierID, courier_name, title_shipment) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {

            insertStmt.setInt(1, userID);
            insertStmt.setString(2, trackingNum);
            insertStmt.setInt(3, courierID);
            insertStmt.setString(4, courierName);
            insertStmt.setString(5, title);

            int rows = insertStmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	
	public static Integer getCourierID(String courierName) {
        String getCourierIdSql = "SELECT courierID FROM courier WHERE courier_name = ?";
        Integer courierID = null;
        
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement getCourierIdStmt = connection.prepareStatement(getCourierIdSql)) {

            getCourierIdStmt.setString(1, courierName);
            ResultSet resultSet = getCourierIdStmt.executeQuery();

            if (resultSet.next()) {
                courierID = resultSet.getInt("courierID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return courierID;
    }
    
	
	
    public static Integer getUserIDByUsername(String username) {
        String getUserIdSql = "SELECT userID FROM user WHERE username = ?";
        Integer userID = null;
        
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement getUserIdStmt = connection.prepareStatement(getUserIdSql)) {

            getUserIdStmt.setString(1, username);
            ResultSet resultSet = getUserIdStmt.executeQuery();

            if (resultSet.next()) {
                userID = resultSet.getInt("userID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userID;
    }
    
  //get all tracking
    public List<Shipment> getAllShipments(String username) {
    	
        List<Shipment> shipments = new ArrayList<>();
        
        Integer userID = getUserIDByUsername(username);
        if (userID == null) {
            return shipments;
        }
        
        String sql = "SELECT * FROM shipment WHERE userID = ? ORDER BY itemID DESC";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Shipment shipment = new Shipment();
                    shipment.setItemID(resultSet.getInt("itemID"));
                    shipment.setUserID(resultSet.getInt("userID"));
                    shipment.setTracking_num(resultSet.getString("tracking_num"));
                    shipment.setCourier_name(resultSet.getString("courier_name")); 
                    shipment.setTitle(resultSet.getString("title_shipment")); 
                    shipment.setDate(resultSet.getString("date")); 
                    shipment.setTime(resultSet.getString("time")); 
                    shipment.setStatus_shipment(resultSet.getString("status_shipment")); 
                    shipments.add(shipment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shipments;
    }
    
  //get tracking by id
    public static Shipment getTrackingById(int id) {
    	
    	Shipment s = new Shipment();
    	
        String sql = "SELECT * FROM shipment WHERE itemID = ?";
       
        
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	s.setItemID(rs.getInt("itemID"));
    			s.setTracking_num(rs.getString("tracking_num"));
    			s.setTitle(rs.getString("title_shipment"));
    			s.setCourier_name(rs.getString("courier_name"));
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return s;
    }
    
  //delete tracking	
    public void deleteTracking(int id) {
        String deleteTrackingSql = "DELETE FROM shipment WHERE itemID = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(deleteTrackingSql)) {

            ps.setInt(1, id);

            // Execute query
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  //update tracking	
    public void updateTracking(Shipment bean) {
    	
    	int id = bean.getItemID();
    	String trackingNum = bean.getTracking_num();
        String title = bean.getTitle();
        String courier = bean.getCourier_name();
    	
        String updateTrackingSql = "UPDATE shipment SET tracking_num=?,courier_name=?,title_shipment=? WHERE itemID = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(updateTrackingSql)) {

        	ps.setString(1, trackingNum);
			ps.setString(2, courier);
			ps.setString(3, title);
			ps.setInt(4, id);

            // Execute query
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
