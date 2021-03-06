package be.vdab.web;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import be.vdab.aop.AOPConfig;
import be.vdab.datasource.DataSourceConfig;
import be.vdab.mail.MailConfig;
import be.vdab.repositories.RepositoriesConfig;
import be.vdab.restclients.RestClientsConfig;
import be.vdab.restservices.RestControllersConfig;
import be.vdab.security.SecurityConfig;
import be.vdab.services.ServicesConfig;


public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {DataSourceConfig.class, RepositoriesConfig.class, ServicesConfig.class, RestClientsConfig.class, MailConfig.class, SecurityConfig.class, AOPConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {ControllersConfig.class, RestControllersConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	@Override
	 protected javax.servlet.Filter[] getServletFilters() { 
		   return new javax.servlet.Filter[] {new OpenEntityManagerInViewFilter()};
	}
	
}
