<%@page contentType='text/html' pageEncoding='UTF-8' session='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='v' uri='http://vdab.be/tags' %>
<!doctype html>
<html lang='nl'>
	<head>
		<v:head/>
	</head>
	<body>
		<v:header/>
		<h2>Stap 1: wie ben je ?</h2>
		<form method="post">
			<ul class="zonderbolletjes">
				<li><label>Gebruikersnaam:<input name="gebruikersnaam" autofocus></label></li>
				<li><label>Paswoord:<input type="password" name="paswoord"></label></li>
			</ul>
			<input type="submit" value="Zoek me op" ${actiefEen}>
		</form>
		<form action="/cultuurhuisbis/nieuweklant.htm">
			<input type="submit" value="Ik ben nieuw" ${actiefEen}>
		</form>
		${klantgegevens}
		<h2>Stap 2: Bevestigen</h2>
		<form action="/cultuurhuisbis/overzicht.htm" method="post">
			<input type="submit" value="Bevestigen" ${actiefTwee}>
		</form>
	</body>
</html>