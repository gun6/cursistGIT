package be.vdab.valueobjects;

import java.math.BigDecimal;

public class MandOnderdeel {
	private String naam;
	private BigDecimal prijs;
	private int aantal;
	private int wijnId;
	
	public MandOnderdeel(String land,String soort,int jaar, BigDecimal prijs, int aantal, int wijnId) {
		super();
		this.naam = land + " " + soort + " " + jaar;
		this.prijs = prijs;
		this.aantal = aantal;
		this.wijnId = wijnId;
	}

	public String getNaam() {
		return naam;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public int getAantal() {
		return aantal;
	}

	public int getWijnId() {
		return wijnId;
	}
	
	
}
