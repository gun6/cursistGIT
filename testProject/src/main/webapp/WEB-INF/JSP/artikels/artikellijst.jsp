<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%> 
<%@taglib uri='http://vdab.be/tags' prefix='v' %>
<!doctype html>
<html lang='nl'>
	<head>
		<v:head title="Lijst artikels"/>
	</head>
	<body>
		<v:menu/>
		<h1>Lijst artikels</h1>
		<table>
			<thead>
				<tr>
					<th>Artikel</th>
					<th>Groep</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${lijst}" var="item">
					<tr>
						<td>${item.naam}</td>
						<td>${item.artikelgroepen.naam}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>