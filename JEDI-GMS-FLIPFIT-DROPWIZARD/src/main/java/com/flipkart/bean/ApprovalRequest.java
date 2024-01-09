package com.flipkart.bean;

public class ApprovalRequest {
    private String id;
    private int choice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public ApprovalRequest(String id, int choice) {
        this.id = id;
        this.choice = choice;
    }
    public ApprovalRequest(){

    }
}
