package com.example.FileUploadAndDownloadAPI.controller;

import java.net.MalformedURLException;
import java.net.http.HttpHeaders;
import java.nio.charset.MalformedInputException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class FileDownloadController {
	
	
	private static final String UPLOAD_DIR="C:/uploads/";
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName){
		
		try {
			Path filePath=Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
			Resource resource=new UrlResource(filePath.toUri());
			if(!resource.exists()) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\""+resource.getFilename()+"\"").body(resource);
			
		}catch(MalformedURLException e) {
			return ResponseEntity.badRequest().build();
			
		}
	}

}
