package ar.edu.davinci.dvds20202cg5.controller.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.davinci.dvds20202cg5.controller.TiendaApp;
import ar.edu.davinci.dvds20202cg5.controller.view.VentaController;
import ar.edu.davinci.dvds20202cg5.service.ClienteService;
import ar.edu.davinci.dvds20202cg5.service.ItemService;
import ar.edu.davinci.dvds20202cg5.service.PrendaService;
import ar.edu.davinci.dvds20202cg5.service.VentaService;
import ar.edu.davinci.dvds20202cg5.model.Cliente;
import ar.edu.davinci.dvds20202cg5.model.Item;
import ar.edu.davinci.dvds20202cg5.model.Prenda;
import ar.edu.davinci.dvds20202cg5.model.Venta;
import ar.edu.davinci.dvds20202cg5.model.VentaEfectivo;
import ar.edu.davinci.dvds20202cg5.model.VentaTarjeta;

@Controller
public class VentaController extends TiendaApp {
	private final Logger LOGGER = LoggerFactory.getLogger(VentaController.class);

	
	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private ClienteService clienteService;
		
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private PrendaService prendaService;
	
	@GetMapping(path = "ventas/list")
	public String showVentaPage(Model model) {
		LOGGER.info("GET - showVentaPage  - /ventas/list");
		
		Pageable pageable = PageRequest.of(0, 100);
		Page<Venta> ventas = ventaService.list(pageable);
		LOGGER.info("GET - showVentaPage venta importe final: " + ventas.getContent().toString());
		
		model.addAttribute("listVentas", ventas);

		LOGGER.info("ventas.size: " + ventas.getNumberOfElements());
		return "ventas/list_ventas";
	}
	
	@GetMapping(path = "/ventas/new")
	public String showNewVentaEfectivoPage(Model model) {
		LOGGER.info("GET - showNewVentaPage - /ventas/new");
		String tipoVenta = "Efectivo";
		model.addAttribute("tipoVenta", tipoVenta);
		VentaEfectivo venta = new VentaEfectivo();
		model.addAttribute("venta", venta);
		List<Cliente> clientes = clienteService.listAll();
		model.addAttribute("clientes", clientes);
		List<Item> items = itemService.listAll();
		model.addAttribute("items", items);
		
		LOGGER.info("ventas: " + venta.toString());

		return "ventas/new_ventas";
	}
	
	@GetMapping(path = "/ventas/items/{id}")
	public String showItem(Model model, @PathVariable(name = "id") Long ventaId) {
		LOGGER.info("GET - showItem - /ventas/item");
		Optional<Venta> venta = ventaService.findById(ventaId);
		Item item = new Item();
		if(venta.isPresent()) {
			item.setVenta(venta.get());
		}
		model.addAttribute("item", item);
		List<Prenda> prendas = prendaService.listAll();
		model.addAttribute("prendas", prendas);
		model.addAttribute("ventaId", ventaId);
		
		LOGGER.info("items" + item.toString());

		return "ventas/items";
	}
	
	@PostMapping(value = "/ventas/saveItems")
	public String saveItems(@ModelAttribute("item") Item item, @ModelAttribute("ventaId") String ventaId) throws Exception {
		LOGGER.info("POST - saveItem - /ventas/saveItems");
		LOGGER.info("item: " + item.toString());
		Long i = Long.parseLong(ventaId);
		Optional<Venta> venta = ventaService.findById(i);
		if(venta.isPresent()) {
			item.setVenta(venta.get());
		}
		itemService.save(item);
		return "redirect:edit/" + ventaId;
	}

	
	@PostMapping(value = "/ventas/save")
	public String saveVenta(@ModelAttribute("venta") VentaEfectivo venta, @ModelAttribute("tipoVenta") String tipoVenta, @ModelAttribute("clienteId") String clienteId) throws Exception {
		LOGGER.info("POST - saveVenta - /ventas/save");
		LOGGER.info("venta: " + venta.toString());
		Long i = Long.parseLong(clienteId);
		Optional<Cliente> cliente = clienteService.findById(i);
		
		if(tipoVenta.toLowerCase().equals("efectivo")) {
			if(cliente.isPresent()) {
				venta.setCliente(cliente.get());
			}
			ventaService.save(venta);
		}
		else {
			Venta ventaGuardar = new VentaTarjeta();
			if(cliente.isPresent()) {
				ventaGuardar.setCliente(cliente.get());
			}
			ventaGuardar.setFecha(venta.getFecha());
			ventaGuardar.setItems(venta.getItems());
			ventaGuardar.setId(venta.getId());
			ventaService.save(ventaGuardar);
		}
		
		return "redirect:/tienda/ventas/list";
	}
	
	
	@RequestMapping(value = "/ventas/edit/{id}", method = RequestMethod.GET)
	public ModelAndView showEditVentaPage(@PathVariable(name = "id") Long ventaId) {
		LOGGER.info("GET - showEditVentaPage - /ventas/edit/{id}");
		LOGGER.info("venta: " + ventaId);
		ModelAndView mav = new ModelAndView("ventas/edit_ventas");
		List<Cliente> clientes = clienteService.listAll();
		mav.addObject("clientes", clientes);
		mav.addObject("clienteId", 0);
		List<Item> items = itemService.listAll();
		List<Item> itemsAgregar = new ArrayList<Item>();
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getVenta().getId() == ventaId) {
				itemsAgregar.add(items.get(i));
			}
		}
		mav.addObject("items", itemsAgregar);
		String tipoVenta = "Efectivo";
		
		Optional<Venta> ventaOptional = ventaService.findById(ventaId);
		Venta venta = null;
		if (ventaOptional.isPresent()) {
			venta  = ventaOptional.get();
			mav.addObject("venta", venta);
			Object ventaObject = Hibernate.unproxy(venta);
		    if(ventaObject.getClass().equals(VentaTarjeta.class)) {
				tipoVenta = "Tarjeta";
			}
		}
		mav.addObject("tipoVenta", tipoVenta);
		return mav;
	}

	@RequestMapping(value = "/ventas/delete/{id}", method = RequestMethod.GET)
	public String deleteVenta(@PathVariable(name = "id") Long ventaId) throws Exception {
		LOGGER.info("GET - deleteVenta - /ventas/delete/{id}");
		LOGGER.info("venta: " + ventaId);
		ventaService.delete(ventaId);
		return "redirect:/tienda/ventas/list";
	}
}
