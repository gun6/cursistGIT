package be.vdab.services;

import java.util.List;

import be.vdab.entities.Album;
import be.vdab.repositories.AlbumRepository;

public class AlbumService extends AbstractService {
	private final AlbumRepository albumRepository = new AlbumRepository();
	
	public List<Album> getAlbumsMetArtiest() {
		return albumRepository.getAlbumsMetArtiest();
	}
	
	public Album getAlbumMetArtiestEnTracks(long id) {
		return albumRepository.getAlbumMetArtiestEnTracks(id);
	}
}
