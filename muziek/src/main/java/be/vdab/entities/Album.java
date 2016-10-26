package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the albums database table.
 * 
 */
@Entity
@Table(name="albums")
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String naam;

	//bi-directional many-to-one association to Artiesten
	@ManyToOne
	@JoinColumn(name="artiestid")
	private Artiesten artiesten;

	//bi-directional many-to-one association to Track
	@OneToMany(mappedBy="album")
	private Set<Track> tracks;

	public Album() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Artiesten getArtiesten() {
		return this.artiesten;
	}

	public void setArtiesten(Artiesten artiesten) {
		this.artiesten = artiesten;
	}

	public Set<Track> getTracks() {
		return this.tracks;
	}

	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}

	public Track addTrack(Track track) {
		getTracks().add(track);
		track.setAlbum(this);

		return track;
	}

	public Track removeTrack(Track track) {
		getTracks().remove(track);
		track.setAlbum(null);

		return track;
	}

}