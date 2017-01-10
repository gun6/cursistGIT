package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Bier;

@Embeddable
public class Bestelbonlijn implements Serializable {

	private static final long serialVersionUID = 1L;
	private long aantal;
	private BigDecimal prijs;
	@ManyToOne(optional = false)
	@JoinColumn(name = "bierid")
	private Bier bier;
	
	public Bestelbonlijn() {
	}
	
	public Bestelbonlijn(long aantal, BigDecimal prijs, Bier bier) {
		this.aantal = aantal;
		this.prijs = prijs;
		this.bier = bier;
	}

	public long getAantal() {
		return aantal;
	}
	
	public BigDecimal getPrijs() {
		return prijs;
	}
	
	public Bier getBier() {
		return bier;
	}
	
	public void setAantal(long aantal) {
		this.aantal = aantal;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	public void setBier(Bier bier) {
		this.bier = bier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (aantal ^ (aantal >>> 32));
		result = prime * result + ((bier == null) ? 0 : bier.hashCode());
		result = prime * result + ((prijs == null) ? 0 : prijs.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bestelbonlijn other = (Bestelbonlijn) obj;
		if (aantal != other.aantal)
			return false;
		if (bier == null) {
			if (other.bier != null)
				return false;
		} else if (!bier.equals(other.bier))
			return false;
		if (prijs == null) {
			if (other.prijs != null)
				return false;
		} else if (!prijs.equals(other.prijs))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Bestelbonlijn [aantal=" + aantal + ", prijs=" + prijs + ", bier=" + bier + "]";
	}
	
	
}
