package com.flipkart.utils;

import com.flipkart.bean.GymCentre;

import java.util.List;

import static com.flipkart.constant.Constants.*;
import static com.flipkart.constant.Constants.DASHED_LINE;

public class util {

    public static void printList(Iterable<?> list) {
        for (Object element : list) {
            System.out.println(element);
        }
    }
    public static void printGymCentres(List<GymCentre> gymCentres){
        System.out.println(DASHED_LINE);
        System.out.printf(YELLOW_COLOR + "%-8s\t", "CENTRE-ID");
        System.out.printf("%-8s\t", "NAME");
        System.out.printf("%-6s\t", "CITY");
        System.out.printf("%12s\t", "OWNER-ID");
        System.out.printf("%8s\t", "CAPACITY");
        System.out.printf("%-8s\t\n", "IS-APPROVED" + RESET_COLOR);
        System.out.println(DASHED_LINE);
        System.out.println("");
        for(GymCentre gymCentre: gymCentres) {
            System.out.printf("%-8s\t", gymCentre.getGymCentreID());
            System.out.printf("%-8s\t", gymCentre.getGymCenterName());
            System.out.printf("%-8s\t", gymCentre.getCity());
            System.out.printf("%-8s\t", gymCentre.getOwnerID());
            System.out.printf("%-8s\t", gymCentre.getCapacity());
            if(gymCentre.isApproved() == 0) System.out.println("No\n");
            else if(gymCentre.isApproved() == 1) System.out.println("Yes\n");
            else System.out.println("Pending\n");
        }
        System.out.println(DASHED_LINE);
    }
}
