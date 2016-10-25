package be.vdab.repositories;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.NoResultException;

import be.vdab.entities.Artikel;
import be.vdab.entities.Artikelgroepen;

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
	
	public List<Artikel> findAllOrderByName() {
		return getEntityManager().createNamedQuery("Artikel.findAllOrderByName", Artikel.class).getResultList();
		
	}
	
	public Artikel findByExactName(String naam) {
		try {
			return getEntityManager().createNamedQuery("Artikel.findByExactName", Artikel.class)
					.setParameter("naam", naam)
					.getSingleResult();
		} 
		catch (NoResultException ex) {
			return null;
		}
	}
	
	public List<Artikelgroepen> findAll() {
		return getEntityManager().createNamedQuery("Artikelgroepen.findAll", Artikelgroepen.class).getResultList();
	}
	
	public Artikelgroepen getArtikelgroep(long id) {
		return getEntityManager().find(Artikelgroepen.class, id);
	}
}
