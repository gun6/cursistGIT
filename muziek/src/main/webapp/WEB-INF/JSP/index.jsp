<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
    <head>
    	<v:head title="Muziek"></v:head>
    </head>
    <body>
		<h1>Albums</h1>
		<table>
			<thead>
				<tr>
					<th>Album</th>
					<th>Artiest</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${indexLijst}" var="item">
				<c:url value="/detail.htm" var="detailURL">
					<c:param name="id" value="${item.id}"/>
				</c:url>
					<tr>
						<td><a href="${detailURL}">${item.naam}</a></td>
						<td>${item.artiesten.naam}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
    </body>
</html>
