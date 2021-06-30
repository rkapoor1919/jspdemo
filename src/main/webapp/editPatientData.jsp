
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<link href="<c:url value="/resource/css/B1View.css" />" rel="stylesheet">


<script src="./jquery.min.js"></script>

<script type="text/javascript" src="./fhir-client.js"></script>

<script type="text/javascript" src="./app.js"></script>


<script type='text/javascript'>
    $(function(){
          $('#idUse').val('${param.idUse}');
          console.log('${param.idUse}');
          $('#genderId').val('${param.gender}');
    });
</script>
</head>
<body>
	<h1>Patient Table</h1>
	<hr>
	<br />
	<form name="backForm" method="post" action="index.html">

		<input type="submit" value="BACK" />

	</form>
	<form name="editDeletePatientForm" method="post"
		action="EditPatientServlet">
		<table class="styled-table" border="1">
			<thead>
				<tr>
					<th>ID</th>
					<td><input type="text" name="id" value="${param.id}"/></td>
				</tr>
				<tr>
					<th>USE</th>
					<td>
                     <select id="idUse" name="idUse" >
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
					<td><input type="text" name="idSystem" value="${param.idSystem} " /></td>
				</tr>
				<tr>
					<th>VALUE</th>
					<td><input type="text" name="idValue" value="${param.idValue} " /></td>
				</tr>
				<tr>
					<th>ACTIVE</th>
					<td><input type="text" name="active" value="${param.active} " /></td>
				</tr>
				<tr>
					<th>FAMILY NAME</th>
					<td><input type="text" name="fname" value="${param.fname}" /></td>
				</tr>
								<tr>
					<th>GIVEN NAME</th>

					
					<td><input type="text" name="gname" value="${param.gname}" /></td>
				</tr>
				<tr>
					<th>GENDER</th>
					<td>
                    <select id="genderId" name="gender" >
                         <option value="male" selected>MALE</option>
                         <option value="female">FEMALE</option>
                         <option value="other">OTHER</option>
                         <option value="unknown">UNKNOWN</option>
                     </select>
  					</td>
				</tr>
				<tr>
					<th>BIRTHDATE</th>
					<td><input type="text" name="birthDate"
						value="${param.birthDate}" /></td>
				</tr>
				<tr>
					<th>ADDRESS</th>
					<td><input type="text" name="address" value="${param.address}" /></td>
				</tr>
				<tr>
					<th>CITY</th>
					<td><input type="text" name="city" value="${param.city}" /></td>
				</tr>
								<tr>
					<th>STATE</th>
					<td><input type="text" name="state" value="${param.state}" /></td>
				</tr>
								<tr>
					<th>COUNTRY</th>
					<td><input type="text" name="country" value="${param.country}" /></td>
				</tr>
								<tr>
					<th>POSTAL</th>
					<td><input type="text" name="postal" value="${param.postal}" /></td>
				</tr>
				<tr>
					<th>EMAIL</th>
					<td><input type="text" name="email" value="${param.email}" /></td>
				</tr>
				<tr>
					<th>PHONE</th>
					<td><input type="text" name="phone" value="${param.phone}" /></td>
				</tr>
				<tr>
					<th>GENERAL PRACTITIONER</th>
					<td><input type="text" name="generalPractitioner"
						value="${param.generalPractitioner}" /></td>
				</tr>

			</thead>

		</table>
		<input type="submit" value="EDIT" /> <br></br>
			<textarea rows="100" cols="100" name="feedback" id='output'></textarea>
	<pre id="output">Loading...</pre>
	</form>
		


	<br></br>
	<script type="text/javascript">
	FHIR.oauth2.ready().then(function(client) {
    	var app = new App(client);
    	app.request('Patient/${param.id}');
	}).catch(createRenderer("output"));
	
</script>
	<script type="text/javascript">

/*
	const updatedPatient = {
			"resourceType": "Patient",
	        "id": "BILIBABY1",
	        "meta": {
	            "versionId": "1",
	            "lastUpdated": "2020-07-15T02:51:23.000+00:00",
	            "source": "#mNKBng6Y74bFyYWP"
	        },
	        "extension": [
	            {
	                "url": "http://hl7.org/fhir/StructureDefinition/patient-birthTime",
	                "valueDateTime": "2016-01-04T00:00:00-06:00"
	            }
	        ],
	        "active": true,
	        "name": [
	            {
	                "family": "BiliABCCCCCCCCCCCCCCCC",
	                "given": [
	                    "Baby"
	                ]
	            }
	        ],
	        "gender": "male",
	        "birthDate": "2016-01-04"
			}

	FHIR.oauth2.ready().then(function(client) {
		client.update(updatedPatient).then((data) => { console.log(data); });
	}) */
	function setData(id){
	const updatePatient={
			"resourceType": "Patient",
	        "id": "${param.id}",
	        "meta": {
	            "versionId": "1",
	            "lastUpdated": "2020-07-15T02:51:23.000+00:00",
	            "source": "#mNKBng6Y74bFyYWP"
	        },
	        "extension": [
	            {
	                "url": "http://hl7.org/fhir/StructureDefinition/patient-birthTime",
	                "valueDateTime": "2016-01-04T00:00:00-06:00"
	            }
	        ],
	        "active": ${param.active},
	        "name": [
	            {
	                "family": "${param.name}",
	                "given": [
	                    "Baby"
	                ]
	            }
	        ],
	        "gender": "${param.gender}",
	        "birthDate": "2016-01-04"
			}
	console.log(updatePatient);
	console.log("${param.name}");
	if (confirm('Do you want to Edit the Patient ${param.id}')) {
	FHIR.oauth2.ready().then(function(client) {
		client.update(updatedPatient).then((data) => { console.log(data); });
	}).catch(createRenderer("output"));
	}
    
}
	</script>
</body>
</html>






