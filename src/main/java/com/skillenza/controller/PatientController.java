package com.skillenza.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.skillenza.exception.FileStorageException;
import com.skillenza.exception.MyFileNotFoundException;
import com.skillenza.model.Patient;
import com.skillenza.model.PatientLogin;
import com.skillenza.model.UploadFileResponse;
import com.skillenza.service.PatientService;

@Controller
public class PatientController {
	@Autowired
	  public PatientService patientService;
	  @RequestMapping(value = "/patientRegister", method = RequestMethod.GET)
	  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("patientRegister");
	    mav.addObject("patient", new Patient());
	    return mav;
	  }
	  @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("patient") Patient patient) {
	  patientService.register(patient);
	  return new ModelAndView("welcome", "name", patient.getPatientName());
	  }
	  
	  @RequestMapping(value = "/patientLogin", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("patientLogin");
	    mav.addObject("patientLogin", new PatientLogin());

	    return mav;
	  }
	  
	  @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	  public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		    String result = null;
			try {
				result = patientService.storeFile(file);
			} catch (FileStorageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//		                .path("/downloadFile/")
//		                .path(fileName)
//		                .toUriString();

		        return new UploadFileResponse(fileName,
		                file.getContentType(), file.getSize(),result);
		    	//return result;
		    }
	  
	  @RequestMapping(value = "/downloadFile/{fileName:.+}", method = RequestMethod.GET)
	  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
	        // Load file as Resource
	        Resource resource = null;
			try {
				resource = patientService.loadFileAsResource(fileName);
			} catch (MyFileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        // Try to determine file's content type
	        String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	            //Log4JLogger.info("Could not determine file type.");
	        }

	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    }

	  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
	      @ModelAttribute("patientLogin") PatientLogin patientLogin) {
	    ModelAndView mav = null;

	    Patient patient = patientService.validateUser(patientLogin);

	    if (null != patient) {
	      mav = new ModelAndView("welcome");
	      mav.addObject("patientName", patient.getPatientName());
	    } else {
	      mav = new ModelAndView("patientLogin");
	      mav.addObject("message", "Wrong credentials!!");	
	    }

	    return mav;
	  }

}
