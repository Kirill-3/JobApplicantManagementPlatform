package com.team14.clientProject.profilePage;
import java.util.List;

public interface ProfilePageRepository {
    List<Profile> getProfiles();
    Profile getProfileById(int id);
    void addProfile(Profile profile);
    List<Profile> getProfilesByFirstNameAscending();
    List<Profile> getProfilesByFirstNameDescending();
    List<Profile> getProfilesByLastNameAscending();
    List<Profile> getProfilesByLastNameDescending();
    List<Profile> getProfilesByUniqueLocation();
    List<Profile> getProfilesByUniqueEvent();
    List<String> getUniqueSkills();
    void updateProfile(Profile profile);
}
