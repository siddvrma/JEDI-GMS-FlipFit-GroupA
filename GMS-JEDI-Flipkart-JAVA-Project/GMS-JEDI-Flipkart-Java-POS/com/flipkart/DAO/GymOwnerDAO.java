package com.flipkart.DAO;

import com.flipkart.bean.GymOwner;
import com.flipkart.constant.SQLConstants;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GymOwnerDAO implements GymOwnerInterfaceDAO{

    /**
     * Private data members
     */
    private Connection conn = null; /** Connection to the database */
    private PreparedStatement statement = null; /** SQL Query Statement */
    private List<GymOwner> gymOwnerList = new ArrayList<>();

    /**
     * Constructor class
     */
    public GymOwnerDAO() {}

    /**
     * Makes an API call to the database to fetch the
     * complete list of all GymOwners registered with GMS.
     * @return         list of gym owner list
     */
    public List<GymOwner> getGymOwnerList() {

        List<GymOwner> resGymOwnerList = new ArrayList<>();
        try {
            conn = DBConnection.connect();
            statement = conn.prepareStatement(SQLConstants.FETCH_ALL_GYM_OWNERS_QUERY);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                GymOwner owner = new GymOwner(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("panNumber"),
                        rs.getString("cardDetails")
                );
                owner.setApproved(rs.getInt("isApproved"));
                resGymOwnerList.add(owner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setGymOwnerList(resGymOwnerList);
        return gymOwnerList;
    }

    /**
     * Sets the gymOwnerList with an existing list
     * @param  gymOwnerList     existing list
     * @return                  void
     */
    public void setGymOwnerList(List<GymOwner> gymOwnerList) {
        this.gymOwnerList = gymOwnerList;
    }

    /**
     * Authenticates the user by fetching details from the db
     * and logs in that user, if successful.
     * @param  username     unique username of the user
     * @param  password     password of the user
     * @return              whether login was successful or not (true/false)
     */
    public boolean loginGymOwner(String username,String password) {
        try {
            conn = DBConnection.connect();
            ResultSet result;
            try {
                statement = conn.prepareStatement(SQLConstants.LOGIN_GYM_OWNER);
                statement.setString(1, username);
                statement.setString(2, password);
                result = statement.executeQuery();
                while (result.next()) {
                    if (username.equals(result.getString("Id")) && password.equals(result.getString("password"))) {
                        System.out.println("Login Success\n");
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (Exception e) {
                System.out.println("SQL Exception\n");
                return false;
            }
        }catch (SQLException e){
            System.out.println("SQL Exception\n");
        }
            return false;
    }

    /**
     * Registers a new gym owner with the GMS platform and adds
     * the details to DB. Initially, this gym owner is not approved
     * @param  gymOwner     complete GymOwner object of a new gym owner
     * @return              void
     */
    public void registerGymOwner(GymOwner gymOwner){
        try{
            conn  = DBConnection.connect();
            statement = conn.prepareStatement(SQLConstants.REGISTER_GYM_OWNER);

            statement.setString(1,gymOwner.getUserID());
            statement.setString(2,gymOwner.getUserName());
            statement.setString(3,gymOwner.getEmail());
            statement.setString(4,gymOwner.getPassword());
            statement.setString(5,gymOwner.getPanNumber());
            statement.setString(6,gymOwner.getCardDetails());
            statement.setInt(7,gymOwner.isApproved());

            statement.executeUpdate();
            System.out.println("Registration Success\n");

        }catch(SQLException e){
            System.out.println("Try again with a different Username \n");
        }

    }

    /**
     * Fetches a list of all gym owners from the db that have sent
     * an approval request to the admin, i.e., their approval
     * status is "Pending"
     * @return         list of GymOwner objects with approval status as Pending
     */
    public List<GymOwner> getPendingGymOwnerList() {

        List<GymOwner> pendingList = new ArrayList<>();
        try {
            conn = DBConnection.connect();
            statement = conn.prepareStatement(SQLConstants.FETCH_ALL_PENDING_GYM_OWNERS_QUERY);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                GymOwner owner = new GymOwner(rs.getString("id"),rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("panNumber"), rs.getString("cardDetails"));
                owner.setApproved(rs.getInt("isApproved"));
                pendingList.add(owner);
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
        return pendingList;
    }

    /**
     * A gym owner sends an approval request to the admin so that
     * they get approved in order to list their gym centres with GMS
     * @param  gymOwnerId   the id of the GymOwner who wants to send a req
     * @return              void
     */
    public void sendOwnerApprovalRequest(String gymOwnerId){

        try {
            conn = DBConnection.connect();
            System.out.println("Sending gym owner approval request..");
            statement = conn.prepareStatement(SQLConstants.SEND_GYM_OWNER_APPROVAL_REQ_QUERY);
            statement.setString(1,gymOwnerId);
            statement.executeUpdate();

        } catch (SQLException se) { se.printStackTrace(); }
        catch (Exception e) { e.printStackTrace(); }
    }
    public void setPendingGymOwnerList(){}

    /**
     * Admin calls this function to validate (approve/disapprove) a gymOwner
     * @param  gymOwnerId   the id of the GymOwner who is being validated
     * @param  isApproved   approval status, 0: Not Approved, 1: Approved, 2: Pending
     * @return              void
     */
    public void validateGymOwner(String gymOwnerId, int isApproved) {
        try {
            conn = DBConnection.connect();
            System.out.println("Fetching gyms owners..");

            statement = conn.prepareStatement(SQLConstants.SQL_APPROVE_GYM_OWNER_BY_ID_QUERY);
            statement.setInt(1, isApproved);
            statement.setString(2, gymOwnerId);
            statement.executeUpdate();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }
}
