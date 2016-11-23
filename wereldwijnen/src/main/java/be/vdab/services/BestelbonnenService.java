package be.vdab.services;

import be.vdab.entities.Bestelbonnen;
import be.vdab.repositories.BestelbonnenRepository;

public class BestelbonnenService extends AbstractService {
	private final BestelbonnenRepository bestelbonnenRepository = new BestelbonnenRepository();
	
	public void create(Bestelbonnen bestelbon) {
		beginTransaction();
		bestelbonnenRepository.create(bestelbon);
		commit();
	}

}
