package com.jesus.prueba_tecnica.unitarios.utils;

import com.jesus.prueba_tecnica.utils.BBDDUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BBDDUtilsTest {

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private Scanner scanner;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(connection.createStatement()).thenReturn(statement);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    void testInicializarBBDD_ExecutesWithoutException() {
        assertDoesNotThrow(() -> {
            BBDDUtils.inicializarBBDD();
        });
    }

    @Test
    void testInicializarBBDD_CreatesPricesTable() throws Exception {
        doNothing().when(statement).execute(anyString());
        doNothing().when(statement).execute(anyString());

        BBDDUtils.inicializarBBDD();

        verify(statement, times(1)).execute(contains("CREATE TABLE IF NOT EXISTS PRICES"));
    }

    @Test
    void testInicializarBBDD_InsertsDataFromCSV() throws Exception {
        when(scanner.hasNextLine()).thenReturn(true, false); // Simula una línea de datos
        when(scanner.nextLine()).thenReturn("1,2025-04-08-23.50.00,2025-04-09-23.50.00,1,35455,1,35.50,EUR,2025-04-08-23.50.00,admin");

        BBDDUtils.inicializarBBDD();

        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testInicializarBBDD_ThrowsException_WhenFileNotFound() {
        assertThrows(Exception.class, () -> {
            new FileReader("src/main/resources/nonexistent.csv"); // Archivo inexistente
        });
    }
}
