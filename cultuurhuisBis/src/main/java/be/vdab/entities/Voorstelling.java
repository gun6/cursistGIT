package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Voorstelling implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date datum;
	private String titel;
	private String uitvoerders;
	private BigDecimal prijs;
	private int vrijePlaatsen;
	
	public Voorstelling(Long id, Date datum, String titel, String uitvoerders, BigDecimal prijs, int vrijePlaatsen) {
		this.id = id;
		this.datum = datum;
		this.titel = titel;
		this.uitvoerders = uitvoerders;
		this.prijs = prijs;
		this.vrijePlaatsen = vrijePlaatsen;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getUitvoerders() {
		return uitvoerders;
	}

	public void setUitvoerders(String uitvoerders) {
		this.uitvoerders = uitvoerders;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	public int getVrijePlaatsen() {
		return vrijePlaatsen;
	}

	public void setVrijePlaatsen(int vrijePlaatsen) {
		this.vrijePlaatsen = vrijePlaatsen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Voorstelling [datum=" + datum + ", titel=" + titel + ", uitvoerders=" + uitvoerders + ", prijs=" + prijs
				+ ", vrijePlaatsen=" + vrijePlaatsen + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Voorstelling))
			return false;
		Voorstelling other = (Voorstelling) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
