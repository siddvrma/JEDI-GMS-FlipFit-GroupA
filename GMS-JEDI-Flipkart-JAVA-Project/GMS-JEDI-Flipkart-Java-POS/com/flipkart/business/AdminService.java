package com.flipkart.business;

import com.flipkart.DAO.AdminDAO;
import com.flipkart.DAO.AdminInterfaceDAO;
import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;

import java.util.ArrayList;
import java.util.List;

public class AdminService implements AdminServiceInterface{

    AdminInterfaceDAO adminDAO  = new AdminDAO();

    private List<GymOwner> pendinGymOwnerList = new ArrayList<>();
    private List<GymCentre> pendinGymCentreList = new ArrayList<>();

    public void approveGymCenter(String gymCentreId,int isApproved){
        //takes GymCenter Object as input and return boolean
//        System.out.println("Approved/Rejected Gym Center: ");
        adminDAO.validateGymCentre(gymCentreId,isApproved);
    }

    public void approveGymOwner(String gymOwnerId,int isApprove){
        //takes GymOwner Object as input and return boolean
        adminDAO.validateGymOwner(gymOwnerId,isApprove);
    }

    public List<GymCentre> viewPendingGymCentres(){
        //views all pending requests
        //System.out.println("Viewing pending Gym Center Approvals: ");
        pendinGymCentreList = adminDAO.getPendingGymCentres();
        return pendinGymCentreList;
    }

    public List<GymOwner> viewPendingGymOwners(){
        //views all pending requests
        System.out.println("Viewing pending Gym Owner Approvals: ");
        pendinGymOwnerList = adminDAO.getPendingGymOwners();
        return pendinGymOwnerList;
    }

}
