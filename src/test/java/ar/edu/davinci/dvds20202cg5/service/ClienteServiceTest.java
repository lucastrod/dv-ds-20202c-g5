package ar.edu.davinci.dvds20202cg5.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ar.edu.davinci.dvds20202cg5.model.Cliente;
import ar.edu.davinci.dvds20202cg5.model.Prenda;
import ar.edu.davinci.dvds20202cg5.model.TipoPrenda;
import ar.edu.davinci.dvds20202cg5.service.ClienteService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ClienteServiceTest {

	private final Logger LOGGER = LoggerFactory.getLogger(PrendaServiceImplTest.class);

	@Autowired
	private ClienteService clienteService;
	
	@Test
	void testListAll() {
		
		List<Cliente> clientes = clienteService.listAll();
		
		LOGGER.info("Cliente size:" + clientes.size());

		assertNotNull(clientes, "Clientes es nulo");
		assertTrue(clientes.size() > 0, "Clientes está vacio");
		
	}

	@Test
	void testList() {
		
		Pageable pageable = PageRequest.of(0, 2);
		Page<Cliente> clientes = clienteService.list(pageable);

		LOGGER.info("Cliente size:" + clientes.getSize());
		LOGGER.info("Cliente total page:" + clientes.getTotalPages());

		Pageable pageable1 = PageRequest.of(1, 2);
		Page<Cliente> clientes1 = clienteService.list(pageable1);

		LOGGER.info("Cliente size:" + clientes1.getSize());
		LOGGER.info("Cliente total page:" + clientes1.getTotalPages());

		assertNotNull(clientes, "Clientes es nulo");
		assertTrue(clientes.getSize() > 0, "Clientes está vacio");
	}

	@Test
	void testFindById() {
		Optional<Cliente> clienteOptional = clienteService.findById(1L);
		Cliente cliente = null;
		if (clienteOptional.isPresent()) {
			LOGGER.info("LA PRENDA FUE ENCONTRADA");
			cliente = clienteOptional.get();
		} else {
			LOGGER.info("LA PRENDA NO FUE ENCONTRADA");
		}			
		assertNotNull(cliente);
		assertEquals(cliente.getNombre(), "Rodrigo");
	}

	@Test
	void testFindById_withError() {
		Optional<Cliente> clienteOptional = clienteService.findById(12L);
		Cliente cliente = null;
		if (clienteOptional.isPresent()) {
			LOGGER.info("EL CLIENTE FUE ENCONTRADO");
			cliente = clienteOptional.get();
		} else {
			LOGGER.info("EL CLIENTE NO FUE ENCONTRADO");
		}		
		assertNull(cliente);
	}

	@Test
	void testSave() {
		
		
		LOGGER.info("Cliente count antes insert: " + clienteService.count());
		Cliente cliente = Cliente.builder()
				.nombre("Rodrigo")
				.apellido("Santalla")
				.build();
		
		Cliente clienteCreado = clienteService.save(cliente);
		
		assertNotNull(clienteCreado);
		assertEquals(cliente.getNombre(), clienteCreado.getNombre());

		LOGGER.info("Cliente count después insert: " + clienteService.count());
		
		
	}

	@Test
	void testDelete() {
		
		LOGGER.info("Prenda count antes delete: " + clienteService.count());

		Optional<Cliente> clienteOptional = clienteService.findById(2L);
		Cliente cliente = null;
		if (clienteOptional.isPresent()) {
			LOGGER.info("LA PRENDA FUE ENCONTRADA");
			cliente = clienteOptional.get();
			clienteService.delete(cliente);
			LOGGER.info("Cliente count después delete: " + clienteService.count());
		} else {
			LOGGER.info("EL CLIENTE NO FUE ENCONTRADO");
		}
	}

}
