package com.oauth.angular;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = {"com.oauth.angular.repo.db2"}, entityManagerFactoryRef = "secondaryEntityManager")
public class SecondaryDataSource {
	
	@Bean(name = "dataSource2")
	@ConfigurationProperties(prefix = "spring.seconddatasource")
	public DataSource dataSource2() {
		return new DriverManagerDataSource();
	}

	@Bean(name = "secondaryEntityManager")
	public LocalContainerEntityManagerFactoryBean db2EntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource2());
		em.setPackagesToScan(new String[] { "com.oauth.angular.entity.db2" });
		em.setPersistenceUnitName("secondary");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		em.setJpaPropertyMap(properties);
		
		return em;
	}
	
	@Bean
	 public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory secondaryEntityManager) {
		 HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
		 factory.setEntityManagerFactory(secondaryEntityManager);
		 return factory;
	    }

	@Bean(name = "secondarytransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("secondaryEntityManager") EntityManagerFactory secondaryEntityManager) {
		return new JpaTransactionManager(secondaryEntityManager);
	}
	
}