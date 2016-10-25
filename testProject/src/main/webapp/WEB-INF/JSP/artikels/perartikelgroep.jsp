<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%> 
<%@taglib uri='http://vdab.be/tags' prefix='v' %>
<!doctype html>
<html lang='nl'>
	<head>
		<v:head title="Per artikelgroep"/>
	</head>
	<body>
		<v:menu/>
		<h1>Per artikelgroep</h1>
		<ul>
			<c:forEach items="${groepen}" var="groep">
				<li><a href='<c:url value =''><c:param name = "id" value="${groep.id}"/></c:url>'>${groep.naam}</a></li>
				<c:if test="${groep.id == param.id}">
					<ul>
						<c:forEach items="${groep.artikels}" var="artikel">
							<li>${artikel.id} ${artikel.naam} ${artikel.verkoopprijs}</li>
						</c:forEach>
					</ul>
				</c:if>
			</c:forEach>
		</ul>
	</body>
</html>