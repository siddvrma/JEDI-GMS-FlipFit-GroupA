package com.flipkart.DAO2;

import com.flipkart.DAO.AdminInterfaceDAO;
import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;
import com.flipkart.connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO implements AdminInterfaceDAO {
    private static final String SELECT_PENDING_GYM_OWNERS = "SELECT * FROM GymOwner WHERE isApproved = 2";
    private static final String UPDATE_GYM_OWNER_APPROVAL = "UPDATE GymOwner SET isApproved = ? WHERE userID = ?";
    private static final String SELECT_PENDING_GYM_CENTRES = "SELECT * FROM GymCentre WHERE isApproved = 2";
    private static final String UPDATE_GYM_CENTRE_APPROVAL = "UPDATE GymCentre SET isApproved = ? WHERE gymCentreID = ?";

    private static GymOwnerInterfaceDAO gymOwnerDAO = (GymOwnerInterfaceDAO) new GymOwnerDAO();
    private static GymCentreInterfaceDAO gymCentreDAO = (GymCentreInterfaceDAO) new GymCentreDAO();

    public List<GymOwner> getPendingGymOwners() {
        List<GymOwner> pendingList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PENDING_GYM_OWNERS)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Retrieve data from the resultSet and create GymOwner objects
                    String userID = resultSet.getString("userID");
                    String userName = resultSet.getString("userName");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String panNumber = resultSet.getString("panNumber");
                    String cardDetails = resultSet.getString("cardDetails");
                    int isApproved = resultSet.getInt("isApproved");

                    GymOwner gymOwner = new GymOwner(userID, userName, email, password, panNumber, cardDetails);
                    gymOwner.setApproved(isApproved);

                    // Add GymOwner object to the pendingList
                    pendingList.add(gymOwner);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingList;
    }

    public void validateGymOwner(String gymOwnerId, int isApproved) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GYM_OWNER_APPROVAL)) {

            preparedStatement.setInt(1, isApproved);
            preparedStatement.setString(2, gymOwnerId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void validateGymCentre(String gymCentreId, int isApproved) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GYM_CENTRE_APPROVAL)) {

            preparedStatement.setInt(1, isApproved);
            preparedStatement.setString(2, gymCentreId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GymCentre> getPendingGymCentres() {
        List<GymCentre> pendingList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PENDING_GYM_CENTRES)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Retrieve data from the resultSet and create GymCentre objects
                    String gymCentreID = resultSet.getString("gymCentreID");
                    String ownerID = resultSet.getString("ownerID");
                    String gymCenterName = resultSet.getString("gymCenterName");
                    String gstin = resultSet.getString("gstin");
                    String city = resultSet.getString("city");
                    int capacity = resultSet.getInt("capacity");
                    int price = resultSet.getInt("price");
                    int isApproved = resultSet.getInt("isApproved");

                    GymCentre gymCentre = new GymCentre(gymCentreID, ownerID, gymCenterName, gstin, city, capacity, price, isApproved);

                    // Add GymCentre object to the pendingList
                    pendingList.add(gymCentre);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingList;
    }
}
