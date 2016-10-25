<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%> 
<%@taglib uri='http://vdab.be/tags' prefix='v' %>
<!doctype html>
<html lang='nl'>
	<head>
		<v:head title="Kortingen"/>
	</head>
	<body>
		<v:menu/>
		<h1>Kortingen</h1>
		<ul>
			<c:forEach items="${artikels}" var="artikel">
				<li><a href='<c:url value =''><c:param name = "id" value="${artikel.id}"/></c:url>'>${artikel.naam}</a></li>
				<c:if test="${artikel.id == param.id}">
					<ul>
						<c:forEach items="${artikel.kortingen}" var="korting">
							<li>${korting}</li>
						</c:forEach>
					</ul>
				</c:if>
			</c:forEach>
		</ul>
	</body>
</html>