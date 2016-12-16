package be.vdab.repositories;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

public class TestRepositoriesConfig extends RepositoriesConfig {
	@Override
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factory = super.entityManagerFactory(dataSource);
		factory.getJpaPropertyMap().put("javax.persistence.schema-generation.database.action", "create");
		return factory;
	}
			
}
