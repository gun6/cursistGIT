package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;

import be.vdab.entities.Artikel;
import be.vdab.entities.Artikelgroepen;
import be.vdab.exceptions.ArtikelBestaatAlException;
import be.vdab.repositories.ArtikelRepository;
import be.vdab.valueobjects.ArtikelLijstObject;

public class ArtikelService extends AbstractService{
	private final ArtikelRepository artikelRepository = new ArtikelRepository();
	
	public Artikel find(long id) {
		return artikelRepository.find(id);
	}
	
	public void create(Artikel artikel) {
		if (artikelRepository.findByExactName(artikel.getNaam()) != null) {
			throw new ArtikelBestaatAlException();
		}
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
	
	public List<Artikel> findAllOrderByName(){
		return artikelRepository.findAllOrderByName();
	}
	
	public List<Artikelgroepen> findAll(){
		return artikelRepository.findAll();
	}
	
	public Artikelgroepen getArtikelgroep(long id){
		return artikelRepository.getArtikelgroep(id);
	}
	
	public List<Artikel> getLijst(){
		return artikelRepository.getLijst();
	}
}
