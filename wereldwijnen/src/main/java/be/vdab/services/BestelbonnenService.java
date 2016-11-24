package be.vdab.services;

import javax.persistence.OptimisticLockException;
import javax.persistence.RollbackException;

import be.vdab.entities.Bestelbonnen;
import be.vdab.entities.Wijnen;
import be.vdab.exceptions.RecordAangepastException;
import be.vdab.repositories.BestelbonnenRepository;
import be.vdab.repositories.WijnenRepository;
import be.vdab.valueobjects.Bestelbonlijnen;

public class BestelbonnenService extends AbstractService {
	private final BestelbonnenRepository bestelbonnenRepository = new BestelbonnenRepository();
	private final WijnenRepository wijnenRepository = new WijnenRepository();
	
	public void create(Bestelbonnen bestelbon) {
		beginTransaction();
		bestelbonnenRepository.create(bestelbon);
		for (Bestelbonlijnen lijn : bestelbon.getBestelbonlijnen()) {
			Wijnen wijn = wijnenRepository.getWijn(lijn.getWijnid());
			int aantal = wijn.getInBestelling() + lijn.getAantal();
			wijn.setInBestelling(aantal);
		}
		try {
			commit();
		} 
		catch (RollbackException e) {
			if (e.getCause() instanceof OptimisticLockException) {
				throw new RecordAangepastException();
			}
		}
		
	}
	

}
