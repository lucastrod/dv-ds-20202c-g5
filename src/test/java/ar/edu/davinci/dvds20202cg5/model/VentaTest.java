package ar.edu.davinci.dvds20202cg5.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;

import ar.edu.davinci.dvds20202cg5.model.Cliente;
import ar.edu.davinci.dvds20202cg5.model.VentaEfectivo;

public class VentaTest {
	@Test
	void testBuilder() {
		
		// Given
		
		Long id = 1L;
		Date fecha = java.util.Calendar.getInstance().getTime();
		Cliente cliente = new Cliente();
		
		// When
		VentaEfectivo ventaEfectivo = VentaEfectivo.builder()
				.id(id)
				.fecha(fecha)
				.cliente(cliente)
				.build();

		
		//Then
		assertNotNull(ventaEfectivo);
		assertEquals(id, ventaEfectivo.getId());
		assertEquals(fecha, ventaEfectivo.getFecha());
		assertEquals(cliente, ventaEfectivo.getCliente());
		
	}
}
