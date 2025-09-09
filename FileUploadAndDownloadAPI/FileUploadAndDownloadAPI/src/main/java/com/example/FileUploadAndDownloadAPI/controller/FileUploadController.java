package com.example.FileUploadAndDownloadAPI.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileUploadController {
	
	private static final String UPLOAD_DIR="C:/uploads/";
	
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		
		try {
		File dir = new File(UPLOAD_DIR);
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		String filePath=UPLOAD_DIR+file.getOriginalFilename();
		file.transferTo(new File(filePath));
		
		return ResponseEntity.ok("File Upload Successfully: "+file.getOriginalFilename());
		}catch(IOException e) {
			return ResponseEntity.status(500).body("File Upload Failed"+e.getMessage());
		}
	}

}
