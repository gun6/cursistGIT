<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
	<head>
		<v:head title="${land.naam}"/>
	</head>
	<body>
		<v:menu/>
		<c:if test="${not empty soorten}">
			<h2>Soorten uit ${land.naam}</h2>
			<ul class="zonderbolletjes">
				<c:forEach items="${soorten}" var="soort">
					<c:url value="" var="soortURL">
						<c:param name="id" value="${param.id}"/>
						<c:param name="soortid" value="${soort.id}"/>
					</c:url>
					<li><a href="${soortURL}">${soort.naam}</a></li>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${not empty wijnen}">
			<h2>Wijnen uit ${huidigeSoort.naam}</h2>
			<ul>
				<c:forEach items="${wijnen}" var="wijn">
					<c:url value="/toevoegen.htm" var="wijnURL">
						<c:param name="wijnid" value="${wijn.id}"/>				
					</c:url>
					<li><a href="${wijnURL}">${wijn.jaar}</a> <c:forEach begin="1" end="${wijn.beoordeling}">&#9733;</c:forEach></li>
				</c:forEach>
			</ul>
		</c:if>
	</body>
</html>