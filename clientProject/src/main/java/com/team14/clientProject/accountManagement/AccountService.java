package com.team14.clientProject.accountManagement;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AccountService {

    private static final String picture_path = "src/main/resources/static/images/profile-pictures/";
    private static final String default_picture = "default.jpg";

    public void updateProfilePicture(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Empty File...");
        }

        Path filePath = Paths.get(picture_path, default_picture);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());
    }

    public String getProfilePicturePath() {
        return "/images/profile-pictures/" + default_picture;
    }
}