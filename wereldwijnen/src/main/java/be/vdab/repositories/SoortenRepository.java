package be.vdab.repositories;

import java.util.List;

import be.vdab.entities.Landen;
import be.vdab.entities.Soorten;

public class SoortenRepository extends AbstractRepository {
	
	public List<Soorten> findAllOfLand(long land) {
		return getEntityManager().createNamedQuery("Soorten.findAllOfLand", Soorten.class)
				.setParameter("land", land).getResultList();
	}
	
	public Soorten read(long id) {
		return getEntityManager().find(Soorten.class, id);
	}

}
