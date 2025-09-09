package com.example.FileUploadAndDownloadAPIMySql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FileUploadAndDownloadAPIMySql.entity.FileData;

public interface FileRepository extends JpaRepository<FileData,Long> {

}
