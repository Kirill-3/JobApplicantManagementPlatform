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

    public AccountService() {
        createDirectories();
    }

    private void createDirectories() {
        try {
            Path path = Paths.get(PICTURE_PATH);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create directories: " + e.getMessage(), e);
        }
    }

    public void updateProfilePicture(MultipartFile file, int userId) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Empty File...");
        }

        deleteOldProfilePicture(userId);

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

    private void deleteOldProfilePicture(int userId) throws IOException {
        Files.list(Paths.get(PICTURE_PATH))
                .filter(path -> path.getFileName().toString().startsWith("user_" + userId + "_"))
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
    public void removeProfilePicture(int userId) throws IOException {
        deleteOldProfilePicture(userId);
    }
}