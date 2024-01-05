package com.flipkart.DAO;

import com.flipkart.DAO.GymOwnerInterfaceDAO;
import com.flipkart.bean.GymOwner;
import com.flipkart.connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GymOwnerDAO implements GymOwnerInterfaceDAO {

    private static List<GymOwner> gymOwnerList = new ArrayList<>();

    // Constructor to initialize the GymOwner list from the database
    public GymOwnerDAO() {
        gymOwnerList = loadGymOwnersFromDatabase();
    }

    public List<GymOwner> getGymOwnerList() {
        return new ArrayList<>(gymOwnerList);
    }

    public void setGymOwnerList(List<GymOwner> gymOwnerList) {
        this.gymOwnerList = new ArrayList<>(gymOwnerList);
    }

    public boolean loginGymOwner(String username, String password) {
        for (GymOwner owner : gymOwnerList) {
            if (username.equals(owner.getUserName()) && password.equals(owner.getPassword())) {
                System.out.println("Login Success\n");
                return true;
            }
        }
        return false;
    }

    public void registerGymOwner(GymOwner gymOwner) {
        // Insert the GymOwner data into the database and get the generated ID
        String gymOwnerId = insertGymOwnerIntoDatabase(gymOwner);

        // Set the generated ID to the GymOwner object
        gymOwner.setUserID(gymOwnerId);
        gymOwner.setApproved(2);

        // Add the GymOwner object to the list
        gymOwnerList.add(gymOwner);
        System.out.println("Registration Success\n");
    }

    public List<GymOwner> getPendingGymOwnerList() {
        List<GymOwner> pendingList = new ArrayList<>();
        for (GymOwner owner : gymOwnerList) {
            if (owner.isApproved() == 2) {
                pendingList.add(owner);
            }
        }
        return pendingList;
    }

    public void sendOwnerApprovalRequest(String gymOwnerId) {
        // Update the GymOwner's approval status in the database
        updateGymOwnerApprovalStatus(gymOwnerId, 2);
        System.out.println("Approval Request sent to Admin");
    }

    public void setPendingGymOwnerList() {
        // Not sure what this method is supposed to do. You can add the implementation accordingly.
    }

    public void validateGymOwner(String gymOwnerId, int isApproved) {
        // Update the GymOwner's approval status in the database
        updateGymOwnerApprovalStatus(gymOwnerId, isApproved);
    }

    private String insertGymOwnerIntoDatabase(GymOwner gymOwner) {
        String gymOwnerId = generateUniqueGymOwnerId(gymOwner.getUserName());

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO GymOwner(userID, userName, password, email, phoneNumber, approved) VALUES (?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, gymOwnerId);
            preparedStatement.setString(2, gymOwner.getUserName());
            preparedStatement.setString(3, gymOwner.getPassword());
            preparedStatement.setString(4, gymOwner.getEmail());
            preparedStatement.setInt(5, gymOwner.isApproved());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe throw a custom exception
        }

        return gymOwnerId;
    }

    private List<GymOwner> loadGymOwnersFromDatabase() {
        List<GymOwner> loadedGymOwners = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GymOwner");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String userId = resultSet.getString("userID");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                int approved = resultSet.getInt("approved");

                GymOwner gymOwner = new GymOwner(userId, userName,email, password, approved);
                loadedGymOwners.add(gymOwner);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe throw a custom exception
        }

        return loadedGymOwners;
    }

    private void updateGymOwnerApprovalStatus(String gymOwnerId, int isApproved) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE GymOwner SET isApproved = ? WHERE userID = ?")) {

            preparedStatement.setInt(1, isApproved);
            preparedStatement.setString(2, gymOwnerId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateUniqueGymOwnerId(String userName) {
        return userName + System.currentTimeMillis();
    }
}
