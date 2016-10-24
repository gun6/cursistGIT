package be.vdab.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NF")
public class NonFoodArtikel extends Artikel {
	private static final long serialVersionUID = 1L;
	private int garantie;
}
