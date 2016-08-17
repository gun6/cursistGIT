package be.vdab.enums;

/**
 *		6 Enum
 *		
 *			JPA vertaalt de enum in de db naar een int of een varchar.
 *			Je houdt per docent het geslacht bij in een private variabele.
 *
 *			Als JPA een record omzet naar een Docent entity, vult JPA de variabele geslacht met:
 *			- MAN (als de kolom 0 bevat)
 *			- VROUW (als de kolom 1 bevat)
 *
 *			Geslacht.java
 *			Docent.java
 *			zoeken.jsp
 */

public enum Geslacht 
{
	MAN, VROUW
}
