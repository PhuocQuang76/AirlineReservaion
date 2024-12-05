package com.synergisticit.config;


import com.synergisticit.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class AppConfig {
	
	@Autowired DataSource dataSource;

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		
		entityManager.setDataSource(dataSource);
		//entityManager.setDataSource(dataSource());
		entityManager.setPackagesToScan("com.synergisticit.domain");
		
		entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManager.setJpaProperties(properties());
		return entityManager;
	}
	
	public Properties properties() {
		Properties p = new Properties();
		p.setProperty("hibernate.hbm2ddl.auto", "update");
		
		//'hibernate.dialect' (remove the property setting and it will be selected by default)
		p.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		return p;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource);
		//sessionFactory.setDataSource(dataSource());

		sessionFactory.setAnnotatedPackages("com.synergisticit.domain");
		sessionFactory.setAnnotatedClasses(Flight.class, Airline.class, Airport.class, Passenger.class, User.class,Role.class, Reservation.class);
		sessionFactory.setHibernateProperties(properties());
		return sessionFactory;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		 InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		 viewResolver.setPrefix("WEB-INF/jsp/");
		 viewResolver.setSuffix(".jsp");
		 return  viewResolver;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("WEB-INF/message/messages");
		
		return messageSource;
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	};



	/*
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/deedb?useSSL=false&serverTimezone=UTC");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");
		return dataSource;
	}
	*/




}
