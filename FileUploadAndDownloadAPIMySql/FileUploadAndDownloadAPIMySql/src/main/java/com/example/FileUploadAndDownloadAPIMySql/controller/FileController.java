package com.example.FileUploadAndDownloadAPIMySql.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.FileUploadAndDownloadAPIMySql.entity.FileData;
import com.example.FileUploadAndDownloadAPIMySql.service.FileService;


@RestController
@RequestMapping("/files")
public class FileController {
	
private FileService fileService;

public FileController(FileService fileService) {
	this.fileService = fileService;
}

@PostMapping("/upload")
public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
	
	try {
	FileData savedFile = fileService.uploadFile(file);
	return ResponseEntity.ok("File Upload Successfully Id:"+savedFile.getId());
	}catch(Exception e) {
		return ResponseEntity.status(500).body("File upload Failed "+e.getMessage());
	}
}

@GetMapping("/download/{id}")
public ResponseEntity<?> downLoadFile(@PathVariable Long id){
	return fileService.downloadFile(id)
			.map(fileData ->ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(fileData.getFileType()))
					.header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,"attachement;filename=\""+fileData.getFileName()+"\"")
					.body(new ByteArrayResource(fileData.getFileData())))
			.orElseGet(()-> ResponseEntity.notFound().build());
					
}

	
	

}
