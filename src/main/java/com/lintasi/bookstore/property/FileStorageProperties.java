package com.lintasi.bookstore.property;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.lintasi.bookstore.exception.FileStorageException;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	
	private String uploadProfile;
	private String uploadCover;
	private String uploadGenre;
	
	public String getUploadDir(String type) {
		String dir = "";
		if(type.equalsIgnoreCase("profile")) {
			dir = getUploadProfile();
		}else if(type.equalsIgnoreCase("genre")) {
			dir = getUploadGenre();
		}else if(type.equalsIgnoreCase("cover")) {
			dir = getUploadCover();
		}
		
		try {
			Path fileStorageLocationx =  Paths.get(dir).toAbsolutePath().normalize();
			if(!Files.exists(fileStorageLocationx)) {
				Files.createDirectories(fileStorageLocationx);
			}
		} catch (Exception ex) {
			throw new FileStorageException(
					"Could not create the directory where the uploaded files will be stored.", ex);
		}
		
		return dir;
	}
	
	public String getUploadProfile() {
		return uploadProfile;
	}
	public void setUploadProfile(String uploadProfile) {
		this.uploadProfile = uploadProfile;
	}
	public String getUploadCover() {
		return uploadCover;
	}
	public void setUploadCover(String uploadCover) {
		this.uploadCover = uploadCover;
	}
	public String getUploadGenre() {
		return uploadGenre;
	}
	public void setUploadGenre(String uploadGenre) {
		this.uploadGenre = uploadGenre;
	}

}
