package com.flipkart.client;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;
import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;
import com.flipkart.business.CustomerService;
import com.flipkart.business.CustomerServiceInterface;
import com.flipkart.exceptions.DataEntryException;
import com.flipkart.utils.UserPlan;
import com.flipkart.utils.util;
import com.flipkart.validators.Validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.flipkart.client.MainApplicationClient.scanner;
import static com.flipkart.constant.Constants.*;
import static com.flipkart.constant.Constants.RESET_COLOR;


public class CustomerClient {
    private CustomerServiceInterface customerService  =  new CustomerService();

    public boolean customerLogin(String userName, String password) {
//        Check if credentials are right
        if (customerService.isUserValid(userName, password)) {
            System.out.println(GREEN_COLOR+"Successfully logged in"+RESET_COLOR);
            customerClientMainPage(userName);
        } else {
            System.out.println(RED_COLOR+"UserName or password doesn't match"+RESET_COLOR);
            return false;
        }
        return true;
    }


    public void register(){
        System.out.println("Enter your UserName");
        String userName = scanner.next();

        System.out.println("Enter your Passkey");
        String password = scanner.next();

        System.out.println("Enter your Email");
        String email = scanner.next();

        System.out.println("Enter your Phone Number");
        String phoneNumber = scanner.next();

        System.out.println("Enter your Card Number");
        String cardNumber = scanner.next();

        customerService.registerCustomer(userName,password,email,phoneNumber,cardNumber);
        customerClientMainPage(userName);
    }

    private void printSlots(List<Slot> slots){
        System.out.println(DASHED_LINE);
        System.out.printf(YELLOW_COLOR + "%-8s\t", "SLOT-ID");
        System.out.printf("%-8s\t\n", "SLOT-TIME" + RESET_COLOR);
        System.out.println(DASHED_LINE);
        for(Slot slot: slots) {
            System.out.printf("%-8s\t", slot.getSlotId());
            System.out.printf("%-8s\t\n", slot.getTime());
        }
        System.out.println(DASHED_LINE);
    }

    private void bookSlotSubMenu(String userName){
        //Get Location for filter
        System.out.println("Provide Location to search :");
        String location = scanner.next();
        List<GymCentre> centreListByLocation = customerService.getAllGymCenterDetailsByCity(location);
        // Print All Centres
        util.printGymCentres(centreListByLocation);
        //Select Gym Centre
        if(centreListByLocation.isEmpty()){
            System.out.println(RED_COLOR +"There are no available GYM Centres in " + location + ". Please Select some other location" + RESET_COLOR);
            customerClientMainPage(userName);
            return;
        }
        System.out.print("Choose a gymCentre ID to proceed:");
        String chosenGym = scanner.next();
        //Select Date
        Date sqlDate = selectDate();
        //Choose Slot
        chooseSlot(chosenGym,userName,sqlDate,chosenGym);
        System.out.println("Booking Successful");
    }

    private Date selectDate(){
        //Select Date
        System.out.print("Enter Date (dd/MM/yyyy): ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date;
        Date sqlDate = null;
        try {
            String dat = scanner.next();
//            if(Validators.isDateValid(dat)){
//                date = sdf.parse(dat);
//                sqlDate = new Date(date.getTime());
//            }
//            else{
//                new DataEntryException();
//                sqlDate = selectDate();
//            }
                date = sdf.parse(dat);
                sqlDate = new Date(date.getTime());
        } catch (ParseException e) {
            throw new DataEntryException();
        }
        return sqlDate;
    }

    private void chooseSlot(String gymCentreId,String userName,Date sqlDate,String centreId){
        System.out.println("Choose from the Below Slots");
        List<Slot> availableSlots = customerService.getAvailableSlots(gymCentreId,sqlDate);
        printSlots(availableSlots);
        if(availableSlots.isEmpty()){
            System.out.println(RED_COLOR +"There are no available slots in the " + gymCentreId + ". Please Select some other gym" + RESET_COLOR);
            customerClientMainPage(userName);
            return;
        }
        System.out.println("Enter SlotID :");
        String slotID = scanner.next();
        //Select Slot to book
        if(!customerService.bookSlot(userName,sqlDate,slotID,centreId)) chooseSlot(gymCentreId, userName, sqlDate,centreId);
    }

    private void printUserPlan(String userName){
        System.out.println("Bookings : ");
        List<UserPlan> allUserPlan= customerService.getCustomerPlan(userName);
        List<Booking> bookingList = customerService.getCustomerBookings(userName);
        System.out.println(DASHED_LINE);
        System.out.printf(YELLOW_COLOR + "%-8s\t", "Centre-ID");
        System.out.printf(YELLOW_COLOR + "%-8s\t", "SLOT-ID");
        System.out.printf(YELLOW_COLOR + "%-8s\t", "DATE");
        System.out.printf(YELLOW_COLOR + "%8s\t", "SLOT-TIME");
        System.out.printf("%-8s\t\n", "SCHEDULE_ID" + RESET_COLOR);
        System.out.println(DASHED_LINE);
        for(UserPlan userPlan: allUserPlan) {
            System.out.printf("%-8s\t", userPlan.getCentreID());
            System.out.printf("%-8s\t", userPlan.getSlotId());
            System.out.printf("%-8s\t", userPlan.getDate());
            System.out.printf("%-8s\t", userPlan.getTime());
            System.out.printf("%-8s\t\n", userPlan.getScheduleID());
        }
        System.out.println(DASHED_LINE);
    }

    private void printbookingsSubMenu(String userName){
        System.out.println("Bookings : ");
        List<Booking> allBookingList= customerService.getCustomerBookings(userName);
        System.out.println(DASHED_LINE);
        System.out.printf(YELLOW_COLOR + "%-8s\t", "BOOKING-ID");
        System.out.printf("%47s\t\n", "SCHEDULE-ID" + RESET_COLOR);
        System.out.println(DASHED_LINE);
        for(Booking booking: allBookingList) {
            System.out.printf("%-8s\t", booking.getBookingID());
            System.out.printf("%-8s\t\n", booking.getScheduleID());
        }
        System.out.println(DASHED_LINE);
    }

    private void cancelBookingSubMenu(String userName){
        printbookingsSubMenu(userName);
        System.out.println("Select the Booking you want to cancel: ");
        String bookingId = scanner.next();
        customerService.cancelBookingbyID(bookingId);
        System.out.println("Booking Cancellation Successful");
    }

    public void printCustomerProfile(Customer customer){
        System.out.println(GREEN_COLOR +"------------------------------------------------------------------------" + RESET_COLOR);
        System.out.println(YELLOW_COLOR + "USER ID: "+ RESET_COLOR + customer.getUserID());
        System.out.println(YELLOW_COLOR + "USER NAME: "+ RESET_COLOR + customer.getUserName());
        System.out.println(YELLOW_COLOR + "EMAIL: "+ RESET_COLOR + customer.getEmail());
        System.out.println(YELLOW_COLOR + "CONTACT: "+ RESET_COLOR + customer.getCustomerPhone());
        System.out.println(YELLOW_COLOR + "CARD DETAILS: "+ RESET_COLOR + customer.getCardDetails());
        System.out.println(GREEN_COLOR +"------------------------------------------------------------------------" + RESET_COLOR);
    }


    public void customerClientMainPage(String userName) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = currentTime.format(myFormat);
        System.out.println(YELLOW_COLOR+"WELCOME "+userName+" !!\nWhat you what to do\nLogin TIME: "+currentTime+RESET_COLOR);
        while(true){
            System.out.println("1. View My Profile \n2. Book a slot in a Gym \n3. View Bookings\n4. Cancel Bookings\n5. Go Back to previous menu");
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    Customer customer= customerService.viewMyProfile(userName);
                    printCustomerProfile(customer);
                    break;
                case 2:
                    bookSlotSubMenu(userName);
                    break;
                case 3:
                    printbookingsSubMenu(userName);
                    //printUserPlan(userName);
                    break;
                case 4:
                    cancelBookingSubMenu(userName);
                    break;
                case 5:
                    System.out.println(PREVIOUS_MENU_MESSAGE);
                    return;
                default:
                    System.out.println(INVALID_CHOICE_ERROR);
                    break;
            }
        }
    }

}
