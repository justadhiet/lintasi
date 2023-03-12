package com.lintasi.bookstore.service;

import com.lintasi.bookstore.exception.FileStorageException;
import com.lintasi.bookstore.exception.MyFileNotFoundException;
import com.lintasi.bookstore.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

@Service
@Transactional
public class FileStorageService {

    private Path fileStorageLocation;
    private String type;
    @Autowired
    FileStorageProperties fileStorageProperties;

    public String storeFile(MultipartFile file) {
        // Normalize file name
    	this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir(type)).toAbsolutePath().normalize();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            String tfN = generatedString() + "." + getExtensionByStringHandling(fileName);
            System.out.println(tfN);
            Path targetLocation = this.fileStorageLocation.resolve(tfN);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return tfN;
        } catch (Exception ex) {
        	ex.printStackTrace();
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
        	this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir(type)).toAbsolutePath().normalize();
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	private String generatedString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}
	
	public String getExtensionByStringHandling(String filename) {
	    Optional<String> s = Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	    return s.get();
	}
    
}
