package ar.edu.davinci.dvds20202cg5.controller.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemInsertRequest {

	private Integer cantidad;

	private Long prendaId;

}

