package com.flipkart.controller;

import com.codahale.metrics.annotation.Timed;
import com.flipkart.bean.Admin;
import com.flipkart.bean.ApprovalRequest;
import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;
import com.flipkart.business.AdminService;
import com.flipkart.business.GymOwnerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminController {

    private static AdminService adminService = new AdminService();
    private static GymOwnerService gymOwnerService = new GymOwnerService();

    public Admin isUserValid(String userName, String password) {
        Admin admin = new Admin();
        if (userName.equals(admin.getUserName()) && password.equals(admin.getPassword())) {
            return admin;
        }
        return null;
    }


    @GET
    @Path("/login")
    @Timed
    public Response customerLogin(@QueryParam("userName") String userName, @QueryParam("password") String password) {
        Admin admin = null;
        try{
            admin = isUserValid(userName, password);
            System.out.println("Login Successful");
            return Response.ok(admin).build();
        }catch (Exception exception){
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }
    }

    @GET
    @Path("/gym-owner/pending-list")
    @Timed
    public Response viewPendingGymOwners() {
        try{
            List<GymOwner> gymOwnerList = adminService.viewPendingGymOwners();
            return Response.ok(gymOwnerList).build();
        } catch (Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/gym-owner/all")
    @Timed
    public Response viewGymOwners(){
        try{
            List<GymOwner> gymOwnerList = gymOwnerService.viewAllGymOwners();
            return Response.ok(gymOwnerList).build();
        } catch (Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/gym-centre/pending-list")
    @Timed
    public Response viewPendingGymCentres(){
        try{
            List<GymCentre> gymCentreList = adminService.viewPendingGymCentres();
            return Response.ok(gymCentreList).build();
        } catch (Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/gym-owner/handle")
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handleGymOwner(ApprovalRequest approvalRequest){
        try{
            adminService.approveGymOwner(approvalRequest.getId(),approvalRequest.getChoice());
            return Response.ok("Request Handled").build();
        } catch (Exception e){
            return Response.status(400).entity("Invalid Gym Owner details").build();
        }

    }

    @POST
    @Path("/gym-centre/handle")
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handleGymCentre(ApprovalRequest approvalRequest){
        try{
            adminService.approveGymCenter(approvalRequest.getId(),approvalRequest.getChoice());
            return Response.ok("Request Handled").build();
        } catch (Exception e){
            return Response.status(400).entity("Invalid Gym Centre details").build();
        }

    }
}