<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
    <head>
    	<v:head/>
    </head>
    <body>
		<v:header/>
		<h2>Genres</h2>
		<nav>
			<ul>
				<c:forEach var="entry" items="${genres}">
					<li><a href="<c:url value='/index.htm'><c:param name='id' value='${entry.key}'/></c:url>">${entry.value}</a></li>
				</c:forEach>
			</ul>
		</nav>
		<c:if test="${not empty lijstVoorstellingen}">
			<table class="zebra">
				<tr>
					<th>Datum</th>
					<th>Titel</th>
					<th>Uitvoerders</th>
					<th>Prijs</th>
					<th>Vrije plaatsen</th>
					<th>Reserveren</th>
				</tr>
				<c:forEach var="voorstelling" items="${lijstVoorstellingen}">
					<tr>
						<td><fmt:formatDate value="${voorstelling.datum}" type="both" timeStyle="short"/></td>
						<td>${voorstelling.titel}</td>
						<td>${voorstelling.uitvoerders}</td>
						<td class="rechts">&euro;${voorstelling.prijs}</td>
						<td class="rechts">${voorstelling.vrijePlaatsen}</td>
						<td class="centraal"><div><c:if test="${voorstelling.vrijePlaatsen != 0}"><a href="<c:url value='/reserveren.htm'><c:param name='id' value='${voorstelling.id}'/></c:url>">Reserveren</a></c:if></div></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
    </body>
</html>
