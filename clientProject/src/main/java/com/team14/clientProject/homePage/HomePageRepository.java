package com.team14.clientProject.homePage;
import java.util.List;

public interface HomePageRepository {
    List<Applicants> get10MostRecentProfiles();

    Applicants getApplicantById(int id);
}
