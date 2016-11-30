package be.vdab.valueobjects;

import be.vdab.entities.Voorstelling;

public class Reservering {
	private final Voorstelling voorstelling;
	private final int aantalPlaatsen;
	
	public Reservering(Voorstelling voorstelling, int aantalPlaatsen) {
		this.voorstelling = voorstelling;
		this.aantalPlaatsen = aantalPlaatsen;
	}

	public Voorstelling getVoorstelling() {
		return voorstelling;
	}

	public int getAantalPlaatsen() {
		return aantalPlaatsen;
	}

	@Override
	public String toString() {
		return "Reservering [voorstelling=" + voorstelling + ", aantalPlaatsen=" + aantalPlaatsen + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((voorstelling == null) ? 0 : voorstelling.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Reservering))
			return false;
		Reservering other = (Reservering) obj;
		if (voorstelling == null) {
			if (other.voorstelling != null)
				return false;
		} else if (!voorstelling.equals(other.voorstelling))
			return false;
		return true;
	}
	
	
	
}
