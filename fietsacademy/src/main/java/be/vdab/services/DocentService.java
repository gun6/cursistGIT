package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;

import be.vdab.entities.Docent;
import be.vdab.exceptions.DocentBestaatAlException;
import be.vdab.repositories.DocentRepository;
import be.vdab.valueobjects.AantalDocentenPerWedde;
import be.vdab.valueobjects.VoornaamEnId;

public class DocentService extends AbstractService {
	private final DocentRepository docentRepository = new DocentRepository();
	
	public Docent read(long id) {
		return docentRepository.read(id);
	}
	
	public void create(Docent docent) {
		if (docentRepository.findByRijksRegisterNr(docent.getRijksregisterNr()) != null) {
			throw new DocentBestaatAlException();
		}
		beginTransaction();
		docentRepository.create(docent);
		commit();
	}
	
	public void delete(long id) {
		beginTransaction();
		docentRepository.delete(id);
		commit();
	}
	
	public void opslag(long id,BigDecimal percentage) {
		beginTransaction();
		docentRepository.read(id).opslag(percentage);
		commit();

	}
	
	public List<Docent> findByWeddeBetween(BigDecimal van,BigDecimal tot,int vanafRij,int aantalRijen) {
		return docentRepository.findByWeddeBetween(van, tot,vanafRij,aantalRijen);
	}
	
	public List<VoornaamEnId> findVoornamen() {
		return docentRepository.findVoornamen();
	}
	
	public BigDecimal findMaxWedde() {
		return docentRepository.findMaxWedde();
	}
	
	public List<AantalDocentenPerWedde> findAantalDocentenPerWedde() {
		return docentRepository.findAantalDocentenPerWedde();
	}
	
	public void algemeneOpslag(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
		beginTransaction();
		docentRepository.algemeneOpslag(factor);
		commit();
	}
	
	public void bijnaamToevoegen(long id,String bijnaam) {
		beginTransaction();
		docentRepository.read(id).addBijnaam(bijnaam);
		commit();
	}
	
	public void bijnamenVerwijderen(long id,String[] bijnamen) {
		beginTransaction();
		Docent docent = docentRepository.read(id);
		for (String bijnaam : bijnamen) {
			docent.removeBijnaam(bijnaam);
		}
		commit();
	}
}