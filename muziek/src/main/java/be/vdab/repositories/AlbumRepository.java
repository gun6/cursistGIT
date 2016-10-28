package be.vdab.repositories;

import java.util.List;

import be.vdab.entities.Album;

public class AlbumRepository extends AbstractRepository {
	
	public List<Album> getAlbumsMetArtiest() {
		return getEntityManager().createNamedQuery("Album.findall", Album.class)
				.setHint("javax.persistence.loadgraph", getEntityManager().createEntityGraph("Album.metArtiest"))
				.getResultList();
	}
	
	public Album getAlbumMetArtiestEnTracks(long id) {
		return getEntityManager().createNamedQuery("Album.findone", Album.class)
				.setParameter("id", id)
				.setHint("javax.persistence.loadgraph", getEntityManager().createEntityGraph("Album.metArtiestEnTracks"))
				.getSingleResult();
	}
}
