package com.cupme.service.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssetFilesService {

    private final Logger log = LoggerFactory.getLogger(AssetFilesService.class);

    public String getFile(String path) {
        // Use the class loader to load the file from within the JAR

        String fullPath = "static/" + path;
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fullPath)) {
            if (inputStream == null) {
                log.debug("File not found: " + fullPath);
                return null;
            }

            // Read the content of the file
            byte[] fileContent = IOUtils.toByteArray(inputStream);

            // Encode the file content to Base64
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            return encodedString;
        } catch (IOException e) {
            log.error("Error reading file: " + path, e);
            return null;
        }
    }
}
