package ar.edu.davinci.dvds20202cg5.model;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Item de Venta
 * 
 * @author frmontero
 *
 */

@Entity
@Table(name="venta_items")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5445769603448838587L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "itm_id")
	private Long id;
	
	@Column(name = "itm_cantidad")
	private Integer cantidad;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="itm_vta_id", referencedColumnName="vta_id", nullable = false)
	@JsonBackReference
	private Venta venta;
	
	@ManyToOne(targetEntity = Prenda.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name="itm_prd_id", referencedColumnName="prd_id")
	private Prenda prenda;
	
	public BigDecimal importe() {
		return prenda.getPrecioFinal().multiply(new BigDecimal(cantidad));
	}

}
