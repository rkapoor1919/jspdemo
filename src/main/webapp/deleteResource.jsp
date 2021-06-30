
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link href="<c:url value="/resource/css/B1View.css" />" rel="stylesheet">

<script type="text/javascript" src="./fhir-client.js"></script>

<script type="text/javascript" src="./app.js"></script>


</head>
<body>
	<h1>Resource to be deleted ::::: ${param.id}</h1>
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
		if (confirm('Do you want to Edit the Patient ${param.id}')) {
			
			FHIR.oauth2.ready().then(function(client) {
				var app = new App(client);
				app.delete("Patient/BILIBABY1");
			})
		}
		
	</script>
</body>
</html>






