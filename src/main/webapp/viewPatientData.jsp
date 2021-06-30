
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link href="<c:url value="/resource/css/B1View.css" />" rel="stylesheet">
</head>
<body>
	<h1>Patient Details</h1>
	
		<form name="backForm" method="post" action="index.html">

		<input type="submit" value="BACK" />

	</form>
	<hr>
	<br />

	<table class="styled-table" border="1">
		<thead>
			<tr>
				<th style='font-weight: bold'>ID</th>
				<th>ACTIVE</th>
				<th>NAME</th>
				<th>GENDER</th>
				<th>BIRTHDATE</th>
				<th>ADDRESS</th>
				<th>EMAIL</th>
				<th>PHONE</th>
				<th>GENERAL PRACTITIONER</th>
			</tr>
		</thead>
		<c:forEach var="tempPatient" items="${patient_list}">

			<tr>
				<td>${tempPatient.id}</td>
				<td>${tempPatient.active}</td>
				<td>${tempPatient.fname} ${tempPatient.gname}</td>
				<td>${tempPatient.gender}</td>
				<td>${tempPatient.birthDate}</td>
				<td>${tempPatient.address} ${tempPatient.city} ${tempPatient.state}-${tempPatient.postal} ${tempPatient.country}</td>
				<td>${tempPatient.email}</td>
				<td>${tempPatient.phone}</td>
				<td>${tempPatient.generalPractitioner}</td>
				
				<td>
					<form name="editDeletePatientForm" action="editPatientData.jsp">
						<input type="submit" value="EDIT" /> <input type="hidden"
							name="id" value="${tempPatient.id}" />
							<input type="hidden" name="idUse" value="${tempPatient.idUse}" /> 
							<input type="hidden" name="idSystem" value="${tempPatient.idSystem}" /> 
							<input type="hidden" name="idValue" value="${tempPatient.idValue}" /> 
							<input type="hidden" name="active" value="${tempPatient.active}" /> 
							<input type="hidden" name="fname" value="${tempPatient.fname}" /> <input
							type="hidden" name="gname" value="${tempPatient.gname}" /> <input
							type="hidden" name="gender" value="${tempPatient.gender}" /> <input
							type="hidden" name="birthDate" value="${tempPatient.birthDate}" />
						<input type="hidden" name="address" value="${tempPatient.address}" />
						<input type="hidden" name="city" value="${tempPatient.city}" /> <input
							type="hidden" name="state" value="${tempPatient.state}" /> <input
							type="hidden" name="postal" value="${tempPatient.postal}" /> <input
							type="hidden" name="country" value="${tempPatient.country}" /> <input
							type="hidden" name="email" value="${tempPatient.email}" /> <input
							type="hidden" name="phone" value="${tempPatient.phone}" /> <input
							type="hidden" name="generalPractitioner"
							value="${tempPatient.generalPractitioner}" />
					</form>

				</td>
				<td>
					<form name="editDeletePatientForm" action="deleteResource.jsp">
						<input type="submit" value="DELETE" /> <input type="hidden"
							name="id" value="${tempPatient.id}" />
					</form>

				</td>
			</tr>

		</c:forEach>


	</table>

</body>
</html>






