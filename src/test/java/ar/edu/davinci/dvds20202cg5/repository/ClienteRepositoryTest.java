package ar.edu.davinci.dvds20202cg5.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ar.edu.davinci.dvds20202cg5.model.Cliente;
import ar.edu.davinci.dvds20202cg5.model.Prenda;
import ar.edu.davinci.dvds20202cg5.model.TipoPrenda;
import ar.edu.davinci.dvds20202cg5.repository.ClienteRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ClienteRepositoryTest {
	private final Logger LOGGER = LoggerFactory.getLogger(PrendaRepositoryTest.class);

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Test
	void testFindAll() {
		assertNotNull(clienteRepository, "El repositorio es nulo.");
		List<Cliente> clientes = clienteRepository.findAll();
		
		LOGGER.info("Clientes encontrados: " + clientes.size());

		assertNotNull(clientes, "La lista de clientes es nula.");
		assertTrue(clientes.size() > 0, "No existen clientes.");
		
	}

	@Test
	void testFindAllById() {
		
		Long id = 2L;
		Optional<Cliente> clienteOpcional = clienteRepository.findById(id);
		if (clienteOpcional.isPresent()){
			Cliente cliente = clienteOpcional.get();
			
			LOGGER.info("Cliente encontrado: " + cliente);
			assertEquals("Rodrigo", cliente.getNombre());
			
		}
	}
}
