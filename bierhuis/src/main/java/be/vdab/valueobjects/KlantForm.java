package be.vdab.valueobjects;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

public class KlantForm {
	@NotBlank
	@SafeHtml
	private String naam;
	@NotBlank
	@SafeHtml
	private String straat;
	@Pattern(regexp="\\d.*")
	private String huisNr;
	@Range(min = 1000, max = 9999)
	private int postcode;
	@NotBlank
	@SafeHtml
	private String gemeente;
	
	public String getNaam() {
		return naam;
	}
	
	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public String getHuisNr() {
		return huisNr;
	}

	public void setHuisNr(String huisNr) {
		this.huisNr = huisNr;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getGemeente() {
		return gemeente;
	}

	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}
	
	
	
}
