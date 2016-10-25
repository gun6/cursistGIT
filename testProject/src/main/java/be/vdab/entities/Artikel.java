package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;

import be.vdab.valueobjects.Korting;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="artikels")
@DiscriminatorColumn(name = "soort")
public abstract class Artikel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private BigDecimal aankoopprijs;

	private String naam;

	private BigDecimal verkoopprijs;
	
	@ElementCollection
	@CollectionTable(name = "kortingen",joinColumns = @JoinColumn(name = "artikelid"))
	@OrderBy("vanafAantal")
	private Set<Korting> kortingen;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "artikelgroepid")
	private Artikelgroepen artikelgroepen;
	

	protected Artikel() {
	}

	public Artikel(BigDecimal aankoopprijs, String naam, BigDecimal verkoopprijs) {
		super();
		setAankoopprijs(aankoopprijs);
		setNaam(naam);
		setVerkoopprijs(aankoopprijs,verkoopprijs);
		kortingen = new LinkedHashSet<>();
	}

	public long getId() {
		return this.id;
	}

	public BigDecimal getAankoopprijs() {
		return this.aankoopprijs;
	}

	public void setAankoopprijs(BigDecimal aankoopprijs) {
		if (! isAankoopprijsValid(aankoopprijs)) {
			throw new IllegalArgumentException();
		}
		this.aankoopprijs = aankoopprijs;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		if (! isNaamValid(naam)) {
			throw new IllegalArgumentException();
		}
		this.naam = naam;
	}

	public BigDecimal getVerkoopprijs() {
		return this.verkoopprijs;
	}

	public void setVerkoopprijs(BigDecimal aankoopprijs,BigDecimal verkoopprijs) {
		if (! isVerkoopprijsValid(aankoopprijs, verkoopprijs)) {
			throw new IllegalArgumentException();
		}
		this.verkoopprijs = verkoopprijs;
	}
	
	public Set<Korting> getKortingen() {
		return Collections.unmodifiableSet(kortingen);
	}
	
	public void add(Korting korting) {
		kortingen.add(korting);
	}
	
	public void remove(Korting korting) {
		kortingen.remove(korting);
	}
	
	
	public static boolean isNaamValid(String naam) {
		return naam != null && ! naam.isEmpty();
	}
	
	public static boolean isAankoopprijsValid(BigDecimal prijs) {
		return prijs.compareTo(BigDecimal.valueOf(0.01)) >= 0;
	}
	
	public static boolean isVerkoopprijsValid(BigDecimal aankoopprijs,BigDecimal verkoopprijs) {
		return verkoopprijs.compareTo(aankoopprijs) >= 0;
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
		if (!(obj instanceof Artikel))
			return false;
		Artikel other = (Artikel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Artikelgroepen getArtikelgroepen() {
		return artikelgroepen;
	}

	public void setArtikelgroepen(Artikelgroepen artikelgroepen) {
		if (this.artikelgroepen != null && this.artikelgroepen.getArtikels().contains(this)) {
			this.artikelgroepen.removeArtikel(this);
		}
		this.artikelgroepen = artikelgroepen;
		if (artikelgroepen != null && ! artikelgroepen.getArtikels().contains(this)) {
			artikelgroepen.addArtikel(this);
		}
	}
	
	

}