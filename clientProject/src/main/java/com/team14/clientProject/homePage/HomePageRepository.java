package com.team14.clientProject.homePage;
import java.util.List;
import com.team14.clientProject.profilePage.Profile;

public interface HomePageRepository {
    List<Applicants> get10MostRecentProfiles();
}
