<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
	<head>
		<v:head title="${wijn.soorten.naam} ${wijn.jaar} uit ${wijn.soorten.landen.naam}"/>
	</head>
	<body>
		<h1>Wijn toevoegen aan mandje</h1>
		<c:url value="/detail.htm" var="terugkeerURL">
			<c:param name="id" value="${wijn.soorten.landen.id}"/>
			<c:param name="soortid" value="${wijn.soorten.id}"/>						
		</c:url>
		<a href="${terugkeerURL}">Terug naar overzicht</a>
		<dl>
			<dt>Land</dt>
			<dd>${wijn.soorten.landen.naam}</dd>
			<dt>Soort</dt>
			<dd>${wijn.soorten.naam}</dd>
			<dt>Jaar</dt>
			<dd>${wijn.jaar}</dd>
			<dt>Beoordeling</dt>
			<dd><c:forEach begin="1" end="${wijn.beoordeling}">&#9733;</c:forEach></dd>
			<dt>Prijs</dt>
			<dd>${wijn.prijs}</dd>
		</dl>
		<form method="post">
			<label>Aantal flessen<span>${fout}</span><input name="aantalFlessen" type="number" autofocus="autofocus" required="required" min="1" step="1"></label>
			<input type="submit" value="Toevoegen">
		</form>
	</body>
</html>