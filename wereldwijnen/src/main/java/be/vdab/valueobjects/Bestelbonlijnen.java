package be.vdab.valueobjects;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


@Embeddable
public class Bestelbonlijnen implements Serializable {
	private static final long serialVersionUID = 1L;

	private int aantal;

	private BigDecimal prijs;

	private int wijnid;

	public Bestelbonlijnen() {
	}
	
	

	public Bestelbonlijnen(int aantal, BigDecimal prijs, int wijnid) {
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

	public int getWijnid() {
		return this.wijnid;
	}

}