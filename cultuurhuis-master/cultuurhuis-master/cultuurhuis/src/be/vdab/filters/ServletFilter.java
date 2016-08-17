package be.vdab.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*		@WebFilter("*.htm")
 * 		Een filter onderschept een browser request op het moment dat die binnenkomt vóór de webserver de request naar één van de servlets stuurt.
 * 
 * 		@WebFilter bestaat sinds Servlets 3.0
 * 		De filter kan ook in de web.xml worden gedefinieerd.
 */

public class ServletFilter implements Filter 
{
	// de waarde "UTF-8" staat in de web.xml
	private String encoding;

	@Override
	public void init(FilterConfig fConfig) throws ServletException 
	{
		encoding = fConfig.getInitParameter("encoding");
	}
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}
}
