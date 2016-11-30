package be.vdab.entities;

import java.io.Serializable;

public class Klant implements Serializable{
	private static final long serialVersionUID = 1L;
	private String voornaam;
	private String familienaam;
	private String straat;
	private String huisnr;
	private String postcode;
	private String gemeente;
	private String gebruikersnaam;
	private String paswoord;
	
	public Klant(String voornaam, String familienaam, String straat, String huisnr, String postcode, String gemeente,
			String gebruikersnaam, String paswoord) {
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.straat = straat;
		this.huisnr = huisnr;
		this.postcode = postcode;
		this.gemeente = gemeente;
		this.gebruikersnaam = gebruikersnaam;
		this.paswoord = paswoord;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public String getHuisnr() {
		return huisnr;
	}

	public void setHuisnr(String huisnr) {
		this.huisnr = huisnr;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getGemeente() {
		return gemeente;
	}

	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

	public String getPaswoord() {
		return paswoord;
	}

	public void setPaswoord(String paswoord) {
		this.paswoord = paswoord;
	}

	@Override
	public String toString() {
		return voornaam + " " + familienaam + " " + straat + " "
				+ huisnr + " " + postcode + " " + gemeente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gebruikersnaam == null) ? 0 : gebruikersnaam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Klant))
			return false;
		Klant other = (Klant) obj;
		if (gebruikersnaam == null) {
			if (other.gebruikersnaam != null)
				return false;
		} else if (!gebruikersnaam.equals(other.gebruikersnaam))
			return false;
		return true;
	}
	
	
	
}
