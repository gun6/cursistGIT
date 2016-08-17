package be.vdab.services;

import javax.persistence.EntityManager;

import be.vdab.dao.DocentDAO;
import be.vdab.entities.Docent;
import be.vdab.filters.JPAFilter;

/**
 * 		8 Transacties & Service layer
 * 
 * 			Transacties zijn nodig bij het toevoegen, verwijderen of wijzigen van records.
 * 
 * 			Je beheert de transactie van een EntityManager met de method getTransaction:
 * 				- entityManager.getTransaction().begin();
 * 				- entityManager.getTransaction().commit();
 * 				- entityManager.getTransaction().rollback();
 * 
 * 			Service layer bevat het transactiebeheer met 1 service class per entity.
 * 
 * 			1. Presentation layer (servlet, jsp) roept een service method op
 * 			2. Service method maakt een EM en start daarop een transaction
 * 			3. Service method roept 1/meer DAO methods op
 * 			4. Service method doet commit/rollback
 * 
 * 			DocentService.java
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

public class DocentService 
{
	private final DocentDAO docentDAO = new DocentDAO();
	
	public Docent read(long id) 
	{
		EntityManager entityManager = JPAFilter.getEntityManager();
		
		try 
		{
			return docentDAO.read(id, entityManager);
		} 
		finally
		{
			entityManager.close();
		}
	}
	
	public void create(Docent docent) 
	{
		EntityManager entityManager = JPAFilter.getEntityManager();
		
		try 
		{
			entityManager.getTransaction().begin();
			docentDAO.create(docent, entityManager);
			entityManager.getTransaction().commit();
		} 
		catch (RuntimeException ex) 
		{
			entityManager.getTransaction().rollback();
			throw ex;
		} 
		finally 
		{
			entityManager.close();
		}
	}
	
	public void delete(long id) 
	{
		EntityManager entityManager = JPAFilter.getEntityManager();
		
		try 
		{
			entityManager.getTransaction().begin();
			docentDAO.delete(id, entityManager);
			entityManager.getTransaction().commit();
		} 
		catch (RuntimeException ex)
		{
			entityManager.getTransaction().rollback();
			throw ex;
		}
		finally 
		{
			entityManager.close();
		}
	}
}
