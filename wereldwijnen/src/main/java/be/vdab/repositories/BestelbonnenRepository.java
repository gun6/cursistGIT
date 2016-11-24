package be.vdab.repositories;

import be.vdab.entities.Bestelbonnen;

public class BestelbonnenRepository extends AbstractRepository {
	
	public void create(Bestelbonnen bestelbon) {
		getEntityManager().persist(bestelbon);
	}
	

}
