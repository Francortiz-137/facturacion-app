INSERT INTO regiones (id, name) VALUES (1, 'Sudamerica');
INSERT INTO regiones (id, name) VALUES (2, 'CentroAmerica');
INSERT INTO regiones (id, name) VALUES (3, 'NorteAmerica');
INSERT INTO regiones (id, name) VALUES (4, 'Europa');
INSERT INTO regiones (id, name) VALUES (5, 'Asia');
INSERT INTO regiones (id, name) VALUES (6, 'Africa');
INSERT INTO regiones (id, name) VALUES (7, 'Oceania');
INSERT INTO regiones (id, name) VALUES (8, 'Antartida');


INSERT INTO client (name, last_name, email, created_at, region_id) VALUES ('asadasdas','1','1@example.com','2023-01-01',1);
INSERT INTO client (name, last_name, email, created_at, region_id) VALUES ('bsadasdasd','2','2@example.com','2023-01-01',2);
INSERT INTO client (name, last_name, email, created_at, region_id) VALUES ('cdsadasdsad','3','3@example.com','2023-01-01',3);
INSERT INTO client (name, last_name, email, created_at, region_id) VALUES ('dasdasdasda','4','4@example.com','2023-01-01',4);
INSERT INTO client (name, last_name, email, created_at, region_id) VALUES ('edsadasdsad','5','5@example.com','2023-01-01',5);
INSERT INTO client (name, last_name, email, created_at, region_id) VALUES ('fasdsadasdsa','6','6@example.com','2023-01-01',6);
INSERT INTO client (name, last_name, email, created_at, region_id) VALUES ('gsdadsadasd','7','7@example.com','2023-01-01',7);
INSERT INTO client (name, last_name, email, created_at, region_id) VALUES ('hasdasdasda','8','8@example.com','2023-01-01',8);
INSERT INTO client (name, last_name, email, created_at, region_id) VALUES ('idasdasdsad','9','9@example.com','2023-01-01',1);
INSERT INTO client (name, last_name, email, created_at, region_id) VALUES ('jdsadasdsad','10','10@example.com','2023-01-01',1);
INSERT INTO client (name, last_name, email, created_at, region_id) VALUES ('ksdadasdasd','11','11@example.com','2023-01-01',1);


INSERT INTO `usuarios` (username, password, enabled) VALUES ('franco','$2a$10$pGhVqWBB/sOjuiLqx2eqxOps6Yqyl6UYhvcf.6hAxLX.DGpmslarK',1);
INSERT INTO `usuarios` (username, password, enabled) VALUES ('admin','$2a$10$L.KGUpBft/QQIfRC.E6BoeC28o91FgimwXUuGVhmAZhZHYs3O/8BO',1);

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1,1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2,2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2,1);

INSERT INTO productos (name, price, created_at) VALUES ('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (name, price, created_at) VALUES ('Sony Camara digital DSC-W3200', 123490, NOW());
INSERT INTO productos (name, price, created_at) VALUES ('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (name, price, created_at) VALUES ('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (name, price, created_at) VALUES ('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (name, price, created_at) VALUES ('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (name, price, created_at) VALUES ('Mica comoda 5 Cajones', 299990, NOW());


INSERT INTO facturas (description, observation, client_id, created_at) VALUES ('Factura equipos de oficina', null, 1, NOW());
INSERT INTO facturas_items (amount, factura_id, product_id) VALUES (1, 1, 1);
INSERT INTO facturas_items (amount, factura_id, product_id) VALUES (2, 1, 4);
INSERT INTO facturas_items (amount, factura_id, product_id) VALUES (1, 1, 5);
INSERT INTO facturas_items (amount, factura_id, product_id) VALUES (1, 1, 7);

INSERT INTO facturas (description, observation, client_id, created_at) VALUES ('Factura Bicicleta', 'Alguna nota', 1, NOW());
INSERT INTO facturas_items (amount, factura_id, product_id) VALUES (3, 2, 6);


