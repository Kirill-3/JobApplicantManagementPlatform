package com.team14.clientProject.profilePage;

public class applicantPreferences {
    private int id;
    private boolean subscribeToNewsletter;
    private boolean subscribeToBulletins;
    private boolean subscribeToJobUpdates;

    public applicantPreferences(int id, boolean subscribeToNewsletter, boolean subscribeToBulletins, boolean subscribeToJobUpdates) {
        this.id = id;
        this.subscribeToNewsletter = subscribeToNewsletter;
        this.subscribeToBulletins = subscribeToBulletins;
        this.subscribeToJobUpdates = subscribeToJobUpdates;
    }

    public int getId() {
        return id;
    }

    public boolean isSubscribeToNewsletter() {
        return subscribeToNewsletter;
    }

    public void setSubscribeToNewsletter(boolean subscribeToNewsletter) {
        this.subscribeToNewsletter = subscribeToNewsletter;
    }

    public boolean isSubscribeToBulletins() {
        return subscribeToBulletins;
    }

    public void setSubscribeToBulletins(boolean subscribeToBulletins) {
        this.subscribeToBulletins = subscribeToBulletins;
    }

    public boolean isSubscribeToJobUpdates() {
        return subscribeToJobUpdates;
    }

    public void setSubscribeToJobUpdates(boolean subscribeToJobUpdates) {
        this.subscribeToJobUpdates = subscribeToJobUpdates;
    }
}