package com.team14.clientProject.accountManagement;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AccountService {

    private static final String PICTURE_PATH = "uploads/images/profile-pictures/";

    public void updateProfilePicture(MultipartFile file, int userId) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Empty File...");
        }

        String fileName = "user_" + userId + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(PICTURE_PATH, fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());
    }

    public String getProfilePicturePath(int userId) {
        try {
            return Files.list(Paths.get(PICTURE_PATH))
                    .filter(path -> path.getFileName().toString().startsWith("user_" + userId + "_"))
                    .map(path -> "/uploads/images/profile-pictures/" + path.getFileName().toString())
                    .findFirst()
                    .orElse("/images/default.jpg");
        } catch (IOException e) {
            return "/images/default.jpg";
        }
    }
}