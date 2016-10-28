package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the artiesten database table.
 * 
 */
@Entity
@NamedQuery(name="Artiesten.findAll", query="SELECT a FROM Artiesten a")
public class Artiesten implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String naam;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="artiesten")
	private Set<Album> albums;

	public Artiesten() {
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

	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public Album addAlbum(Album album) {
		getAlbums().add(album);
		album.setArtiesten(this);

		return album;
	}

	public Album removeAlbum(Album album) {
		getAlbums().remove(album);
		album.setArtiesten(null);

		return album;
	}

}