package com.team14.clientProject.profilePage;
import java.util.List;

public interface ProfilePageRepository {
    List<Profile> getProfiles();
    Profile getProfileById(int id);
    void addProfile(Profile profile);
}
