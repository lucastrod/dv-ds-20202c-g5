package ar.edu.davinci.dvds20202cg5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.davinci.dvds20202cg5.model.Prenda;
import ar.edu.davinci.dvds20202cg5.model.TipoPrenda;

public interface PrendaService {
	
	public List<Prenda> listAll();
	public Page<Prenda> list(Pageable pageable);
	public Optional<Prenda> findById(Long id);
	public Prenda save(Prenda prenda);
	public void delete(Prenda prenda);
	public void delete(Long id);
	public long count();
	public List<TipoPrenda> getTipoPrendas();


}
