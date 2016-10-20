package be.vdab.repositories;

import be.vdab.entities.Artikel;

public class ArtikelRepository extends AbstractRepository{
	
	public Artikel find(long id) {
		return getEntityManager().find(Artikel.class, id);
	}
}
