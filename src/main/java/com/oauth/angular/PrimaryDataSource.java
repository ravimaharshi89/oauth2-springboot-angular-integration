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
@EnableJpaRepositories(basePackages = { "com.oauth.angular.repo.db1" }, entityManagerFactoryRef = "primaryEntityManagerFactory")
public class PrimaryDataSource {

	@Primary
	@Bean(name = "dataSource1")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		//return DataSourceBuilder.create().build();
		  return new DriverManagerDataSource(); 
	}

	@Bean(name = "primaryEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean db1EntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.oauth.angular.entity.db1" });
		em.setPersistenceUnitName("primary");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		em.setJpaPropertyMap(properties);
		
		return em;
	}
	
	@Bean
	 public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory primaryEntityManagerFactory) {
		 HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
		 factory.setEntityManagerFactory(primaryEntityManagerFactory);
		 return factory;
	    }

	@Primary
	@Bean(name = "primaryTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {
		return new JpaTransactionManager(primaryEntityManagerFactory);
	}
}