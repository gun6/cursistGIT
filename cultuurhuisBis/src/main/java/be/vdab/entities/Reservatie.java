package be.vdab.entities;

public class Reservatie {
	private long klantId;
	private long voorstellingsId;
	private int aantalPlaatsen;
	
	public Reservatie(long klantId, long voorstellingsId, int aantalPlaatsen) {
		this.klantId = klantId;
		this.voorstellingsId = voorstellingsId;
		this.aantalPlaatsen = aantalPlaatsen;
	}

	public long getKlantId() {
		return klantId;
	}

	public void setKlantId(long klantId) {
		this.klantId = klantId;
	}

	public long getVoorstellingsId() {
		return voorstellingsId;
	}

	public void setVoorstellingsId(long voorstellingsId) {
		this.voorstellingsId = voorstellingsId;
	}

	public int getAantalPlaatsen() {
		return aantalPlaatsen;
	}

	public void setAantalPlaatsen(int aantalPlaatsen) {
		this.aantalPlaatsen = aantalPlaatsen;
	}

	@Override
	public String toString() {
		return "Reservatie [klantId=" + klantId + ", voorstellingsId=" + voorstellingsId + ", aantalPlaatsen="
				+ aantalPlaatsen + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aantalPlaatsen;
		result = prime * result + (int) (klantId ^ (klantId >>> 32));
		result = prime * result + (int) (voorstellingsId ^ (voorstellingsId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Reservatie))
			return false;
		Reservatie other = (Reservatie) obj;
		if (aantalPlaatsen != other.aantalPlaatsen)
			return false;
		if (klantId != other.klantId)
			return false;
		if (voorstellingsId != other.voorstellingsId)
			return false;
		return true;
	}
	
	
	
	
}
