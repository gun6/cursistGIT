<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%> 
<%@taglib uri='http://vdab.be/tags' prefix='v' %> 
<!doctype html>
<html lang='nl'>
	<head>
		<v:head title="Zoeken op nummer"/>
	</head>
	<body>
		<v:menu/>
		<h1>Zoeken op nummer</h1>
		<form>
			<label>Artikelnr:<span>${fouten.artikelnr}</span><input type="number" name="artikelnr" value='${param.artikelnr}' required="required" autofocus="autofocus"></label>
			<input type="submit" value="Zoeken">
		</form>
		<c:if test="${not empty param and empty fouten and empty artikel}">Artikel niet gevonden</c:if>
		<c:if test="${not empty artikel}">${artikel.naam}, AP: <fmt:formatNumber value="${artikel.aankoopprijs}" maxFractionDigits="2" minFractionDigits="2"/> &euro;, VP: <fmt:formatNumber value="${artikel.verkoopprijs}" maxFractionDigits="2" minFractionDigits="2"/> &euro;, Winst: <fmt:formatNumber value="${(artikel.verkoopprijs-artikel.aankoopprijs)/artikel.aankoopprijs*100}" minFractionDigits="0" maxFractionDigits="0"/> &#37;</c:if>
	</body>
</html>