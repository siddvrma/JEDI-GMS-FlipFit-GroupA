package com.flipkart.DAOLocal;

import com.flipkart.bean.GymCentre;

import java.util.ArrayList;
import java.util.List;

public class GymCentreDAO implements GymCentreInterfaceDAO {

    private static List<GymCentre> allGymCentres = new ArrayList<>();

    public List<GymCentre> getAllCentresByOwnerId(String ownerId) {
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
        return null;
    }

    public void addGymCentre(GymCentre centre) {
        //String gymCentreId = generateUniqueGymCentreId(centre.getGymCenterName());
        String gymCentreId = centre.getGymCentreID();
        centre.setGymCentreID(gymCentreId);
        allGymCentres.add(centre);
    }

    public List<GymCentre> getPendingGymCentreList() {
        List<GymCentre> pendingList = new ArrayList<>();
        for (GymCentre gymCentre : allGymCentres) {
            if (gymCentre.isApproved()==2) {
                pendingList.add(gymCentre);
            }
        }
        return pendingList;
    }

    public void validateGymCentre(String gymCentreId, int isApproved) {
        for (GymCentre gymCentre : allGymCentres) {
            if (gymCentre.getGymCentreID().equals(gymCentreId)) {
                gymCentre.setApproved(isApproved);
                break;
            }
        }
    }

    public void sendCentreApprovalRequest(String gymCentreId) {
        for (GymCentre gymCentre : allGymCentres) {
            if (gymCentre.getGymCentreID().equals(gymCentreId)) {
                gymCentre.setApproved(2);
                System.out.println("Sending gym centre approval request for ID: " + gymCentreId);
                break;
            }
        }
    }

    public List<GymCentre> getGymCentreListByCity(String city) {
        List<GymCentre> allCentreByCity = new ArrayList<>();
        for (GymCentre gymCentre : allGymCentres) {
            if (gymCentre.getCity().equals(city)) {
                allCentreByCity.add(gymCentre);
            }
        }
        return allCentreByCity;
    }

    private String generateUniqueGymCentreId(String centreName) {
        // Implement logic to generate a unique gym centre ID based on your requirements
        return centreName + System.currentTimeMillis();
    }
}
