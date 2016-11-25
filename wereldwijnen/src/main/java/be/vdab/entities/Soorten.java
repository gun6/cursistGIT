package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Entity
public class Soorten implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String naam;

	@Version
	private int versie;

	//bi-directional many-to-one association to Landen
	@ManyToOne
	@JoinColumn(name="landid")
	private Landen landen;

	//bi-directional many-to-one association to Wijnen
	@OneToMany(mappedBy="soorten")
	private List<Wijnen> wijnens;

	public Soorten() {
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

	public int getVersie() {
		return this.versie;
	}

	public void setVersie(int versie) {
		this.versie = versie;
	}

	public Landen getLanden() {
		return this.landen;
	}

	public void setLanden(Landen landen) {
		this.landen = landen;
	}

	public List<Wijnen> getWijnens() {
		return this.wijnens;
	}

	public void setWijnens(List<Wijnen> wijnens) {
		this.wijnens = wijnens;
	}

	public Wijnen addWijnen(Wijnen wijnen) {
		getWijnens().add(wijnen);
		wijnen.setSoorten(this);

		return wijnen;
	}

	public Wijnen removeWijnen(Wijnen wijnen) {
		getWijnens().remove(wijnen);
		wijnen.setSoorten(null);

		return wijnen;
	}

	@Override
	public String toString() {
		return "Soort [naam=" + naam + "]";
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
		if (!(obj instanceof Soorten))
			return false;
		Soorten other = (Soorten) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	
	

}