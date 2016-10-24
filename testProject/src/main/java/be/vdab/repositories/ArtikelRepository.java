package be.vdab.repositories;

import java.math.BigDecimal;
import java.util.List;

import be.vdab.entities.Artikel;

public class ArtikelRepository extends AbstractRepository{
	
	public Artikel find(long id) {
		return getEntityManager().find(Artikel.class, id);
	}
	
	public void create(Artikel artikel){
		getEntityManager().persist(artikel);
	}
	
	public List<Artikel> findByName(String naam,int vanafRij,int aantalRijen) {
		return getEntityManager().createNamedQuery("Artikel.findByName", Artikel.class)
				.setParameter("naam",'%'+ naam+ '%')
				.setFirstResult(vanafRij)
				.setMaxResults(aantalRijen)
				.getResultList();
	}
	
	public void prijsverhoging(BigDecimal factor) {
		getEntityManager().createNamedQuery("Artikel.prijsverhoging")
		.setParameter("factor", factor)
		.executeUpdate();
	}
}
