<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
	<head>
		<v:head title="under construction"/>
	</head>
	<body>
		<h1>Wijn toevoegen aan mandje</h1>
		<a>Terug naar overzicht</a>
		<dl>
			<dt>Land</dt>
			<dd></dd>
			<dt>Soort</dt>
			<dd></dd>
			<dt>Jaar</dt>
			<dd></dd>
			<dt>Beoordeling</dt>
			<dd></dd>
			<dt>Prijs</dt>
			<dd></dd>
		</dl>
		<form method="post">
			<label>Aantal flessen<span></span><input type="number" autofocus="autofocus" required="required" min="1" step="1"></label>
			<input type="submit" value="Toevoegen">
		</form>
	</body>
</html>