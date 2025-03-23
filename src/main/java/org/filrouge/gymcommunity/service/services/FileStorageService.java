package org.filrouge.gymcommunity.service.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import java.io.InputStream;

@Service
public class FileStorageService {

    private final Path rootLocation = Paths.get("uploads");

    public FileStorageService() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage directory", e);
        }
    }

    // Save a file and return the generated file name
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path destinationFile = rootLocation.resolve(fileName).normalize().toAbsolutePath();
            Files.copy(file.getInputStream(), destinationFile);

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    // Copy a default file from resources to the uploads directory
    public String copyDefaultFile(String defaultFileName) {
        try {
            // Load the default file from the classpath
            ClassPathResource resource = new ClassPathResource("default-images/" + defaultFileName);
            if (!resource.exists()) {
                throw new RuntimeException("Default file not found: default-images/" + defaultFileName);
            }

            // Generate a unique file name for the copied file
            String fileName = UUID.randomUUID() + "_" + defaultFileName;
            Path destinationFile = rootLocation.resolve(fileName).normalize().toAbsolutePath();

            // Copy the file to the uploads directory
            try (InputStream inputStream = resource.getInputStream()) {
                Files.copy(inputStream, destinationFile);
            }

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to copy default file.", e);
        }
    }

    // Load a file as a resource
    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }
}