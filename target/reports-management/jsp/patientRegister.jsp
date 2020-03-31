<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<form:form id="regForm" modelAttribute="patient" action="registerProcess"
		method="post">

		<table align="center">
			<tr>
				<td><form:label path="userName">Username</form:label></td>
				<td><form:password path="userName" name="userName" id="userName" /></td>
			</tr>
			
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password" name="password" id="password" /></td>
			</tr>
			<tr>
				<td><form:label path="patientName">Name</form:label></td>
				<td><form:input path="patientName" name="patientName" id="patientName" /></td>
			</tr>
			<tr>
				<td><form:label path="patientEmail">Email</form:label></td>
				<td><form:input path="patientEmail" name="patientEmail" id="patientEmail" /></td>
			</tr>
			<tr>
				<td><form:label path="patientAddress">Address</form:label></td>
				<td><form:input path="patientAddress" name="patientAddress" id="patientAddress" /></td>
			</tr>
			<tr>
				<td><form:label path="patientPhone">Phone</form:label></td>
				<td><form:input path="patientPhone" name="patientPhone" id="patientPhone" /></td>
			</tr>
			
			<tr>
				<td><form:label path="patientAdharNumber">Adhar Card Number</form:label></td>
				<td><form:input path="patientAdharNumber" name="patientAdharNumber" id="patientAdharNumber" /></td>
			</tr>
			
			<tr>
				<td><form:label path="patientPanNumber">PAN Card Number</form:label></td>
				<td><form:input path="patientPanNumber" name="patientPanNumber" id="patientPanNumber" /></td>
			</tr>
			<tr>
				<td></td>
				<td><form:button id="patientRegister" name="patientRegister">Register</form:button></td>
			</tr>
			<tr></tr>
			<tr>
				<td></td>
				<td><a href="index.jsp">Home</a></td>
			</tr>
		</table>
	</form:form>

</body>
</html>