package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;
import com.flipkart.business.AdminService;
import com.flipkart.business.AdminServiceInterface;
import com.flipkart.business.GymOwnerService;
import com.flipkart.business.GymOwnerServiceInterface;
import com.flipkart.exceptions.LoginFailedException;
import com.flipkart.utils.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static com.flipkart.client.MainApplicationClient.scanner;
import static com.flipkart.constant.Constants.*;

public class AdminClient {

    private static Admin admin = new Admin();
    private static AdminServiceInterface adminService = new AdminService();
    private static GymOwnerServiceInterface gymOwnerService = new GymOwnerService();

    public boolean isUserValid(String userName, String password) {
        if (userName.equals(admin.getUserName()) && password.equals(admin.getPassword())) {
            return true;
        }
        return false;
    }

    public boolean adminLogin(String userName, String password) {
        if (isUserValid(userName, password)) {
            System.out.println("Successfully logged in");
            adminClientMainPage();
        }
        else{
            new LoginFailedException("Admin Login Failed");
            return false;
        }
        return true;
    }

    private void handleGymOwnerApprovalRequests(){
            // print the list with indexes from 1
            System.out.println("Admin Approval for a Gym Owner ----------");

            System.out.println("(Press 0 to exit)\nEnter the Id of Gym Owner:");
            String requestGymOwnerId = scanner.next();

            if(requestGymOwnerId.equals("0")) {return;}

            System.out.println("1. Approve the request\n2. Reject the request");
            int choice = scanner.nextInt();
            if(choice == 1){
                System.out.println(APPROVAL_GYM_OWNER_CONFIRMATION);
            } else if (choice == 2) {
                System.out.println(DISAPPROVAL_GYM_OWNER_CONFIRMATION);
            }

            adminService.approveGymOwner(requestGymOwnerId,choice);
            //modify the list
//            adminClientMainPage();
    }
    private void handleGymCenterApprovalRequests(){
            // print the list with indexes from 1
            System.out.println("Press 0 to EXIT_MESSAGE or Choose the Gym Centre To Modify:");
            String requestGymCenterId = scanner.next();
            if (requestGymCenterId.equals("0")) return;
//            Now Admin will select an request and we will pop up with two
            System.out.println("1. Approve the request\n2. Reject the request\n");
            int choice = scanner.nextInt();
            if(choice == 1){
                System.out.println(APPROVAL_GYM_CENTRE_CONFIRMATION);
            } else if (choice == 2) {
                System.out.println(DISAPPROVAL_GYM_CENTRE_CONFIRMATION);
            }
            adminService.approveGymCenter(requestGymCenterId,choice);
            //modify the list
//            adminClientMainPage();
    }

    private void printOwnerList(List<GymOwner> gymOwnerList){
        System.out.println(DASHED_LINE);
        System.out.printf(YELLOW_COLOR + "%-8s\t", "ID");
        System.out.printf("%-8s\t", "NAME");
        System.out.printf("%-8s\t", "EMAIL-ID");
        System.out.printf("%11s\t", "PAN");
        System.out.printf("%23s\t\n", "IS-APPROVED" + RESET_COLOR);
        System.out.println(DASHED_LINE);
        System.out.println("");
        for(GymOwner gymOwner: gymOwnerList) {
            System.out.printf("%-8s\t", gymOwner.getUserID());
            System.out.printf("%-8s\t", gymOwner.getUserName());
            System.out.printf("%-8s\t", gymOwner.getEmail());
            System.out.printf("%-8s\t", gymOwner.getPanNumber());
            if(gymOwner.isApproved()==1)
            {
                System.out.println("Yes\n");
            }
            else if(gymOwner.isApproved() == 0)
            {
                System.out.println("No\n");
            } else {
                System.out.println("Pending\n");
            }
        }
        System.out.println(DASHED_LINE);
    }

    public void adminClientMainPage(){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = currentTime.format(myFormat);
        System.out.println(YELLOW_COLOR+"WELCOME ADMIN!!\nLogin Time: "+currentTime+RESET_COLOR);
        while(true){
            System.out.println("0. View All Gym Owners\n1. View Pending GymOwner Approval Requests\n2. View Pending GymCenter's Approval Requests\n3. Go Back To Previous Menu");
            int pendingChoice = scanner.nextInt();
            switch (pendingChoice) {
                case 0:
                    List<GymOwner> allGymOwners =  gymOwnerService.viewAllGymOwners();
                    printOwnerList(allGymOwners);
                    break;
                case 1:
                    List<GymOwner> pendingGymOwners = adminService.viewPendingGymOwners();
                    printOwnerList(pendingGymOwners);
                    if(!pendingGymOwners.isEmpty()) handleGymOwnerApprovalRequests();
                    break;

                case 2:
                    List<GymCentre> pendingGymCentres = adminService.viewPendingGymCentres();//get listGymCenterIds
                    util.printGymCentres(pendingGymCentres);
                    if(!pendingGymCentres.isEmpty()) handleGymCenterApprovalRequests();
                    break;
                case 3:
                    return;
            }
        }
    }

}
