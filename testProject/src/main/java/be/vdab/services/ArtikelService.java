package be.vdab.services;

import be.vdab.entities.Artikel;
import be.vdab.repositories.ArtikelRepository;

public class ArtikelService extends AbstractService{
	private final ArtikelRepository artikelRepository = new ArtikelRepository();
	
	public Artikel find(long id) {
		return artikelRepository.find(id);
	}
}
