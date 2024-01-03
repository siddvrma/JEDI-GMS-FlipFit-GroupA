package com.flipkart.client;

import com.flipkart.service.AdminService;
import com.flipkart.service.CustomerService;

public class FlipFitAdminMenu {
    public static void main(String[] args){
        AdminService adminService = new AdminService();
        System.out.println(adminService.isUserApproved(1));
        System.out.println(adminService.isGymApproved(1));
        System.out.println(adminService.isGymOwnerApproved(1));
    }
}
