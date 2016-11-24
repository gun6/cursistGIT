<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
	<head>
		<v:head title="Bevestiging"/>
	</head>
	<body>
		<h1>Je mandje is bevestigd als bestelbon ${bestelbonnr}</h1>
		<div class="spacing">
			<c:url value="index.htm" var="terugkeerURL"/>					
			<a href="${terugkeerURL}">Terug naar overzicht</a>
		</div>
	</body>
</html>