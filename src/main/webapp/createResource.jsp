
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link href="<c:url value="/resource/css/B1View.css" />" rel="stylesheet">

<script type="text/javascript" src="./fhir-client.js"></script>

<script type="text/javascript" src="./app.js"></script>


</head>
<body>
	<h1>Add New Patient</h1>
	<hr>
	<br />


	<form name="addForm" method="post"
		action="index.html">

		<input type="submit" value="BACK" />

	</form>

	<form name="addForm">

		<textarea rows="20" cols="100" name="feedback" id='output'>${patient}</textarea>

	</form>

	<script type="text/javascript">	
		console.log(${patient});
		if (confirm('Do you want to Add the Patient')) {
			
				FHIR.oauth2.ready().then(function(client) {
					var app = new App(client);
					app.create(${patient});
				})
		}
		
	</script>
</body>
</html>






