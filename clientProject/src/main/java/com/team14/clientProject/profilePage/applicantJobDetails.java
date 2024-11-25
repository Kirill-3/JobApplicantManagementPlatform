package com.team14.clientProject.profilePage;

public class applicantJobDetails {
    private int applicationID;
    private String currentPosition;
    private String status;

    public applicantJobDetails(int applicationID, String currentPosition, String status) {
        this.applicationID = applicationID;
        this.currentPosition = currentPosition;
        this.status = status;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public String getStatus() {
        return status;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
