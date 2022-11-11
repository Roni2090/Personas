package com.mx.prueba.personas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonasApplication  implements CommandLineRunner{

	@Autowired
	MainPersona mainPersona;
	private static final Logger log = LoggerFactory.getLogger(PersonasApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PersonasApplication.class, args).close();
	}

	@Override
	public void run(String... args)throws Exception{
		log.info("inicia el proceso");

		mainPersona.inicio();
		
	}

}
