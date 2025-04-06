package com.jesus.prueba_tecnica;

import com.jesus.prueba_tecnica.utils.BBDDUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Clase principal del proyecto.
 * @author Jesus
 */
@SpringBootApplication
public class PruebaTecnicaApplication extends SpringBootServletInitializer {

	/**
	 * Metodo principal de la aplicacion para inicializarla en el servidor.
	 * @param args
	 */
	public static void main(String[] args) {

		BBDDUtils.inicializarBBDD();

		SpringApplication.run(PruebaTecnicaApplication.class, args);
	}



}
