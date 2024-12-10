package com.team14.clientProject.loggingSystem;

public class SystemLog {
    private int systemLogId;
    private int userId;
    private String actionTaken;
    private String timestamp;
    public SystemLog(int systemLogId, int userId, String actionTaken, String timestamp) {
        this.systemLogId = systemLogId;
        this.userId = userId;
        this.actionTaken = actionTaken;
        this.timestamp = timestamp;
    }

    public int getLogId() {
        return systemLogId;
    }

    public int getUserId() {
        return userId;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
