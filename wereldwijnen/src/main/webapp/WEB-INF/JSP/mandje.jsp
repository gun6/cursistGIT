<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
	<head>
		<v:head title="Mandje"/>
	</head>
	<body>
		<h1>Mandje</h1>
		<c:if test="${not empty fouten.database}"><h2>${fouten.database}</h2></c:if>
		<div class="spacing">
			<c:url value="index.htm" var="terugkeerURL"/>					
			<a href="${terugkeerURL}">Terug naar overzicht</a>
		</div>
		<div class="spacing">
			<table>
				<tr>
					<th>Wijn</th>
					<th>Prijs</th>
					<th>Aantal</th>
					<th>Te betalen</th>
				</tr>
				<c:set var="total" value="${0}"/>
				<c:forEach items="${bestellijnen}" var="lijn">
					<tr>
						<td>${lijn.naam}</td>
						<td>${lijn.prijs}</td>
						<td>${lijn.aantal}</td>
						<td>${lijn.prijs * lijn.aantal}</td>
					</tr>
					<c:set var="total" value="${total + lijn.prijs * lijn.aantal}"/>
				</c:forEach>
				<tr>
					<td colspan="3"></td>
					<td>Totaal: ${total}</td>
				</tr>
			</table>
		</div>
		<div class="spacing">
			<form method="post" id="toevoegform">
				<label>Naam<span>${fouten.naam}</span><input name="naam" type="text" required="required" autofocus="autofocus" value="${param.naam}"></label>
				<label>Straat<span>${fouten.straat}</span><input name="straat" type="text" required="required" value="${param.straat}"></label>
				<label>Huisnummer<span>${fouten.huisnummer}</span><input name="huisnummer" type="number" min="1" step="1" required="required" value="${param.huisnummer}"></label>
				<label>Postcode<span>${fouten.postcode}</span><input name="postcode" type="text" pattern="\d{4}" required="required" value="${param.postcode}"></label>
				<label>Gemeente<span>${fouten.gemeente}</span><input name="gemeente" type="text" required="required" value="${param.gemeente}"></label>
				<label>afhalen<input name="bestelwijze" type="radio" value="0" required="required" ${param.bestelwijze!=1 ? "checked" : ""}></label>
				<label>Opsturen<span>${fouten.bestelwijze}</span><input name="bestelwijze" type="radio" value="1" required="required" ${param.bestelwijze==1 ? "checked" : ""}></label>
				<input type="submit" value="Als bestelbon bevestigen" id="toevoegknop">
			</form>
		</div>
		<script>
			document.getElementById('toevoegform').onsubmit = function(){
				document.getElementById('toevoegknop').disabled = true;
			};
		</script>
	</body>
</html>