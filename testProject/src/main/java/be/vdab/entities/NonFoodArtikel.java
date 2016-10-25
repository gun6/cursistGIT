package be.vdab.entities;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NF")
public class NonFoodArtikel extends Artikel {
	private static final long serialVersionUID = 1L;
	private long garantie;
	
	public NonFoodArtikel(BigDecimal aankoopprijs, String naam, BigDecimal verkoopprijs,long garantie) {
		super(aankoopprijs,naam,verkoopprijs);
		this.garantie = garantie;
	}
	
	public NonFoodArtikel() {
	}

	public long getGarantie() {
		return garantie;
	}

	public void setGarantie(long garantie) {
		this.garantie = garantie;
	}
	
	
	
}
