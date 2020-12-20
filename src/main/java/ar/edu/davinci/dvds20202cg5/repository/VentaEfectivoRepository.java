package ar.edu.davinci.dvds20202cg5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.davinci.dvds20202cg5.model.VentaEfectivo;

@Repository
public interface VentaEfectivoRepository extends JpaRepository<VentaEfectivo, Long> {

}
