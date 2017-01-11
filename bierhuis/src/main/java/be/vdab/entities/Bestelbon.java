package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;;

@Entity
@Table(name = "bestelbonnen")
public class Bestelbon implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	@Embedded
	private Adres adres;
	@ElementCollection
	@CollectionTable(name = "bestelbonlijnen",joinColumns = @JoinColumn(name = "bestelbonid"))
	private List<Bestelbonlijn> bestelbonlijnen;
	
	public Bestelbon() {
	}
	
	public Bestelbon(String naam, String straat, String huisNr, int postcode, String gemeente,
			List<Bestelbonlijn> bestelbonlijnen) {
		this.naam = naam;
		this.adres = new Adres(straat, huisNr, postcode, gemeente);
		this.bestelbonlijnen = bestelbonlijnen;
	}

	public long getId() {
		return id;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public Adres getAdres() {
		return adres;
	}

	public List<Bestelbonlijn> getBestelbonlijnen() {
		return Collections.unmodifiableList(bestelbonlijnen);
	}
	
	public void add(Bestelbonlijn bestelbonlijn) {
		bestelbonlijnen.add(bestelbonlijn);
	}
	
	public void remove(Bestelbonlijn bestelbonlijn) {
		bestelbonlijnen.remove(bestelbonlijn);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bestelbon other = (Bestelbon) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Bestelbon [id=" + id + "]";
	}
	
	
}
