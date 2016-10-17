package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "verantwoordelijkheden")

public class Verantwoordelijkheid implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	
	public long getId() {
		return id;
	}
	public String getNaam() {
		return naam;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Verantwoordelijkheid))
			return false;
		Verantwoordelijkheid other = (Verantwoordelijkheid) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}
	
	@ManyToMany
	@JoinTable(name= "docentenverantwoordelijkheden",
				joinColumns = @JoinColumn(name= "verantwoodelijkheidid"),
				inverseJoinColumns = @JoinColumn(name = "docentid"))
	private Set<Docent> docenten = new LinkedHashSet<>();
	
	public void add(Docent docent) {
		docenten.add(docent);
		if (! docent.getVerantwoordelijkheden().contains(this)) {
			docent.add(this);
		}
	}
	
	public void remove(Docent docent) {
		docenten.remove(docent);
		if (docent.getVerantwoordelijkheden().contains(this)) {
			docent.remove(this);
		}
	}

	public Set<Docent> getDocenten() {
		return Collections.unmodifiableSet(docenten);
	}
   
}
