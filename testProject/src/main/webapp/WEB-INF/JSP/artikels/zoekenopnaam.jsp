<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%> 
<%@taglib uri='http://vdab.be/tags' prefix='v' %>
<!doctype html>
<html lang='nl'>
	<head>
		<v:head title="Zoeken op naam"/>
	</head>
	<body>
		<v:menu/>
		<h1>Zoeken op naam</h1>
		<form>
			<label>Naam: <span>${fouten.naam}</span><input type="text" value="${param.naam}" name="naam" required="required" autofocus="autofocus" /></label>
			<input type="submit" value="Zoeken" />
			<input type="hidden" name="teller" value="${teller}">
		</form>
		<c:if test="${not empty param and empty fouten and empty artikels}">
			Geen artikels gevonden
		</c:if>
		<c:if test="${not empty artikels}">
			<table>
				<thead>
					<tr>
						<th>Nummer</th>
						<th>Naam</th>
						<th>Aankoopprijs</th>
						<th>Verkoopprijs</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${artikels}" var="artikel">
						<tr>
							<td>${artikel.id}</td>
							<td>${artikel.naam}</td>
							<td><fmt:formatNumber value="${artikel.aankoopprijs}" minFractionDigits='2' maxFractionDigits='2'/></td>
							<td><fmt:formatNumber value="${artikel.verkoopprijs}" minFractionDigits='2' maxFractionDigits='2'/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${vanafRij != 0}">
				<c:url value='' var='vorigePaginaURL'>
					<c:param name="naam" value="${param.naam}"/>
					<c:param name="vanafRij" value="${vanafRij - aantalRijen}"/>
					<c:param name="teller" value="${teller-1}"/>
				</c:url>
				<a href='<c:out value="${vorigePaginaURL}"/>' title="vorige pagina" class='pagineren'>&larr; ${teller-1}</a>
			</c:if>
			${teller}
			<c:if test="${empty laatstePagina}">
				<c:url value='' var='volgendePaginaURL'>
					<c:param name="naam" value="${param.naam}"/>
					<c:param name="vanafRij" value="${vanafRij + aantalRijen}"/>
					<c:param name="teller" value="${teller+1}"/>
				</c:url>
				<a href='<c:out value="${volgendePaginaURL}"/>' title="volgede pagina" class='pagineren'>${teller+1} &rarr;</a>
			</c:if>
		</c:if>
	</body>
</html>