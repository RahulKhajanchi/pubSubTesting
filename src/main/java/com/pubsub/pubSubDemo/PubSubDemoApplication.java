package com.pubsub.pubSubDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
								   DataSourceTransactionManagerAutoConfiguration.class,
								   HibernateJpaAutoConfiguration.class})
public class PubSubDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PubSubDemoApplication.class, args);
	}

}
