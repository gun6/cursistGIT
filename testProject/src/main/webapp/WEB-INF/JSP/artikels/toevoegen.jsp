<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%> 
<%@taglib uri='http://vdab.be/tags' prefix='v' %>
<!doctype html>
<html lang='nl'>
	<head>
		<v:head title="Toevoegen"/>
	</head>
	<body>
		<v:menu/>
		<h1>Toevoegen</h1>
		<form method="post" id="toevoegform">
			<label>Naam: <span>${fouten.naam}</span><input type="text" name="naam" required="required" autofocus="autofocus" value="${param.naam}"/></label>
			<label>Aankoopprijs: <span>${fouten.aankoopprijs}</span><input type="number" id="aankoopprijs" onchange="updateField(this)" name="aankoopprijs" min="0.01" step="0.01" value="${param.aankoopprijs}"/></label>
			<label>Verkoopprijs: <span>${fouten.verkoopprijs}</span><input type="number" id="verkoopprijs" name="verkoopprijs" min="" step="0.01" value="${param.verkoopprijs}" /></label>
			<input type="submit" value="Toevoegen" id="toevoegknop" />
		</form>
		<script>
			document.getElementById('toevoegform').onsubmit = function(){
				document.getElementById('toevoegknop').disabled = true;
			};
		</script>
		<script>
			function updateField(aankoopprijs){
				document.getElementById("verkoopprijs").min = aankoopprijs.value;
			}
		</script>
	</body>
</html>