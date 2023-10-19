package com.bankingapp.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class SpringConfig {

	@Autowired
	private Environment environment;
	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/bank?useSSL=false&serverTimezone=UTC")
				.username(environment.getProperty("spring.datasource.username"))
				.password(environment.getProperty("spring.datasource.password"))
				.driverClassName(environment.getProperty("spring.driverClassName")).build();

	}

	@Bean
	public Properties hibernateProperties() {
		Properties props = new Properties();
		props.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO,
				environment.getProperty("spring.jpa.hibernate.hbm2ddl.auto"));
		props.put(org.hibernate.cfg.Environment.SHOW_SQL, environment.getProperty("spring.hibernate.show_sql"));
		props.put(org.hibernate.cfg.Environment.DIALECT,
				environment.getProperty("spring.jpa.properties.hibernate.dialect"));
		return props;
	}

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(environment.getProperty("spring.mail.host"));
		mailSender.setPort(587);
		mailSender.setUsername(environment.getProperty("spring.mail.username"));
		mailSender.setPassword(environment.getProperty("spring.mail.password"));

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.smtp.auth", environment.getProperty("spring.mail.properties.mail.smtp.auth"));
		props.put("mail.smtp.starttls.enable",
				environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));

		return mailSender;
	}

	@Bean(name = "entityManagerFactory")
	public LocalSessionFactoryBean localSession() {
		LocalSessionFactoryBean session = new LocalSessionFactoryBean();
		session.setDataSource(dataSource());
		session.setHibernateProperties(hibernateProperties());
		session.setPackagesToScan("com.bankingapp");
		return session;
	}
}
