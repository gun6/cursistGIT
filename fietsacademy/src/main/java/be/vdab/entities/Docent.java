package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import be.vdab.enums.Geslacht;

@Entity
@Table (name = "docenten")
public class Docent implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private String voornaam;
	private String familienaam;
	private BigDecimal wedde;
	private long rijksregisterNr;
	@Enumerated (EnumType.STRING)
	private Geslacht geslacht;
	
	
	public Docent(String voornaam, String familienaam, BigDecimal wedde, long rijksregisterNr,
			Geslacht geslacht) {
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.wedde = wedde;
		this.rijksregisterNr = rijksregisterNr;
		this.geslacht = geslacht;
	}
	
	protected Docent() {}
	
	public static boolean isVoornaamValid(String voornaam) {
		return voornaam != null && ! voornaam.isEmpty();
	}
	
	public static boolean isFamilienaamValid(String familienaam) {
		return familienaam != null && ! familienaam.isEmpty();
	}
	
	public static boolean isWeddeValid(BigDecimal wedde) {
		return wedde != null && wedde.compareTo(BigDecimal.ZERO)>=0;
	}
	
	public static boolean isRijksregisterNrValid(long rijksregisterNr) {
		long getal = rijksregisterNr/100;
		if (rijksregisterNr/1000000000 <50) {
			getal += 2000000000;
		}
		return rijksregisterNr % 100 == 97 - getal % 97;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getVoornaam() {
		return voornaam;
	}
	
	public void setVoornaam(String voornaam) {
		if (! isVoornaamValid(voornaam)) {
			throw new IllegalArgumentException();
		}
		this.voornaam = voornaam;
	}
	
	public String getFamilienaam() {
		return familienaam;
	}
	
	public void setFamilienaam(String familienaam) {
		if (! isFamilienaamValid(familienaam)) {
			throw new IllegalArgumentException();
		}
		this.familienaam = familienaam;
	}
	
	public BigDecimal getWedde() {
		return wedde;
	}
	
	public void setWedde(BigDecimal wedde) {
		if (! isWeddeValid(wedde)) {
			throw new IllegalArgumentException();
		}
		this.wedde = wedde;
	}
	
	public long getRijksregisterNr() {
		return rijksregisterNr;
	}
	
	public void setRijksregisterNr(long rijksregisterNr) {
		if (! isRijksregisterNrValid(rijksregisterNr)) {
			throw new IllegalArgumentException();
		}
		this.rijksregisterNr = rijksregisterNr;
	}
	
	public String getNaam() {
		return voornaam + ' ' + familienaam;
	}

	public Geslacht getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(Geslacht geslacht) {
		this.geslacht = geslacht;
	}
	
	public void opslag(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
		wedde = wedde.multiply(factor).setScale(2, RoundingMode.HALF_UP);
	}
	
	
}
