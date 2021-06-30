
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link href="<c:url value="/resource/css/B1View.css" />" rel="stylesheet">

<script type="text/javascript" src="./fhir-client.js"></script>

<script type="text/javascript" src="./app.js"></script>


</head>
<body>
	<h1>Resource to be Edited ::::: ${id}</h1>
	<hr>
	<br />


	<form name="editDeletePatientForm" method="post"
		action="index.html">

		<input type="submit" value="BACK" />

	</form>

	<form name="deletePatientForm" method="post"
		action="PatientEditServlet">

		<textarea rows="20" cols="100" name="feedback" id='output'></textarea>

	</form>

	<script type="text/javascript">	
	console.log("${param.id}");
	console.log("${param.fname}"+","+"${param.gname}");
	console.log('${param.address}');
	const updatedPatient={
			"resourceType": "Patient",
	        "id": "${param.id}",
	        "identifier": [
	            {
	                "use": "${param.idUse}",
	                "type": {
	                    "coding": [
	                        {
	                            "system": "http://terminology.hl7.org/CodeSystem/v2-0203",
	                            "code": "MR",
	                            "display": "Medical Record Number"
	                        }
	                    ],
	                    "text": "Medical Record Number"
	                },
	                "system": "${param.idSystem}",
	                "value": "${param.idValue}"
	            }
	        ],
	        "active": "${param.active}",
	        "name": [
	            {
	                "family": "${param.fname}",
	                "given": [
	                	"${param.gname}"
	                ]
	            }
	        ],
	        "telecom": [
	            {
	                "system": "phone",
	                "value": "${param.phone}",
	                "use": "mobile"
	            },
	            {
	                "system": "email",
	                "value": "${param.email}"
	            }
	        ],
	        "gender": "${param.gender}",
	        "birthDate": "${param.birthDate}",
	        "address": [
	            {
	                "use": "home",
	                "text": "${param.address}${param.city}${param.state}${param.postal}${param.country}",
	                "line": [
	                    "${param.address}"
	                ],
	                "city": "${param.city}",
	                "state": "${param.state}",
	                "postalCode": "${param.postal}",
	                "country": "${param.country}"
	            }
	        ],
	        "generalPractitioner": [
	            {
	                "reference": "${param.generalPractitioner}"
	            }
	        ]
			}
	
	console.log(${patient});
		if (confirm('Do you want to Edit the Patient ${id}')) {
			
			FHIR.oauth2.ready().then(function(client) {
				var app = new App(client);
				app.update(${patient});
			})
		}
		
	</script>
</body>
</html>






