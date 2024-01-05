package com.flipkart.DAO;

import com.flipkart.DAO.GymCentreInterfaceDAO;
import com.flipkart.bean.GymCentre;
import com.flipkart.connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymCentreDAO implements GymCentreInterfaceDAO {
    private static final String INSERT_GYM_CENTRE = "INSERT INTO GymCentre (gymCentreID, ownerID, city, approved) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_GYM_CENTRES_BY_OWNER = "SELECT * FROM GymCentre WHERE ownerID = ?";
    private static final String SELECT_GYM_CENTRE_BY_ID = "SELECT * FROM GymCentre WHERE gymCentreID = ?";
    private static final String UPDATE_GYM_CENTRE_APPROVAL = "UPDATE GymCentre SET approved = ? WHERE gymCentreID = ?";
    private static final String SELECT_PENDING_GYM_CENTRES = "SELECT * FROM GymCentre WHERE approved = 2";
    private static final String UPDATE_GYM_CENTRE_APPROVAL_REQUEST = "UPDATE GymCentre SET approved = 2 WHERE gymCentreID = ?";
    private static final String SELECT_GYM_CENTRES_BY_CITY = "SELECT * FROM GymCentre WHERE city = ?";

    private static List<GymCentre> allGymCentres = new ArrayList<>();

    public List<GymCentre> getAllCentresByOwmerId(String ownerId) {
        List<GymCentre> ownerGymCentres = new ArrayList<>();
        for (GymCentre gymCentre : allGymCentres) {
            if (gymCentre.getOwnerID().equals(ownerId)) {
                ownerGymCentres.add(gymCentre);
            }
        }
        return ownerGymCentres;
    }

    public GymCentre getGymCentreByCentreId(String gymCentreId) {
        for (GymCentre gymCentre : allGymCentres) {
            if (gymCentre.getGymCentreID().equals(gymCentreId)) {
                return gymCentre;
            }
        }
        return null; // or throw an exception if not found
    }

    public void addGymCentre(GymCentre centre) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GYM_CENTRE)) {

            // Assuming you have a method to generate a unique gym centre ID
            String gymCentreId = generateUniqueGymCentreId(centre.getGymCenterName());

            preparedStatement.setString(1, gymCentreId);
            preparedStatement.setString(2, centre.getOwnerID());
            preparedStatement.setString(3, centre.getCity());
            preparedStatement.setInt(4, centre.isApproved());

            // Set the generated ID to the GymCentre object
            centre.setGymCentreID(gymCentreId);

            // Add the GymCentre object to the list
            allGymCentres.add(centre);

        } catch (SQLException e) {
            // Handle exception or throw a specific exception
        }
    }

    public List<GymCentre> getPendingGymCentreList() {
        List<GymCentre> pendingList = new ArrayList<>();
        for (GymCentre gymCentre : allGymCentres) {
            if (gymCentre.isApproved() == 2) {
                pendingList.add(gymCentre);
            }
        }
        return pendingList;
    }

    public void validateGymCentre(String gymCentreId, int isApproved) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GYM_CENTRE_APPROVAL)) {

            preparedStatement.setInt(1, isApproved);
            preparedStatement.setString(2, gymCentreId);

            preparedStatement.executeUpdate();

            for (GymCentre gymCentre : allGymCentres) {
                if (gymCentre.getGymCentreID().equals(gymCentreId)) {
                    gymCentre.setApproved(isApproved);
                    break;
                }
            }

        } catch (SQLException e) {
            // Handle exception or throw a specific exception
        }
    }

    public void sendCentreApprovalRequest(String gymCentreId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GYM_CENTRE_APPROVAL_REQUEST)) {

            preparedStatement.setString(1, gymCentreId);
            preparedStatement.executeUpdate();

            for (GymCentre gymCentre : allGymCentres) {
                if (gymCentre.getGymCentreID().equals(gymCentreId)) {
                    gymCentre.setApproved(2);
                    System.out.println("Sending gym centre approval request for ID: " + gymCentreId);
                    break;
                }
            }

        } catch (SQLException e) {
            // Handle exception or throw a specific exception
        }
    }

    public List<GymCentre> getGymCentreListByCity(String city) {
        List<GymCentre> allCentreByCity = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GYM_CENTRES_BY_CITY)) {

            preparedStatement.setString(1, city);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String gymCentreId = resultSet.getString("gymCentreID");
                    String ownerId = resultSet.getString("ownerID");
                    String centreName = resultSet.getString("gymCenterName");
                    int approved = resultSet.getInt("approved");

                    GymCentre gymCentre = new GymCentre(gymCentreId, ownerId, centreName, city, approved);
                    allCentreByCity.add(gymCentre);
                }
            }

        } catch (SQLException e) {
            // Handle exception or throw a specific exception
        }
        return allCentreByCity;
    }

    private String generateUniqueGymCentreId(String centreName) {
        // Implement logic to generate a unique gym centre ID based on your requirements
        return centreName + System.currentTimeMillis();
    }
}
