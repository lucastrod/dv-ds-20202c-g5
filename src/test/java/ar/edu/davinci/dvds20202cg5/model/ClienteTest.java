package ar.edu.davinci.dvds20202cg5.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import ar.edu.davinci.dvds20202cg5.model.Cliente;

public class ClienteTest {
	@Test
	void testBuilder() {
		
		// Given
		
		Long id = 1L;
		String nombre = "Rodrigo";
		String apellido = "Santalla";
		String razonSocial = nombre + " " + apellido;
		
		// When
		Cliente cliente = Cliente.builder()
				.id(id)
				.nombre(nombre)
				.apellido(apellido)
				.build();

		
		//Then
		assertNotNull(cliente);
		assertEquals(id, cliente.getId());
		assertEquals(nombre, cliente.getNombre());
		assertEquals(apellido, cliente.getApellido());
		assertEquals(razonSocial, cliente.getRazonSocial());
	}

}
