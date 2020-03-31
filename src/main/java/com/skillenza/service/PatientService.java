package com.skillenza.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.skillenza.exception.FileStorageException;
import com.skillenza.exception.MyFileNotFoundException;
import com.skillenza.model.Patient;
import com.skillenza.model.PatientLogin;


public interface PatientService {

  int register(Patient patient);

  Patient validateUser(PatientLogin patientLogin);

String storeFile(MultipartFile file) throws FileStorageException;
 Resource loadFileAsResource(String fileName) throws MyFileNotFoundException;
}