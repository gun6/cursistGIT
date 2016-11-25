package be.vdab.repositories;

import java.util.List;

import be.vdab.entities.Landen;

public class LandenRepository extends AbstractRepository {
	
	public List<Landen> findAll() {
		return getEntityManager().createNamedQuery("Landen.findAll", Landen.class).getResultList();
	}
	
	public Landen read(long id) {
		return getEntityManager().find(Landen.class, id);
	}

}
