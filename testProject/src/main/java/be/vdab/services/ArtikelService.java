package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;

import be.vdab.entities.Artikel;
import be.vdab.repositories.ArtikelRepository;

public class ArtikelService extends AbstractService{
	private final ArtikelRepository artikelRepository = new ArtikelRepository();
	
	public Artikel find(long id) {
		return artikelRepository.find(id);
	}
	
	public void create(Artikel artikel) {
		beginTransaction();
		artikelRepository.create(artikel);
		commit();
	}
	
	public List<Artikel> findByName(String naam,int vanafRij,int aantalRijen){
		return artikelRepository.findByName(naam,vanafRij,aantalRijen);
	}
	
	public void prijsverhoging(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
		beginTransaction();
		artikelRepository.prijsverhoging(factor);
		commit();
	}
}
