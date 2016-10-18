package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.net.ssl.ManagerFactoryParameters;
import javax.persistence.*;

import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.TelefoonNr;

@Entity
@Table (name="campussen")
public class Campus implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	@Embedded
	private Adres adres;
	@ElementCollection
	@CollectionTable(name = "campussentelefoonnrs",joinColumns = @JoinColumn(name = "campusid"))
	@OrderBy("fax")
	private Set<TelefoonNr> telefoonNrs;
	@OneToMany(mappedBy = "campus")
	//@JoinColumn(name = "campusid")
	@OrderBy("voornaam,familienaam")
	private Set<Docent> docenten;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "managerid")
	private Manager manager;

	protected Campus() {}
	
	

	public Campus(String naam, Adres adres) {
		super();
		this.naam = naam;
		this.adres = adres;
		telefoonNrs = new LinkedHashSet<>();
		docenten = new LinkedHashSet<>();
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	
	public Manager getManager() {
		return manager;
	}

	public Set<TelefoonNr> getTelefoonNrs() {
		return Collections.unmodifiableSet(telefoonNrs);
	}
	
	public void add(TelefoonNr telefoonNr) {
		telefoonNrs.add(telefoonNr);
	}
   
	public void remove(TelefoonNr telefoonNr) {
		telefoonNrs.remove(telefoonNr);
	}
	
	public Set<Docent> getDocenten() {
		return Collections.unmodifiableSet(docenten);
	}
	
	public void add(Docent docent) {
		docenten.add(docent);
		if (docent.getCampus() != this) {
			docent.setCampus(this);
		}
	}
	
	public void remove(Docent docent) {
		docenten.remove(docent);
		if (docent.getCampus() == this) {
			docent.setCampus(null);
		}
	}
}
