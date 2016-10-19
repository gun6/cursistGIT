package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.enums.Geslacht;
import be.vdab.valueobjects.TelefoonNr;

@Entity
@Table (name = "docenten")
@NamedEntityGraph(name = "Docent.metCampus",attributeNodes = @NamedAttributeNode("campus"))
public class Docent implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private String voornaam;
	private String familienaam;
	private BigDecimal wedde;
	private long rijksRegisterNr;
	@Enumerated (EnumType.STRING)
	private Geslacht geslacht;
	@ElementCollection
	@CollectionTable(name = "docentenbijnamen",
							joinColumns = @JoinColumn(name = "docentid"))
	@Column(name = "Bijnaam")
	private Set<String> bijnamen;
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "campusid")
	private Campus campus;
	@Version
	private Timestamp versie;
							
	
	
	public Docent(String voornaam, String familienaam, BigDecimal wedde, long rijksregisterNr,
			Geslacht geslacht) {
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.wedde = wedde;
		this.rijksRegisterNr = rijksregisterNr;
		this.geslacht = geslacht;
		bijnamen = new HashSet<>();
		verantwoordelijkheden = new LinkedHashSet<>();
	}
	
	protected Docent() {}
	
	public static boolean isVoornaamValid(String voornaam) {
		return voornaam != null && ! voornaam.isEmpty();
	}
	
	public static boolean isFamilienaamValid(String familienaam) {
		return familienaam != null && ! familienaam.isEmpty();
	}
	
	public static boolean isWeddeValid(BigDecimal wedde) {
		return wedde != null && wedde.compareTo(BigDecimal.ZERO)>=0;
	}
	
	public static boolean isRijksregisterNrValid(long rijksregisterNr) {
		long getal = rijksregisterNr/100;
		if (rijksregisterNr/1000000000 <50) {
			getal += 2000000000;
		}
		return rijksregisterNr % 100 == 97 - getal % 97;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getVoornaam() {
		return voornaam;
	}
	
	public void setVoornaam(String voornaam) {
		if (! isVoornaamValid(voornaam)) {
			throw new IllegalArgumentException();
		}
		this.voornaam = voornaam;
	}
	
	public String getFamilienaam() {
		return familienaam;
	}
	
	public void setFamilienaam(String familienaam) {
		if (! isFamilienaamValid(familienaam)) {
			throw new IllegalArgumentException();
		}
		this.familienaam = familienaam;
	}
	
	public BigDecimal getWedde() {
		return wedde;
	}
	
	public void setWedde(BigDecimal wedde) {
		if (! isWeddeValid(wedde)) {
			throw new IllegalArgumentException();
		}
		this.wedde = wedde;
	}
	
	public long getRijksregisterNr() {
		return rijksRegisterNr;
	}
	
	public void setRijksregisterNr(long rijksregisterNr) {
		if (! isRijksregisterNrValid(rijksregisterNr)) {
			throw new IllegalArgumentException();
		}
		this.rijksRegisterNr = rijksregisterNr;
	}
	
	public String getNaam() {
		return voornaam + ' ' + familienaam;
	}

	public Geslacht getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(Geslacht geslacht) {
		this.geslacht = geslacht;
	}
	
	public void opslag(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
		wedde = wedde.multiply(factor).setScale(2, RoundingMode.HALF_UP);
	}

	public Set<String> getBijnamen() {
		return Collections.unmodifiableSet(bijnamen);
	}
	
	public void addBijnaam(String bijnaam) {
		bijnamen.add(bijnaam);
	}
	
	public void removeBijnaam(String bijnaam) {
		bijnamen.remove(bijnaam);
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Docent)) {
			return false;
		}
		return ((Docent) obj).rijksRegisterNr == rijksRegisterNr;
	}
	
	@Override
	public int hashCode(){
		return Long.valueOf(rijksRegisterNr).hashCode();
	}
	
	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		if (this.campus != null && this.campus.getDocenten().contains(this)) {
			this.campus.remove(this);
		}
		this.campus = campus;
		if (campus != null && ! campus.getDocenten().contains(this)) {
			campus.add(this);
		}
	}
	
	@ManyToMany(mappedBy = "docenten")
	private Set<Verantwoordelijkheid> verantwoordelijkheden = new LinkedHashSet<>();
	
	public void add(Verantwoordelijkheid verantwoordelijkheid) {
		verantwoordelijkheden.add(verantwoordelijkheid);
		if (! verantwoordelijkheid.getDocenten().contains(this)) {
			verantwoordelijkheid.add(this);
		}
	}
	
	public void remove(Verantwoordelijkheid verantwoordelijkheid) {
		verantwoordelijkheden.remove(verantwoordelijkheid);
		if (verantwoordelijkheid.getDocenten().contains(this)) {
			verantwoordelijkheid.remove(this);
		}
	}
	
	public Set<Verantwoordelijkheid> getVerantwoordelijkheden() {
		return Collections.unmodifiableSet(verantwoordelijkheden);
	}
}
