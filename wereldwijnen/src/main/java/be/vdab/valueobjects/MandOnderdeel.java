package be.vdab.valueobjects;

import java.math.BigDecimal;

public class MandOnderdeel {
	private String naam;
	private Bestelbonlijnen bestelbonlijn;
	
	
	public MandOnderdeel(String land,String soort,int jaar, Bestelbonlijnen bestelbonlijn) {
		super();
		this.naam = land + " " + soort + " " + jaar;
		this.bestelbonlijn = bestelbonlijn;
	}

	public String getNaam() {
		return naam;
	}

	public BigDecimal getPrijs() {
		return bestelbonlijn.getPrijs();
	}

	public int getAantal() {
		return bestelbonlijn.getAantal();
	}

	public long getWijnId() {
		return bestelbonlijn.getWijnid();
	}
	
	public Bestelbonlijnen getBestelbonlijn() {
		return bestelbonlijn;
	}

	@Override
	public String toString() {
		return "MandOnderdeel [naam=" + naam + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bestelbonlijn == null) ? 0 : bestelbonlijn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MandOnderdeel))
			return false;
		MandOnderdeel other = (MandOnderdeel) obj;
		if (bestelbonlijn == null) {
			if (other.bestelbonlijn != null)
				return false;
		} else if (!bestelbonlijn.equals(other.bestelbonlijn))
			return false;
		return true;
	}

	
	
	
}
