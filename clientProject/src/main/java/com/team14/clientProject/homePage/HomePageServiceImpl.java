package com.team14.clientProject.homePage;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageServiceImpl implements HomePageService {
    private HomePageRepository homePageRepository;

    public HomePageServiceImpl(HomePageRepository homePageRepository) {
        this.homePageRepository = homePageRepository;
    }

    @Override
    public List<Applicants> get10MostRecentProfiles() {
        return homePageRepository.get10MostRecentProfiles();
    }

    @Override
    public Applicants getApplicantById(int id) {
        return homePageRepository.getApplicantById(id);
    }
}
