package be.vdab.valueobjects;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


@Embeddable
public class Bestelbonlijnen implements Serializable {
	private static final long serialVersionUID = 1L;

	private int aantal;

	private int bonid;

	private BigDecimal prijs;

	private int wijnid;

	public Bestelbonlijnen() {
	}
	
	

	public Bestelbonlijnen(int aantal, int bonid, BigDecimal prijs, int wijnid) {
		super();
		this.aantal = aantal;
		this.bonid = bonid;
		this.prijs = prijs;
		this.wijnid = wijnid;
	}



	public int getAantal() {
		return this.aantal;
	}

	public int getBonid() {
		return this.bonid;
	}

	public BigDecimal getPrijs() {
		return this.prijs;
	}

	public int getWijnid() {
		return this.wijnid;
	}

}