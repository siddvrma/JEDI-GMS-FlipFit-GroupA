package com.flipkart.controller;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;
import com.flipkart.business.CustomerService;

import com.flipkart.bean.Customer;
import com.flipkart.business.CustomerServiceInterface;
import com.flipkart.utils.InputSlot;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {
    CustomerServiceInterface customerService = new CustomerService();


    //Customer login
    @GET
    @Path("/login")
    public Response customerLogin(@QueryParam("userName") String userName, @QueryParam("password") String password) {
        try{
            if (customerService.isUserValid(userName, password)) {
                System.out.println("Login Successful");
                return getCustomerProfile(userName);
            } else {
                System.out.println("Login Failed for " + userName);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }catch (Exception exception){
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }

    }

    //View profile
    @GET
    @Path("/view-profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerProfile(@QueryParam("userName") String userName){
        Customer customerProfile = customerService.viewMyProfile(userName);
        System.out.println("Rahul-Customer"+customerProfile);
        try{
            return Response.ok(customerProfile).build();
        }catch (Exception exception){
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }
    }
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response customerRegister(@QueryParam("userId") String userId,@QueryParam("userName") String userName,@QueryParam("email") String email,@QueryParam("password") String password,@QueryParam("customerPhone") String customerPhone,@QueryParam("cardDetails") String cardDetails){
        Customer customer=new Customer(userId,userName,email,password,customerPhone,cardDetails);
        Customer customerProfile = customerService.registerCustomer(customer);
        if(customerProfile == null){
            return Response.notModified().build();
        }
        return Response.ok(customerProfile).build();
    }


    //get centres by City
    @GET
    @Path("/booking/get-centres")
    public Response getCentresByCity(@QueryParam("city") String city){
        try{
            List<GymCentre> gymCentreList = customerService.getAllGymCenterDetailsByCity(city);
            return Response.ok(gymCentreList).build();
        }catch(Exception exception){
            exception.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        }
    }


    //get slots by Centre
    @GET
    @Path("/booking/get-slots-by-centre")
    public Response getSlotsByCity(@QueryParam("centreId") String centreId,@QueryParam("date") String dateStr){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            Date date = dateFormat.parse(dateStr);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            List<Slot> slots =  customerService.getAvailableSlots(centreId,sqlDate);
            return Response.ok(slots).build();
        }catch (Exception exception){
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }

    //book a slot
    @POST
    @Path("/booking/slot")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response bookSlot(InputSlot slot){
        System.out.println("\n\n\n\n"+ slot.getSlotId()+slot.getDate());
        boolean slotBooked = customerService.bookSlot(slot.getUserID(),slot.getDate(),slot.getSlotId(),slot.getCentreId());
        if(slotBooked) return Response.ok("Slot Booked Successfully").build();
        else return Response.ok("Unable to book slot. Either there is no availability or you have an existing booking at same time").build();
    }

    @DELETE
    @Path("/cancel-booking")
    public Response cancelBooking(@QueryParam("bookingId") String bookingId){
        try {
            CustomerServiceInterface customerService = new CustomerService();
            customerService.cancelBookingbyID(bookingId);
            return Response.ok("Booking Cancelled Successfully").build();
        }catch (Exception exception){
            return Response.status(400).entity("Something Went Wrong!").build();
        }
    }

    @GET
    @Path("/booking/get-my-booking")
    public Response getCustomerBookings(@QueryParam("customerId") String customerId){
        try {
            CustomerServiceInterface customerService = new CustomerService();
            return Response.ok(customerService.getCustomerPlan(customerId)).build();
        }catch (Exception exception){
            return Response.status(400).entity("Something Went Wrong!").build();
        }
    }
}