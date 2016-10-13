package be.vdab.repositories;

import java.math.BigDecimal;
import java.util.List;

import be.vdab.entities.Docent;
import be.vdab.valueobjects.AantalDocentenPerWedde;
import be.vdab.valueobjects.VoornaamEnId;

public class DocentRepository extends AbstractRepository {
	
	public Docent read(long id) {

			return getEntityManager().find(Docent.class, id);

	}
	
	public void create(Docent docent) {
		getEntityManager().persist(docent);
	}
	
	public void delete(long id){
		Docent docent = getEntityManager().find(Docent.class, id);
		if (docent != null) {
			getEntityManager().remove(docent);
		}
	}
	
	public List<Docent> findByWeddeBetween(BigDecimal van,BigDecimal tot,int vanafRij,int aantalRijen) {
		return getEntityManager().createQuery("select d from Docent d where d.wedde between :van and :tot order by d.wedde, d.id",Docent.class)
				.setParameter("van", van)
				.setParameter("tot", tot)
				.setFirstResult(vanafRij)
				.setMaxResults(aantalRijen)
				.getResultList();
	}
	
	public List<VoornaamEnId> findVoornamen() {
		return getEntityManager().createQuery("Select new be.vdab.valueobjects.VoornaamEnId(d.id, d.voornaam) from Docent d",VoornaamEnId.class).getResultList();
	}
	
	public BigDecimal findMaxWedde() {
		return getEntityManager().createQuery("select max(d.wedde) from Docent d",BigDecimal.class).getSingleResult();
	}
	
	public List<AantalDocentenPerWedde> findAantalDocentenPerWedde() {
		return getEntityManager().createQuery("Select new be.vdab.valueobjects.AantalDocentenPerWedde(d.wedde, count(d)) from Docent d group by d.wedde",AantalDocentenPerWedde.class).getResultList();
	}
}