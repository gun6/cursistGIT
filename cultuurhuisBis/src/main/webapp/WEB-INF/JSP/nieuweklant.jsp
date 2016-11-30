<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %>
<!doctype html>
<html lang='nl'>
	<head>
		<vdab:head/>
	</head>
	<body>
		<vdab:header/>
		<form method="post">
			<ul class="zonderbolletjes">
				<li><label>Voornaam:<input name="voornaam" autofocus></label></li>
				<li><label>Familienaam:<input name="familienaam"></label></li>
				<li><label>Straat:<input name="straat"></label></li>
				<li><label>Huisnr:<input type="number" name="huisnr"></label></li>
				<li><label>Postcode:<input type="number" name="postcode"></label></li>
				<li><label>Gemeente:<input name="gemeente"></label></li>
				<li><label>Gebruikersnaam:<input name="gebruikersnaam"></label></li>
				<li><label>Paswoord:<input type="password" name="paswoord"></label></li>
				<li><label>Herhaal paswoord:<input type="password" name="herhaalpaswoord"></label></li>
				<li><input type="submit" value="OK"></li>
			</ul>
		</form>
		<c:if test="${not empty fouten}">
			<ul>
				<c:forEach var="melding" items="${fouten}">
					<li>${melding}</li>
				</c:forEach>
			</ul>
		</c:if>
	</body>
</html>