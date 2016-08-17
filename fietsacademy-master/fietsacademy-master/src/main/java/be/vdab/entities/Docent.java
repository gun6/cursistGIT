package be.vdab.entities;

import java.io.Serializable;
import java.math.*;

import javax.persistence.*;

import be.vdab.enums.Geslacht;

/**
 * 		3 Entity class
 *			
 *			Java class met dezelfde data als een tabel in de db
 *			JPA annotations beschrijven de mappings
 *			De class bevat een private variabele per kolom in de tabel
 *
 *			@Entity
 *			@Table
 *			@Id
 *			@GeneratedValue
 */

/**
 *		6 Enum
 *		
 *			JPA vertaalt de enum in de db naar een int of een varchar.
 *			Je houdt per docent het geslacht bij in een private variabele.
 *
 *			Als JPA een record omzet naar een Docent entity, vult JPA de variabele geslacht met:
 *			- MAN (als de kolom 0/MAN bevat)
 *			- VROUW (als de kolom 1/VROUW bevat)
 *
 *			Geslacht.java
 *			Docent.java
 *			zoeken.jsp
 */

/**
 * 		9 Entity toevoegen
 *
 *			EM voegt een entity toe aan de db met .persist(entity)
 *			De method stuurt een sql insert naar de db
 *
 *			Docent.java
 *				public Docent()	{}		protected Docent() {}
 *				isVoornaamValid()		isFamilienaamValid()
 *				isWeddeValid()			isRijksRegisterNrValid()
 *				setVoornaam()			setFamilienaam()
 *				setWedde()				setGeslacht()
 *				setRijksRegisterNr()
 *
 *			DocentDAO.java
 *				create()
 *
 *			DocentService.java
 *				create()
 *
 *			ToevoegenServlet.java
 *
 *			toevoegen.jsp
 */

@Entity
@Table(name = "docenten")
public class Docent implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	private String voornaam;
	private String familienaam;
	private BigDecimal wedde;
	private long rijksRegisterNr;
	
	@Enumerated(EnumType.STRING)
	private Geslacht geslacht;
	
	public Geslacht getGeslacht() {
		return geslacht;
	}
	
	public long getId() {
		return id;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public BigDecimal getWedde() {
		return wedde;
	}

	public long getRijksRegisterNr() {
		return rijksRegisterNr;
	}

	public void opslag(BigDecimal percentage) 
	{
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(new BigDecimal(100)));
		wedde = wedde.multiply(factor, new MathContext(2, RoundingMode.HALF_UP));
	}
	
	public String getNaam() 
	{
		return voornaam + ' ' + familienaam;
	}
	
	public Docent(String voornaam, String familienaam, BigDecimal wedde, Geslacht geslacht, long rijksRegisterNr) 
	{
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setWedde(wedde);
		setGeslacht(geslacht);
		setRijksRegisterNr(rijksRegisterNr);
	}
	
	protected Docent() {}
	
	public static boolean isVoornaamValid(String voornaam) 
	{
		return voornaam != null && ! voornaam.isEmpty();
	}
	
	public static boolean isFamilienaamValid(String familienaam) 
	{
		return familienaam != null && ! familienaam.isEmpty();
	}
	
	public static boolean isWeddeValid(BigDecimal wedde)
	{
		return wedde != null && wedde.compareTo(BigDecimal.ZERO) >= 0;
	}
	
	public static boolean isRijksRegisterNrValid(long rijksRegisterNr) 
	{
		long getal = rijksRegisterNr / 100;
		
		if (rijksRegisterNr / 1_000_000_000 < 50) 
		{
			getal += 2_000_000_000;
		}
		return rijksRegisterNr % 100 == 97 - getal % 97;
	}
	
	public void setVoornaam(String voornaam) 
	{
		if (! isVoornaamValid(voornaam)) 
		{
			throw new IllegalArgumentException();
		}
		this.voornaam = voornaam;
	}
	
	public void setFamilienaam(String familienaam) 
	{
		if (! isFamilienaamValid(familienaam)) 
		{
			throw new IllegalArgumentException();
		}
		this.familienaam = familienaam;
	}
	
	public void setWedde(BigDecimal wedde) 
	{
		if (! isWeddeValid(wedde)) 
		{
			throw new IllegalArgumentException();
		}
		this.wedde = wedde;
	}
	
	public void setGeslacht(Geslacht geslacht) 
	{
		this.geslacht = geslacht;
	}
	
	public void setRijksRegisterNr(long rijksRegisterNr) 
	{
		if (! isRijksRegisterNrValid(rijksRegisterNr)) 
		{
			throw new IllegalArgumentException();
		}
		this.rijksRegisterNr = rijksRegisterNr;
	}
}
