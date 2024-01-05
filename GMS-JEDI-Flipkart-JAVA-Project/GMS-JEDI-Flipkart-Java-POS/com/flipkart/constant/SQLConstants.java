package com.flipkart.constant;

public class SQLConstants {

    // ------------------------ GYM OWNER ------------------------
    public static final String FETCH_ALL_GYM_OWNERS_QUERY = "SELECT * FROM FlipFit.GymOwner";
    public static final String FETCH_ALL_PENDING_GYM_OWNERS_QUERY = "SELECT * FROM FlipFit.GymOwner where isApproved = 2";
    public static final String SEND_GYM_OWNER_APPROVAL_REQ_QUERY = "UPDATE FlipFit.GymOwner SET isApproved = 2 WHERE Id =?;";

    public static final String ADD_GYM_CENTRE_QUERY = "INSERT INTO FlipFit.GymCentre (centreId, ownerId, centreName, gstin, city, capacity, price, isApproved) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
//    public static final String ADD_GYM_CENTRE_QUERY = "INSERT INTO FlipFit.GymCentre (centreId, ownerId, centreName, gstin, city, capacity, price, isApproved) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";



    //  ------------ GYM OWNER ------------
    public static final String REGISTER_GYM_OWNER = "Insert into GymOwner values (?,?,?,?,?,?,?)";
    public static final String LOGIN_GYM_OWNER = "Select * from GymOwner where name=? and password=?";
    public static final String SQL_APPROVE_GYM_OWNER_BY_ID_QUERY="Update GymOwner Set isApproved=? WHERE Id=?";
//    public static final String SQL_APPROVE_GYM_OWNER_BY_ID_QUERY ="Update GymOwner Set isApproved=? WHERE Id=?";


    // ------------------------ GYM CENTRE ------------------------
    public static final String FETCH_GYM_CENTRES_BY_OWNER_ID = "SELECT * FROM FlipFit.GymCentre where ownerId = ?";
    public static final String FETCH_ALL_PENDING_GYM_CENTRES_QUERY = "SELECT * FROM FlipFit.GymCentre where isApproved = 2";
    public static final String SQL_APPROVE_GYM_CENTRE_BY_ID_QUERY="Update GymCentre Set isApproved=? WHERE centreId=?";
    public static final String FETCH_GYM_CENTRES_BY_CITY = "SELECT * FROM FlipFit.GymCentre where city = ?";

    public static final String FETCH_GYM_CENTRE_BY_ID = "SELECT * FROM FlipFit.GymCentre where centreId = ?";


//    public static final String SQL_APPROVE_GYM_CENTRE_BY_ID_QUERY = "Update GymCentre Set isApproved=? WHERE centreId=?";



    // ------------------------ CUSTOMER ------------------------
//    public static final String ADD_NEW_CUSTOMER = "INSERT INTO FlipFit.GymCentre (userId,userName,password,email,phoneNumber,cardNumber) VALUES (?, ?, ?, ?, ?, ?);";






    //    ------- Customer ---------------
    public static final String CUSTOMER_LOGIN_QUERY = "SELECT * FROM FlipFit.Customer WHERE name = ? AND password = ?";
    public static final String ADD_NEW_CUSTOMER = "INSERT INTO FlipFit.Customer (Id,name,password,email,phone,cardDetails) VALUES (?, ?, ?, ?, ?, ?);";
    public static final String GET_CUSTOMER_BY_ID = "SELECT * FROM FlipFit.Customer WHERE name = ?;";


    //  ----------- Booking -----------
    public static final String GET_BOOKING_BY_CUSTOMER_ID ="Select * From FlipFit.Booking where userID = ?";
    public static final String CANCEL_BOOKING_BY_ID= "Delete from FlipFit.Booking where bookingId = ?";
    public static final String ADD_BOOKING= "INSERT INTO FlipFit.Booking (bookingId, userID, scheduleID) values( ?, ?, ?)";

    // ------------- Schedule -------------
    public static final String ADD_SCHEDULE="INSERT INTO FlipFit.Schedule(scheduleId, date, slotId, availability ) values (?,?,?,?)";
    public static final String GET_SCHEDULES_BY_DATE="SELECT * FROM FlipFit.Schedule WHERE date=?";
    public static final String GET_SCHEDULE_BY_ID ="SELECT * FROM FlipFit.Schedule WHERE scheduleId=?";
    public static final String MODIFY_SCHEDULE_AVAILABILITY ="UPDATE `flipfit`.`schedule` SET availability = ? WHERE (`scheduleId` = ?)";

    // ---------------- Slot ----------------
    public static final String FETCH_ALL_SLOTS ="SELECT * FROM FlipFit.Slot";
    public static final String FETCH_SLOT_BY_CENTRE ="SELECT * FROM FlipFit.Slot WHERE centreId=?";
    public static final String ADD_SLOT ="INSERT INTO FlipFit.Slot(slotId, centreId, time) values (?, ?, ?)";
    public static final String FETCH_SLOT_BY_ID = "SELECT * FROM FlipFit.Slot WHERE slotId=?";
    public static final String FETCH_SLOT_BY_ID_AND_CENTRE_ID = "SELECT * FROM FlipFit.Slot WHERE slotId=? AND centreId=?";

    public static final String GET_BOOKING_BY_BOOKING_ID ="Select * From FlipFit.Booking where bookingId = ?";


    public static final String GET_USERPLAN_BY_CUSTOMER_ID = "select * from slot join schedule where slot.slotId=schedule.slotId and schedule.scheduleId=?";
}
