--
-- Dumping data for table prendas
--

INSERT INTO prendas
(prd_descripcion,
 prd_precio_base,
 prd_tipo_prenda
) 
VALUES 
('Pantalon violeta',55,'PANTALON'),
('Remera roja',500.5,'REMERA'),
('Short corto',150.55,'PANTALON'),
('Camisa azul',1550,'CAMISA'),
('Camisa verde',2000.22,'CAMISA'),
('Pantalon negro',3500.5,'PANTALON'),
('Chaqueta blanca',165.20,'CHAQUETA'),
('Campera',2500.15,'CAMPERA'),
('Chaqueta rosa',2003.50,'CHAQUETA'),
('Saco gris',3400.45,'SACO'),
('Saco Negro',9898.00,'SACO');

--
-- Dumping data for table clientes
--

INSERT INTO clientes
(cli_apellido,
 cli_nombre
) 
VALUES 
('Santalla', 'Rodrigo'),
('Botto','Giuliano'),
('Castro','Lucas'),
('Montero','Francisco');

--
-- Dumping data for table ventas
--

INSERT INTO ventas 
(
  tipo_venta,
  vta_fecha,
  vta_cli_id
)
VALUES
('EFECTIVO', '2020-11-10', 1),
('EFECTIVO', '2020-10-25', 3),
('EFECTIVO', '2019-10-15', 3);


