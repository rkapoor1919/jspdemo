
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<h2>Patient Table</h2>
	<hr>
	<br />

	<table border="1">

		<tr>
			<th>ID</th>
			<th>ACTIVE</th>
			<th>NAME</th>
			<th>GENDER</th>
			<th>BIRTHDATE</th>
			<th>ADDRESS</th>
			<th>EMAIL</th>
			<th>PHONE</th>
			<th>GENERAL PRACTITIONER</th>
		</tr>

		<c:forEach var="tempPatient" items="${patient_list}">

			<tr>
				<td>${tempPatient.id}</td>
				<td>${tempPatient.active}</td>
				<td>${tempPatient.name}</td>
				<td>${tempPatient.gender}</td>
				<td>${tempPatient.birthDate}</td>
				<td>${tempPatient.address}</td>
				<td>${tempPatient.email}</td>
				<td>${tempPatient.phone}</td>
				<td>${tempPatient.generalPractitioner}</td>
			</tr>
		</c:forEach>


	</table>

</body>
</html>






