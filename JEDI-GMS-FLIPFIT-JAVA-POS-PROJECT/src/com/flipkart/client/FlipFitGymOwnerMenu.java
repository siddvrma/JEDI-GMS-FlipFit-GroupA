package com.flipkart.client;

import com.flipkart.service.GymOwnerService;

public class FlipFitGymOwnerMenu {

    public static void main(String[] args){
        GymOwnerService gymOwnerService = new GymOwnerService();

        gymOwnerService.createGym();
        gymOwnerService.listGym(1);
        System.out.println(gymOwnerService.updateGym(1));
        System.out.println(gymOwnerService.deleteGym(1));
    }

}
