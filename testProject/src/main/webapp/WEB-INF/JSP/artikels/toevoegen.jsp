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
			<label>Aankoopprijs: <span>${fouten.aankoopprijs}</span><input type="number" id="aankoopprijs" onchange="updateField(this)" name="aankoopprijs" min="0.01" step="0.01" value="${param.aankoopprijs}" required="required"/></label>
			<label>Verkoopprijs: <span>${fouten.verkoopprijs}</span><input type="number" id="verkoopprijs" name="verkoopprijs" min="" step="0.01" value="${param.verkoopprijs}" required="required" /></label>
			<div><label><input type="radio" name="catagorie" id="food" value="F" required="required">Food</label></div>
			<label>Houdbaarheid: <input type="number" name="houdbaarheid" id="houdbaarheid" value="${param.houdbaarheid}" min="1" step="1"></label>
			<div><label><input type="radio" name="catagorie" id="nonfood" value="NF">Non-Food</label></div>
			<label>Garantie: <input type="number" name="garantie" id="garantie" value="${param.garantie}" min="1" step="1"></label>
			<label>Artikelgroep: <select name="artikelgroep"><c:forEach items="${groepen}" var="groep"><option value="${groep.id}">${groep.naam}</option></c:forEach></select></label>
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
		<script>
			document.getElementById('food').onclick = enableDisableInputs;
			document.getElementById('nonfood').onclick = enableDisableInputs;
			function enableDisableInputs(){
				document.getElementById('houdbaarheid').disabled = ! document.getElementById('food').checked;
				document.getElementById('garantie').disabled = ! document.getElementById('nonfood').checked;
			}
		</script>
	</body>
</html>