package ar.edu.davinci.dvds20202cg5.model;

import java.util.LinkedList;
import java.util.List;

public enum TipoPrenda {

	SACO("Saco"), 
	PANTALON("Pantalón"), 
	CAMISA("Camisa"), 
	CAMPERA("Campera"), 
	TAPADO("Tapado"), 
	REMERA("Remera"),
	CHAQUETA("Chaqueta");

	private String descripcion;

	TipoPrenda(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public static List<TipoPrenda> getTipoPrendas() {
		List<TipoPrenda> tipoPrendas = new LinkedList<TipoPrenda>();
		tipoPrendas.add(TipoPrenda.CAMISA);
		tipoPrendas.add(TipoPrenda.CAMPERA);
		tipoPrendas.add(TipoPrenda.CHAQUETA);
		tipoPrendas.add(TipoPrenda.PANTALON);
		tipoPrendas.add(TipoPrenda.SACO);
		tipoPrendas.add(TipoPrenda.TAPADO);
		return tipoPrendas;
	}	

}

