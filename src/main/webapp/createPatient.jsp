<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link href="<c:url value="/resource/css/B1View.css" />" rel="stylesheet">


<script src="./jquery.min.js"></script>

<script type="text/javascript" src="./fhir-client.js"></script>

<script type="text/javascript" src="./app.js"></script>

</head>
<body>
	<h1>Patient Table</h1>
	<hr>
	<br />
	<form name="createPatientForm" method="post" action="index.html">

		<input type="submit" value="BACK" />

	</form>
	<form name="createPatientForm" method="post"
		action="CreatePatientServlet">
		<table class="styled-table" border="1">
			<thead>
				<tr>
					<th>ID</th>
					<td><input type="text" name="id"/></td>
				</tr>
				<tr>
					<th>USE</th>
					<td>
                     <select name="idUse" >
                         <option value="usual">USUAL</option>
                         <option value="official">OFFICIAL</option>
                         <option value="temp">TEMP</option>
                         <option value="secondary">SECONDARY</option>
                         <option value="old">OLD</option>
                         <option value="null">NULL</option>
                     </select>
  					</td>
				</tr>
				<tr>
					<th>SYSTEM</th>
					<td><input type="text" name="idSystem"/></td>
				</tr>
				<tr>
					<th>VALUE</th>
					<td><input type="text" name="idValue"/></td>
				</tr>
				<tr>
					<th>ACTIVE</th>
					<td><input type="text" name="active"/></td>
				</tr>
				<tr>
					<th>FAMILY NAME</th>
					<td><input type="text" name="fname" /></td>
				</tr>
					<tr>
					<th>GIVEN NAME</th>
					<td><input type="text" name="gname" /></td>
				</tr>
				<tr>
					<th>GENDER</th>
					<td>
                    <select name="gender" >
                         <option value="male">MALE</option>
                         <option value="female">FEMALE</option>
                         <option value="other">OTHER</option>
                         <option value="unknown">UNKNOWN</option>
                     </select>
  					</td>

				</tr>
				<tr>
					<th>BIRTHDATE</th>
					<td><input type="text" name="birthDate" /></td>
				</tr>
				<tr>
					<th>ADDRESS</th>
					<td><input type="text" name="address"  /></td>
				</tr>
				<tr>
					<th>CITY</th>
					<td><input type="text" name="city" /></td>
				</tr>
								<tr>
					<th>STATE</th>
					<td><input type="text" name="state"/></td>
				</tr>
								<tr>
					<th>COUNTRY</th>
					<td><input type="text" name="country" /></td>
				</tr>
								<tr>
					<th>POSTAL</th>
					<td><input type="text" name="postal"  /></td>
				</tr>
				<tr>
					<th>EMAIL</th>
					<td><input type="text" name="email"/></td>
				</tr>
				<tr>
					<th>PHONE</th>
					<td><input type="text" name="phone"/></td>
				</tr>
				<tr>
					<th>GENERAL PRACTITIONER</th>
					<td><input type="text" name="generalPractitioner"/></td>
				</tr>

			</thead>

		</table>
		<input type="submit" value="ADD" /> <br></br>

	</form>
		


	<br></br>
	<form>
			<input type="submit" value="EDIT" /> <br></br>
			<textarea rows="100" cols="100" name="feedback" id='output'>${patient}</textarea>
	</form>
</body>
</html>