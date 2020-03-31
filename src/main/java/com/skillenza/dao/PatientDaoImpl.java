package com.skillenza.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.skillenza.model.Patient;
import com.skillenza.model.PatientLogin;

public class PatientDaoImpl implements PatientDao {

	  @Autowired
	  DataSource datasource;

	  @Autowired
	  JdbcTemplate jdbcTemplate;

	  public int register(Patient patient) {
		
	    String sql = "insert into patient values(?,?,?,?,?,?,?,?)";

	    return jdbcTemplate.update(sql, new Object[] { patient.getUserName(), patient.getPassword(), patient.getPatientName(),
	        patient.getPatientEmail(), patient.getPatientAddress(), patient.getPatientPhone(),patient.getPatientAdharNumber(),patient.getPatientPanNumber()});
	  }

	  public Patient validateUser(PatientLogin patientLogin) {
	    String sql = "select * from patient where username='" + patientLogin.getUsername() + "' and password='" + patientLogin.getPassword()
	        + "'";
	    List<Patient> users = jdbcTemplate.query(sql, new UserMapper());

	    return users.size() > 0 ? users.get(0) : null;
	  }

	}

	class UserMapper implements RowMapper<Patient> {

	  public Patient mapRow(ResultSet rs, int arg1) throws SQLException {
	    Patient patient = new Patient();
	    patient.setUserName(rs.getString("username"));
	    patient.setPassword(rs.getString("password"));
	    patient.setPatientName(rs.getString("patientName"));
	    patient.setPatientEmail(rs.getString("patientemail"));
	    patient.setPatientAddress(rs.getString("patientaddress"));
	    patient.setPatientPhone(rs.getInt("patientphone"));
	    patient.setPatientAdharNumber(rs.getString("patientadhar_number"));
	    patient.setPatientPanNumber(rs.getString("patientpan_number"));
	  //  patient.setExistingDiesease(rs.getArray("existingDisease"));
	    return patient;
	  }
	}