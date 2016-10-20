<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
	<head>
		<v:head title="Alles voor de keuken"/>
	</head>
	<body>
		<v:menu/>
		<h1>Alles voor de keuken</h1>
		<img alt="logo" src='<c:url value="/images/logo${randomnr}.jpg"/>'>
	</body>
</html>
