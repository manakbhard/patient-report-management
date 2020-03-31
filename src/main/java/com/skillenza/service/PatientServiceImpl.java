package com.skillenza.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import com.skillenza.configuration.FileStorageProperties;
import com.skillenza.dao.PatientDao;
import com.skillenza.exception.FileStorageException;
import com.skillenza.exception.MyFileNotFoundException;
import com.skillenza.model.Patient;
import com.skillenza.model.PatientLogin;



public class PatientServiceImpl  implements PatientService {

	private final Path fileStorageLocation;
	  @Autowired
	  public PatientDao patientDao;
	  
	  @Autowired
	    public PatientServiceImpl(FileStorageProperties fileStorageProperties) throws FileStorageException {
	        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
	                .toAbsolutePath().normalize();

	        try {
	            Files.createDirectories(this.fileStorageLocation);
	        } catch (Exception ex) {
	            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
	        }
	    }

	  public int register(Patient patient) {
	    return patientDao.register(patient);
	  }

	  public Patient validateUser(PatientLogin patientLogin) {
	    return patientDao.validateUser(patientLogin);
	  }

	public String storeFile(MultipartFile file) throws FileStorageException {
		// TODO Auto-generated method stub
		// Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        List<String> detectedTextValues =new ArrayList<String>();

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            
            if(!file.getContentType().equalsIgnoreCase("image/png")) {
                throw new FileStorageException("Sorry! Filetype is not supported by the api " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
		
//			AmazonTextract client = AmazonTextractClientBuilder.defaultClient();
//
//			DetectDocumentTextRequest request = new DetectDocumentTextRequest()
//			        .withDocument(new Document()
//			                .withBytes(imageBytes));
//
//
//			DetectDocumentTextResult result = client.detectDocumentText(request);
//			System.out.println(result);
//			
//			ObjectMapper mapper = new ObjectMapper();
//			DetectTextModel test=extractObject(result, DetectTextModel.class);
//			//System.out.println(test);
////			List<DetectedTextBlock> arr = mapper.readValue(result.toString(), new TypeReference<List<DetectedTextBlock>>(){});
//			List<DetectedTextBlock> arr=test.getBlocks();
//            for (DetectedTextBlock detectedTextBlock:arr) {
//            	//System.out.println(detectedTextBlock.getText());
//            	if(detectedTextBlock.getText()!=null) {
//            		detectedTextValues.add(detectedTextBlock.getText());
//            	}
//            	
//            }
			
            
            //Need to add logic for getting aadhaar values
            
            return "";
        } catch (IOException ex) {
            throw new FileStorageException("Could not detect from file " + fileName + ". Please try again!", ex);
        }
	}
	


    public Resource loadFileAsResource(String fileName) throws MyFileNotFoundException {
        try {
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

	}