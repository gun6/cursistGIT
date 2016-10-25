package be.vdab.entities;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue("F")
public class FoodArtikel extends Artikel {
	private static final long serialVersionUID = 1L;
	private long houdbaarheid;
	
	public FoodArtikel(BigDecimal aankoopprijs, String naam, BigDecimal verkoopprijs,long houdbaarheid) {
		super(aankoopprijs,naam,verkoopprijs);
		this.houdbaarheid = houdbaarheid;
	}

	public FoodArtikel() {
	}

	public long getHoudbaarheid() {
		return houdbaarheid;
	}

	public void setHoudbaarheid(long houdbaarheid) {
		this.houdbaarheid = houdbaarheid;
	}
	
	
	
	
}
