package be.vdab.dao;

import javax.persistence.EntityManager;

import be.vdab.entities.Docent;

/**
 * 		5 Entity zoeken via de primary key
 * 			
 * 			EM bevat .find() die zoekt Entity op PK in db
 */

/**
 * 		8 Transacties & Service layer
 * 
 * 			Wijzig .read()
 * 
 *   		DocentService.java
 * 			DocentDAO.java
 * 			ZoekenServlet.java
 */

/**
 * 		9 Entity toevoegen
 *
 *			EM voegt een entity toe aan de db met .persist(entity)
 *			De method stuurt een sql insert naar de db
 *
 *			Docent.java
 *				public Docent()	{}		protected Docent() {}
 *				isVoornaamValid()		isFamilienaamValid()
 *				isWeddeValid()			isRijksRegisterNrValid()
 *				setVoornaam()			setFamilienaam()
 *				setWedde()				setGeslacht()
 *				setRijksRegisterNr()
 *
 *			DocentDAO.java
 *				create()
 *
 *			DocentService.java
 *				create()
 *
 *			ToevoegenServlet.java
 *
 *			toevoegen.jsp
 */

/**
 * 		10 Entity verwijderen
 *
 *			DocentDAO.java
 *				delete()
 *
 *			DocentService.java
 *				delete()
 *
 *			zoeken.jsp
 *				Verwijderen button
 *
 *			DocentVerwijderenServlet.java
 *				verwerkt POST requests naar /docenten/verwijderen.htm
 */

public class DocentDAO 
{
	public Docent read(long id, EntityManager entityManager)
	{
		return entityManager.find(Docent.class, id);
	}
	
	public void create(Docent docent, EntityManager entityManager) 
	{
		entityManager.persist(docent);
	}
	
	public void delete(long id, EntityManager entityManager) 
	{
		Docent docent = entityManager.find(Docent.class, id);
		
		if (docent != null) 
		{
			entityManager.remove(docent);
		}
	}
}
