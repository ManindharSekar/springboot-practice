package com.example.FileUploadAndDownloadAPIMySql.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.FileUploadAndDownloadAPIMySql.entity.FileData;
import com.example.FileUploadAndDownloadAPIMySql.repository.FileRepository;

@Service
public class FileService {
	
	private FileRepository fileRepository;

	public FileService(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}
	
	
	public FileData uploadFile(MultipartFile file) throws IOException{
		
		FileData fileData = new FileData();
		fileData.setFileName(file.getOriginalFilename());
		fileData.setFileType(file.getContentType());
		fileData.setFileData(file.getBytes());
		
		return fileRepository.save(fileData);
	}
	
	
	public Optional<FileData> downloadFile(Long id) {
		return fileRepository.findById(id);
	}

}
