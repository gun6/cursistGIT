package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;

import be.vdab.valueobjects.Bestelbonlijnen;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Entity
public class Bestelbonnen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date besteld;

	private int bestelwijze;

	private String gemeente;

	private String huisNr;

	private String naam;

	private String postCode;

	private String straat;
	
	@ElementCollection
	@CollectionTable(name = "bestelbonlijnen",joinColumns = @JoinColumn(name = "bonid"))
	@OrderBy("wijnid")
	private Set<Bestelbonlijnen> bestelbonlijnen;
	
	@Version
	private int versie;

	public Bestelbonnen() {
	}
	
	

	public Bestelbonnen(Date besteld, int bestelwijze, String gemeente, String huisNr, String naam, String postCode,
			String straat, Set<Bestelbonlijnen> bestelbonlijnen, int versie) {
		super();
		this.besteld = besteld;
		this.bestelwijze = bestelwijze;
		this.gemeente = gemeente;
		this.huisNr = huisNr;
		this.naam = naam;
		this.postCode = postCode;
		this.straat = straat;
		this.bestelbonlijnen = bestelbonlijnen;
		this.versie = versie;
	}



	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getBesteld() {
		return this.besteld;
	}

	public void setBesteld(Date besteld) {
		this.besteld = besteld;
	}

	public int getBestelwijze() {
		return this.bestelwijze;
	}

	public void setBestelwijze(int bestelwijze) {
		this.bestelwijze = bestelwijze;
	}

	public String getGemeente() {
		return this.gemeente;
	}

	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}

	public String getHuisNr() {
		return this.huisNr;
	}

	public void setHuisNr(String huisNr) {
		this.huisNr = huisNr;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getStraat() {
		return this.straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public int getVersie() {
		return this.versie;
	}

	public void setVersie(int versie) {
		this.versie = versie;
	}

	public Set<Bestelbonlijnen> getBestelbonlijnen() {
		return Collections.unmodifiableSet(bestelbonlijnen);
	}
	
	
	public void addBestelbonlijn(Bestelbonlijnen bestelbonlijn) {
		bestelbonlijnen.add(bestelbonlijn);
	}
	
	public void removeBestelbonlijn(Bestelbonlijnen bestelbonlijn) {
		bestelbonlijnen.remove(bestelbonlijn);
	}

	public void setBestelbonlijnen(Set<Bestelbonlijnen> bestelbonlijnen) {
		this.bestelbonlijnen = bestelbonlijnen;
	}



	@Override
	public String toString() {
		return "Bestelbon [id=" + id + "]";
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
		if (!(obj instanceof Bestelbonnen))
			return false;
		Bestelbonnen other = (Bestelbonnen) obj;
		if (id != other.id)
			return false;
		return true;
	}



	
	
	

}