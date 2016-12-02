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
		<c:if test="${not empty gelukteReserveringen}">
			<h2>Gelukte reserveringen</h2>
			<table class="zebra">
				<tr>
					<th>Datum</th>
					<th>Titel</th>
					<th>Uitvoerders</th>
					<th>Prijs(&euro;)</th>
					<th>plaatsen</th>
				</tr>
				<c:forEach var="reservering" items="${gelukteReserveringen}">
					<tr>
						<td><fmt:formatDate value="${reservering.voorstelling.datum}" type="both" timeStyle="short"/></td>
						<td>${reservering.voorstelling.titel}</td>
						<td>${reservering.voorstelling.uitvoerders}</td>
						<td class="rechts">${reservering.voorstelling.prijs}</td>
						<td class="rechts">${reservering.aantalPlaatsen}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${not empty mislukteReserveringen}">
			<h2>Mislukte reserveringen</h2>
			<table class="zebra">
				<tr>
					<th>Datum</th>
					<th>Titel</th>
					<th>Uitvoerders</th>
					<th>Prijs(&euro;)</th>
					<th>plaatsen</th>
					<th>Vrije plaatsen</th>
				</tr>
				<c:forEach var="reservering" items="${mislukteReserveringen}">
					<tr>
						<td><fmt:formatDate value="${reservering.voorstelling.datum}" type="both" timeStyle="short"/></td>
						<td>${reservering.voorstelling.titel}</td>
						<td>${reservering.voorstelling.uitvoerders}</td>
						<td class="rechts">${reservering.voorstelling.prijs}</td>
						<td class="rechts">${reservering.aantalPlaatsen}</td>
						<td class="rechts">${reservering.voorstelling.vrijePlaatsen}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</body>
</html>