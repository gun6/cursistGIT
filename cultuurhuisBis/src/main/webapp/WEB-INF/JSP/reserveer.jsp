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
		<dl>
			<dt>Voorstelling:</dt>
			<dd>${voorstelling.titel}</dd>
			<dt>Uitvoerders:</dt>
			<dd>${voorstelling.uitvoerders}</dd>
			<dt>Datum:</dt>
			<dd><fmt:formatDate value="${voorstelling.datum}" type="both" timeStyle="short"/></dd>
			<dt>Prijs:</dt>
			<dd>&euro;${voorstelling.prijs}</dd>
			<dt>Vrije plaatsen:</dt>
			<dd>${voorstelling.vrijePlaatsen}</dd>
		</dl>
		<form method="post">
			<label>Plaatsen<input name="aantalPlaatsen" value='${vorigAantalPlaatsen}' type="number" min='1' step="1" max'${voorstelling.vrijePlaatsen}' autofocus required></label>
			<input type="submit" value="Reserveren"> ${fout}
		</form>		 
	</body>
</html>