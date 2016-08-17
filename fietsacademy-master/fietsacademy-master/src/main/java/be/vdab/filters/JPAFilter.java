package be.vdab.filters;

import java.io.IOException;

import javax.persistence.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

/**
 * 		2.4 JPAFilter & interface EntityManagerFactory
 * 			
 * 			JPA leest persistence.xml 1x bij initialisatie via JPAFilter
 * 			-> creatie object emf (als een static variabele dus voor de ganse duur vd app)
 *			-> emf.close() (sluit de db connections en de pool)
 */

/**
 * 		4	EntityManager is een JPA interface dmv static method .getEntityManager()
 * 
 * 			- Entities toevoegen als nieuwe records aan de database
 * 			- Records lezen als entities
 * 			- Records wijzigen die bij entities horen
 * 			- Records verwijderen die bij entities horen
 * 			- Transacties beheren
 */

@WebFilter("*.htm")
public class JPAFilter implements Filter 
{
	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("fietsacademy");
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}
	
	public static EntityManager getEntityManager() 
	{
		return entityManagerFactory.createEntityManager();
	}
	
	@Override
	public void destroy() 
	{
		entityManagerFactory.close();
	}
}
