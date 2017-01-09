package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bieren")
public class Bier implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	@ManyToOne(optional = false)
	@JoinColumn(name = "brouwerid")
	private Brouwer brouwer;
	@ManyToOne(optional = false)
	@JoinColumn(name = "soortid")
	private Soort soort;
	private BigDecimal alcohol;
	private BigDecimal prijs;
	
	public long getId() {
		return id;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public Brouwer getBrouwer() {
		return brouwer;
	}
	
	public Soort getSoort() {
		return soort;
	}
	
	public BigDecimal getAlcohol() {
		return alcohol;
	}
	
	public BigDecimal getPrijs() {
		return prijs;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alcohol == null) ? 0 : alcohol.hashCode());
		result = prime * result + ((brouwer == null) ? 0 : brouwer.hashCode());
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		result = prime * result + ((soort == null) ? 0 : soort.hashCode());
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
		Bier other = (Bier) obj;
		if (alcohol == null) {
			if (other.alcohol != null)
				return false;
		} else if (!alcohol.equals(other.alcohol))
			return false;
		if (brouwer == null) {
			if (other.brouwer != null)
				return false;
		} else if (!brouwer.equals(other.brouwer))
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		if (soort == null) {
			if (other.soort != null)
				return false;
		} else if (!soort.equals(other.soort))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return naam + " " + alcohol + " " + prijs;
	}
	
	

}
