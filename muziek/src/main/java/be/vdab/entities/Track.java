package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tracks database table.
 * 
 */
@Entity
@Table(name="tracks")
@NamedQuery(name="Track.findAll", query="SELECT t FROM Track t")
public class Track implements Serializable {
	private static final long serialVersionUID = 1L;

	private String naam;

	private BigDecimal tijd;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="albumid")
	private Album album;

	public Track() {
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public BigDecimal getTijd() {
		return this.tijd;
	}

	public void setTijd(BigDecimal tijd) {
		this.tijd = tijd;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

}