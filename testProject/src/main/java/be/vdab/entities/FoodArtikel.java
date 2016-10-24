package be.vdab.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue("F")
public class FoodArtikel extends Artikel {
	private static final long serialVersionUID = 1L;
	private int houdbaarheid;
}
