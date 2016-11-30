<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %>
<!doctype html>
<html lang='nl'>
	<head>
		<vdab:head/>
	</head>
	<body>
		<vdab:header/> 
		<form method="post">
		<table class="zebra">
				<tr>
					<th>Datum</th>
					<th>Titel</th>
					<th>Uitvoerders</th>
					<th>Prijs</th>
					<th>Plaatsen</th>
					<th class="centraal"><div><input type="submit" value="Verwijderen"></div></th>
				</tr>
				<c:if test="${not empty reserveringen}">
					<c:set var="totaalBedrag" value="${0}"/>
					<c:forEach var="reservering" items="${reserveringen}">
						<tr>
							<td><fmt:formatDate value="${reservering.voorstelling.datum}" type="both" timeStyle="short"/></td>
							<td>${reservering.voorstelling.titel}</td>
							<td>${reservering.voorstelling.uitvoerders}</td>
							<td class="rechts">&euro;${reservering.voorstelling.prijs}</td>
							<td class="rechts">${reservering.aantalPlaatsen}</td>
							<td class="centraal"><div><input type="checkbox" name="id" value="${reservering.voorstelling.id}"></div></td>
						</tr>
						<c:set var="totaalBedrag" value="${totaalBedrag + reservering.voorstelling.prijs * reservering.aantalPlaatsen}"/>
					</c:forEach>
				</c:if>
		</table>
		</form>
		<p>Te betalen: &euro;${totaalBedrag}</p>  	
	</body>
</html>
