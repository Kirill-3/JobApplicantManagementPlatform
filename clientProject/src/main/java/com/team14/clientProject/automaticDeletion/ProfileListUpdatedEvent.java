package com.team14.clientProject.automaticDeletion;

import org.springframework.context.ApplicationEvent;

// Create a custom event that will be published when the list of profiles is updated
public class ProfileListUpdatedEvent extends ApplicationEvent {
    public ProfileListUpdatedEvent(Object source) {
        super(source);
    }
}