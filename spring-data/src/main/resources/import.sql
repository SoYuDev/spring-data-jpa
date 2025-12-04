
INSERT INTO categoria (id, nombre) VALUES (1, 'Electrónica');
INSERT INTO categoria (id, nombre) VALUES (2, 'Videojuegos y Consolas');
INSERT INTO categoria (id, nombre) VALUES (3, 'Hogar y Electrodomésticos');
INSERT INTO categoria (id, nombre) VALUES (4, 'Accesorios y Estilo de Vida');
INSERT INTO categoria (id, nombre) VALUES (5, 'Papelería y Oficina');

ALTER SEQUENCE IF EXISTS categoria_seq START WITH 6;

INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 599.99, 'Smartphone Samsung Galaxy S23', 'Teléfono móvil con pantalla AMOLED de 6.1 pulgadas y 256 GB de almacenamiento.', 1);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 1299.99, 'Laptop Dell XPS 13', 'Ultrabook con procesador Intel Core i7, 16 GB RAM y 512 GB SSD.', 1);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 249.99, 'Auriculares Sony WH-1000XM5', 'Auriculares inalámbricos con cancelación activa de ruido.', 1);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 49.99, 'Teclado Mecánico Logitech G Pro', 'Teclado mecánico compacto con switches GX Blue.', 1);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 29.99, 'Ratón Inalámbrico Logitech M705', 'Ratón ergonómico con tecnología inalámbrica de largo alcance.', 1);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 699.00, 'Monitor LG UltraFine 4K', 'Monitor 4K de 27 pulgadas con soporte para HDR10.', 1);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 399.99, 'Consola Nintendo Switch OLED', 'Versión mejorada con pantalla OLED y 64 GB de almacenamiento.', 2);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 59.99, 'Videojuego Zelda: Tears of the Kingdom', 'Juego de aventura y exploración en un mundo abierto.', 2);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 89.99, 'Smartwatch Apple Watch Series 8', 'Reloj inteligente con monitor de salud y resistencia al agua.', 2);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 999.00, 'Bicicleta Eléctrica Xiaomi Mi Smart', 'Bicicleta con batería de larga duración y motor de 250W.', 4);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 39.99, 'Altavoz Inteligente Amazon Echo Dot', 'Altavoz con asistente Alexa integrado.', 1);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 120.00, 'Cafetera Espresso DeLonghi', 'Cafetera semiautomática para espresso y cappuccino.', 3);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 15.99, 'Botella de Agua Térmica Hydro Flask', 'Botella de acero inoxidable que mantiene la temperatura por 24 horas.', 4);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 59.99, 'Cargador Inalámbrico Belkin', 'Cargador rápido compatible con dispositivos Qi.', 1);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 24.99, 'Lámpara LED Xiaomi Mi Bedside', 'Lámpara con control táctil y ajuste de temperatura de color.', 3);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 12.99, 'Cable HDMI 2.1 UGREEN', 'Cable de 2 metros con soporte para 8K y 60Hz.', 1);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 499.99, 'Robot Aspirador iRobot Roomba 960', 'Aspiradora inteligente con navegación láser.', 3);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 29.99, 'Altavoz Bluetooth JBL Go 3', 'Altavoz portátil con sonido potente y resistencia al agua.', 1);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 45.00, 'Mochila Herschel Little America', 'Mochila urbana con compartimento acolchado para laptop.',4);
INSERT INTO producto (id, precio, nombre_producto, descripcion, categoria_id) VALUES (nextval('producto_seq'), 10.50, 'Libreta Moleskine Classic', 'Libreta de tapa dura ideal para notas y bocetos.',5);