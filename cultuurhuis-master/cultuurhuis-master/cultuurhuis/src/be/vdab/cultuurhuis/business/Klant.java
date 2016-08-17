package be.vdab.cultuurhuis.business;

import java.io.Serializable;

public class Klant implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int klantId;	//id in de db
	private String voornaam;
	private String familienaam;
	private String straat;
	private String huisNr;
	private String postCode;
	private String gemeente;
	private String gebruikersnaam;
	private String paswoord;
	
	public Klant(String voornaam, String familienaam, String straat, String huisNr, String postCode, String gemeente, String gebruikersnaam, String paswoord) 
	{
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setStraat(straat);
		setHuisNr(huisNr);
		setPostCode(postCode);
		setGemeente(gemeente);
		setGebruikersnaam(gebruikersnaam);
		setPaswoord(paswoord);
	}
	
	public Klant(int klantId, String voornaam, String familienaam, String straat, String huisNr, String postCode, String gemeente, String gebruikersnaam, String paswoord) 
	{
		setKlantId(klantId);
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setStraat(straat);
		setHuisNr(huisNr);
		setPostCode(postCode);
		setGemeente(gemeente);
		setGebruikersnaam(gebruikersnaam);
		setPaswoord(paswoord);
	}
	
	public int getKlantId() 
	{
		return klantId;
	}
	
	public void setKlantId(int klantId) 
	{
		this.klantId = klantId;
	}
	
	public String getVoornaam() 
	{
		return voornaam;
	}
	
	public void setVoornaam(String voornaam) 
	{
		if((voornaam == null) || voornaam.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.voornaam = voornaam;
	}
	
	public String getFamilienaam() 
	{
		return familienaam;
	}
	
	public void setFamilienaam(String familienaam) 
	{
		if((familienaam == null) || familienaam.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.familienaam = familienaam;
	}
	
	public String getStraat() 
	{
		return straat;
	}
	
	public void setStraat(String straat) 
	{
		if ( (straat == null) || straat.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.straat = straat;
	}
	
	public String getHuisNr() 
	{
		return huisNr;
	}
	
	public void setHuisNr(String huisNr) 
	{
		if((huisNr == null) || huisNr.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.huisNr = huisNr;
	}
	
	public String getPostCode() 
	{
		return postCode;
	}
	
	public void setPostCode(String postCode) 
	{
		if((postCode == null) || postCode.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.postCode = postCode;
	}
	
	public String getGemeente()
	{
		return gemeente;
	}
	
	public void setGemeente(String gemeente) 
	{
		if((gemeente == null) || gemeente.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.gemeente = gemeente;
	}
	
	public String getGebruikersnaam() 
	{
		return gebruikersnaam;
	}
	
	public void setGebruikersnaam(String gebruikersnaam) 
	{
		if((gebruikersnaam == null) || gebruikersnaam.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.gebruikersnaam = gebruikersnaam;
	}
	
	public String getPaswoord() 
	{
		return paswoord;
	}
	
	public void setPaswoord(String paswoord)
	{
		if((paswoord == null) || paswoord.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.paswoord = paswoord;
	}
}
