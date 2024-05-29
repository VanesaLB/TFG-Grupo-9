create database orderyourfood;
use orderyourfood;



CREATE TABLE Productos (
    id_producto INT not null AUTO_INCREMENT PRIMARY KEY ,
    ingredientes VARCHAR (255)not null,
    descripcion VARCHAR (255) not null,
    tipo VARCHAR (50)not null, CHECK (tipo IN ('ENTRANTE', 'PRINCIPAL', 'POSTRES','BEBIDAS')),
    vegano VARCHAR (2) CHECK (vegano IN ('si', 'no')), 
	Gluten  VARCHAR (2) CHECK (Gluten IN ('si', 'no')),
    precio DOUBLE
);


CREATE TABLE Mesas (
    id_mesa INT not null AUTO_INCREMENT PRIMARY KEY,
    area VARCHAR(50) not null
);


CREATE TABLE Pedidos (
    id_pedido INT not null AUTO_INCREMENT PRIMARY KEY,
    precio_total DOUBLE,
    id_mesa INT not null,
    cantidad_productos INT not null,
    fecha DATE not null,
    FOREIGN KEY (id_mesa) REFERENCES Mesas(id_mesa)
);

CREATE TABLE Comandas (
    id_comanda INT not null AUTO_INCREMENT PRIMARY KEY,
    id_producto INT not null,
    id_pedido INT not null,
    id_mesa INT not null,
    servido VARCHAR(2) NOT NULL DEFAULT 'no' CHECK (servido IN ('si', 'no')),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto),
    FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido),
    FOREIGN KEY (id_mesa) REFERENCES Mesas(id_mesa)
);

INSERT INTO productos (id_producto, ingredientes, descripcion, tipo, vegano, Gluten, precio) 
VALUES 
    (1, 'Vino blanco', 'Copa vino blanco Albariño', 'BEBIDAS', 'si','no', 3),
    (2, 'Vino tinto', 'Copa vino tinto Ribera de Duero', 'BEBIDAS', 'si','no',3),
    (3, 'Agua', 'Agua mineral', 'BEBIDAS', 'si', 'no', 1.50),
    (4, 'Coca-cola', 'Coca-cola', 'BEBIDAS', 'no', 'no', 1.50),
    (5, 'Naranja', 'Naranja', 'BEBIDAS', 'no', 'no', 1.50),
    (6, 'Limón', 'Limón', 'BEBIDAS', 'no', 'no',1.50),
    (7, 'Café con leche', 'Café Colombia, leche entera', 'BEBIDAS', 'no', 'no', 1),
    (8, 'Café solo', 'Café Colombia', 'BEBIDAS', 'no', 'no', 1),
    (9, 'Café con leche de soja', 'Café Colombia y leche de soja', 'BEBIDAS', 'no', 'no', 1),
    (10, 'Canónigos, tomates cherri, chía, naranja', 'Ensalada de canónigos', 'ENTRANTE', 'si', 'no', 9),
    (11, 'Salchichón, lomo, chorizo, jamón', 'Tabla de embutidos ibéricos', 'ENTRANTE', 'no', 'si', 11),
    (12, 'Gouda, curado, roquefort, edam', 'Tabla de quesos', 'ENTRANTE', 'no', 'no', 11),
    (13, 'Gambas, aguacate, limón, pan', 'Tosta de gambas con aguacate', 'ENTRANTE', 'no', 'si', 10),
    (14, 'Salmón, queso, pan', 'Tosta de salmón y queso', 'ENTRANTE', 'no', 'si', 10),
    (15, 'Chocolate, leche, huevo, harina de trigo, frambuesa', 'Brownie de chocolate', 'POSTRES', 'no', 'si', 5.50),
    (16, 'Melón', 'melón', 'POSTRES', 'si', 'no', 3),
    (17, 'Piña', 'Piña', 'POSTRES', 'si', 'no', 3),
    (18, 'Sandía, agua, azúcar', 'Smothie Sandía', 'POSTRES', 'si', 'no', 4),
    (19, 'Leche, huevos, harina trigo y un toque a limón', 'Tarta con merengue', 'POSTRES', 'no', 'si', 4),
    (20, 'Café, huevos, leche, harina trigo, chocolate', 'Tarta de tiramisú', 'POSTRES', 'no', 'si', 3),
    (21, 'Chuleta ternera con guarnición', 'Chuletón del Bierzo asado a la piedra', 'PRINCIPAL', 'no', 'no', 19),
    (22, 'Ternera, patatas, zanahoria, ajo, cebolla, vino blanco', 'Goulash de ternera de la abuela', 'PRINCIPAL', 'no', 'si', 10),
    (23, 'Lomo ternera a baja temperatura','Lomo de vaca vieja', 'PRINCIPAL', 'no', 'no', 20),
    (24, 'Macarrones, tomate, nata, tomates cherri, albahaca', 'Macarrones a la Amatriciana', 'PRINCIPAL', 'si', 'si', 6),
    (25, 'Raviolis, tomate, espinacas, cebollino', 'Raviolis Provenzales receta italiana', 'PRINCIPAL', 'si', 'si', 6),
    (26, 'Lomos de salmón noruego, lechuga, limón', 'Lomos de salmón a la plancha', 'PRINCIPAL', 'no', 'no', 8),
    (27, 'Solomillos de cerdo, espárragos, tomates cherri','Solomillo de cerdo', 'PRINCIPAL', 'no', 'no', 10);

  INSERT INTO mesas (id_mesa,area)
VALUES
     (1,'primera interior'),
     (2,'segunda interior'),
     (3,'tercera interior'),
     (4,'cuarta interior'),
     (5,'primera exterior'),
     (6,'segunda exterior'),
     (7,'tercera exterior');
