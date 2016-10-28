<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
	<head>
		<v:head title="album ${album.naam}"></v:head>
	</head>
	<body>
		<h1>${album.naam}: ${album.artiesten.naam}</h1>
		<table>
			<thead>
				<tr>
					<th>Track</th>
					<th>Tijd</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${album.tracks}" var="track">
					<tr>
						<td>${track.naam}</td>
						<td>${track.tijd}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		totale tijd: ${album.tijd}
	</body>
</html>