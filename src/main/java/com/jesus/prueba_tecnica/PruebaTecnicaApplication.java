package com.jesus.prueba_tecnica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.FileReader;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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
		System.out.println(System.getProperty("java.class.path"));

		inicializarBBDD();

		SpringApplication.run(PruebaTecnicaApplication.class, args);
	}

	/**
	 * Metodo encargado de inicializar la BBDD, creando la tabla e insertando los datos del archivo csv
	 */
	private static void inicializarBBDD(){
		String jdbcUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
		String username = "admin";
		String password = "admin";

		try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
			 Scanner scanner = new Scanner(new FileReader("src/main/resources/prices.csv"));
			 Statement stmt = conn.createStatement()) {

			// Crear la tabla si no existe
			stmt.execute("CREATE TABLE PRICES (" +
					"BrandId INT, StartDate TIMESTAMP, EndDate TIMESTAMP, " +
					"PriceList INT, ProductId INT, Priority INT, Price DECIMAL(10,2), " +
					"Currency VARCHAR(3), LastUpdate TIMESTAMP, LastUpdateBy VARCHAR(50))");


			String sql = "INSERT INTO PRICES (BrandId, StartDate, EndDate, PriceList, ProductId, Priority, Price, Currency, LastUpdate, LastUpdateBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);

			//El formato de fecha del csv no es el esperado por h2, se debe reconvertir
			DateTimeFormatter csvFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
			DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			scanner.nextLine(); // Saltar la primera línea (encabezados)
			while (scanner.hasNextLine()) {
				String[] data = scanner.nextLine().split(",");

				statement.setInt(1, Integer.parseInt(data[0]));
				statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.parse(data[1], csvFormatter).format(dbFormatter)));
				statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.parse(data[2], csvFormatter).format(dbFormatter)));
				statement.setInt(4, Integer.parseInt(data[3]));
				statement.setInt(5, Integer.parseInt(data[4]));
				statement.setInt(6, Integer.parseInt(data[5]));
				statement.setDouble(7, Double.parseDouble(data[6]));
				statement.setString(8, data[7]);
				statement.setTimestamp(9, Timestamp.valueOf(LocalDateTime.parse(data[8], csvFormatter).format(dbFormatter)));
				statement.setString(10, data[9]);

				statement.executeUpdate();
			}

			System.out.println("Datos insertados correctamente.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
