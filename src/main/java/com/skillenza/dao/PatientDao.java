package com.skillenza.dao;

import com.skillenza.model.Patient;
import com.skillenza.model.PatientLogin;

public interface PatientDao {
	
	int register(Patient patient);

	  Patient validateUser(PatientLogin patientLogin);

}
