package com.team14.clientProject.homePage;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HomePageService {
    List<Applicants> get10MostRecentProfiles();

    Applicants getApplicantById(int id);
}
