package ar.edu.davinci.dvds20202cg5.controller.rest.request;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaTarjetaRequest {
	
	private Long clienteId;
	
	private Integer cantidadCuotas;

}
