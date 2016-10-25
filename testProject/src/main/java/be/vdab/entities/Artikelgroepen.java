package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;



@Entity
public class Artikelgroepen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String naam;

	@OneToMany(mappedBy="artikelgroepen")
	private List<Artikel> artikels;

	public Artikelgroepen() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public List<Artikel> getArtikels() {
		return this.artikels;
	}

	public void setArtikels(List<Artikel> artikels) {
		this.artikels = artikels;
	}

	public Artikel addArtikel(Artikel artikel) {
		getArtikels().add(artikel);
		if (artikel.getArtikelgroepen() != this) {
			artikel.setArtikelgroepen(this);
		}
		return artikel;
	}

	public Artikel removeArtikel(Artikel artikel) {
		getArtikels().remove(artikel);
		if (artikel.getArtikelgroepen() == this){
			artikel.setArtikelgroepen(null);
		}
		return artikel;
	}

}