package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the albums database table.
 * 
 */
@Entity
@Table(name="albums")
@NamedEntityGraphs({
@NamedEntityGraph(name = "Album.metArtiest",attributeNodes = @NamedAttributeNode("artiesten")),
@NamedEntityGraph(name = "Album.metArtiestEnTracks",attributeNodes = { @NamedAttributeNode("artiesten"), @NamedAttributeNode("tracks")})
})
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

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
	
	public BigDecimal getTijd() {
		BigDecimal tijd = BigDecimal.ZERO;
		for (Track track : tracks) {
			tijd=tijd.add(track.getTijd());
		}
		return tijd;
	}

}