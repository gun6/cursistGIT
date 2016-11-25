package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Entity
public class Landen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String naam;

	@Version
	private int versie;

	//bi-directional many-to-one association to Soorten
	@OneToMany(mappedBy="landen")
	private List<Soorten> soortens;

	public Landen() {
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

	public List<Soorten> getSoortens() {
		return this.soortens;
	}

	public void setSoortens(List<Soorten> soortens) {
		this.soortens = soortens;
	}

	public Soorten addSoorten(Soorten soorten) {
		getSoortens().add(soorten);
		soorten.setLanden(this);

		return soorten;
	}

	public Soorten removeSoorten(Soorten soorten) {
		getSoortens().remove(soorten);
		soorten.setLanden(null);

		return soorten;
	}

	@Override
	public String toString() {
		return "Land [naam=" + naam + "]";
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
		if (!(obj instanceof Landen))
			return false;
		Landen other = (Landen) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	

}