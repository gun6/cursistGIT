package be.vdab.repositories;

import java.util.List;

import be.vdab.entities.Soorten;
import be.vdab.entities.Wijnen;

public class WijnenRepository extends AbstractRepository {
	
	public List<Wijnen> findAllOfSoort(long soort) {
		return getEntityManager().createNamedQuery("Wijnen.findAllOfSoort", Wijnen.class)
				.setParameter("soort", soort).getResultList();
	}
	
	public Wijnen getWijnMetSoortEnLand(long id) {
		return getEntityManager().createNamedQuery("wijnen.findWijn",Wijnen.class)
		.setParameter("id", id).setHint("javax.persistence.loadgraph", getEntityManager().createEntityGraph("Wijnen.metSoortenEnLanden"))
		.getSingleResult();
	}
	
	public Wijnen getWijn(long id) {
		return getEntityManager().createNamedQuery("wijnen.findWijn",Wijnen.class)
				.setParameter("id", id).getSingleResult();		
	}

}
