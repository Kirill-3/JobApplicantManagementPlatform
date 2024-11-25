package com.team14.clientProject.profilePage;

public class applicantPreferences {
    private int applicationId;
    private boolean subscribeToNewsletter;
    private boolean subscribeToBulletins;
    private boolean subscribeToJobUpdates;

    public applicantPreferences(int applicationId, boolean subscribeToNewsletter, boolean subscribeToBulletins, boolean subscribeToJobUpdates) {
        this.applicationId = applicationId;
        this.subscribeToNewsletter = subscribeToNewsletter;
        this.subscribeToBulletins = subscribeToBulletins;
        this.subscribeToJobUpdates = subscribeToJobUpdates;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public boolean isSubscribeToNewsletter() {
        return subscribeToNewsletter;
    }

    public boolean isSubscribeToBulletins() {
        return subscribeToBulletins;
    }

    public boolean isSubscribeToJobUpdates() {
        return subscribeToJobUpdates;
    }
}
