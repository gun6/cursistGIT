package be.vdab.valueobjects;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


@Embeddable
public class Bestelbonlijnen implements Serializable {
	private static final long serialVersionUID = 1L;

	private int aantal;

	private BigDecimal prijs;

	private long wijnid;

	public Bestelbonlijnen() {
	}
	
	

	public Bestelbonlijnen(int aantal, BigDecimal prijs, long wijnid) {
		super();
		this.aantal = aantal;
		this.prijs = prijs;
		this.wijnid = wijnid;
	}



	public int getAantal() {
		return this.aantal;
	}

	public BigDecimal getPrijs() {
		return this.prijs;
	}

	public long getWijnid() {
		return this.wijnid;
	}



	@Override
	public String toString() {
		return "Bestelbonlijnen [aantal=" + aantal + ", prijs=" + prijs + ", wijnid=" + wijnid + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (wijnid ^ (wijnid >>> 32));
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Bestelbonlijnen))
			return false;
		Bestelbonlijnen other = (Bestelbonlijnen) obj;
		if (wijnid != other.wijnid)
			return false;
		return true;
	}

	

	
	
	

}