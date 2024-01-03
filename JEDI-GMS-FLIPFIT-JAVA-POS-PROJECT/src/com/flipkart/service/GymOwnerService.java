package com.flipkart.service;

public class GymOwnerService {
    public void createGym(){
        System.out.println("Gym Created");
    }

    public Boolean updateGym(int id){
        System.out.println("Gym Updated");
        return true;
    }

    public void listGym(int id){
        System.out.println("Gym Details");
    }

    public Boolean deleteGym(int id){
        System.out.println("Gym Deleted");
        return true;
    }
}
