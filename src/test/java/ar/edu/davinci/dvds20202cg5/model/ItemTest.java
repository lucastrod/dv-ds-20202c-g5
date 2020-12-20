package ar.edu.davinci.dvds20202cg5.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import ar.edu.davinci.dvds20202cg5.model.Item;
import ar.edu.davinci.dvds20202cg5.model.Prenda;
import ar.edu.davinci.dvds20202cg5.model.VentaEfectivo;

public class ItemTest {

	@Test
	void testBuilder() {
		
		// Given
		
		Long id = 1L;
		Integer cantidad = 10;
		VentaEfectivo venta = new VentaEfectivo();
		Prenda prenda = new Prenda();
		prenda.setPrecioBase(new BigDecimal(10.2D));
		BigDecimal importe = prenda.getPrecioFinal().multiply(new BigDecimal(cantidad));
		
		// When
		Item item = Item.builder()
				.id(id)
				.venta(venta)
				.cantidad(cantidad)
				.prenda(prenda)
				.build();

		
		//Then
		assertNotNull(item);
		assertEquals(id, item.getId());
		assertEquals(prenda, item.getPrenda());
		assertEquals(venta, item.getVenta());
		assertEquals(importe, item.importe());
	}

}
