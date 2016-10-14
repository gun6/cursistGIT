package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;

import be.vdab.valueobjects.Adres;

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

	protected Campus() {}
	
	

	public Campus(String naam, Adres adres) {
		super();
		this.naam = naam;
		this.adres = adres;
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
	
	
   
}
