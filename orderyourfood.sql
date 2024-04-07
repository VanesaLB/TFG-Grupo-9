create database orderyourfood;
use orderyourfood;



CREATE TABLE Productos (
    id_plato INT not null AUTO_INCREMENT PRIMARY KEY ,
    ingredientes VARCHAR (255)not null,
    descripcion VARCHAR (255) not null,
    tipo VARCHAR (50)not null, CHECK (tipo IN ('ENTRANTE', 'PRINCIPAL', 'POSTRES','BEBIDAS')),
    vegano VARCHAR (2) CHECK (tipo IN ('si', 'no')), 
	Gluten  VARCHAR (2) CHECK (tipo IN ('si', 'no')),
    precio DOUBLE
);


CREATE TABLE Mesas (
    id_mesa INT not null AUTO_INCREMENT PRIMARY KEY,
    area VARCHAR(50) not null
);


CREATE TABLE Pedidos (
    id_pedido INT not null AUTO_INCREMENT PRIMARY KEY,
    id_mesa INT not null,
    id_plato INT not null,
    cantidad INT not null,
    numero_comensales INT not null,
    fecha DATE not null,
    servido VARCHAR(2) NOT NULL DEFAULT 'no' CHECK (servido IN ('si', 'no')),
    FOREIGN KEY (id_mesa) REFERENCES Mesas(id_mesa),
    FOREIGN KEY (id_plato) REFERENCES Productos(id_plato)
);

INSERT INTO productos (id_plato, ingredientes, descripcion, tipo, vegano,Gluten, precio) 
VALUES 
    (1, 'Vino blanco', 'copa vino blanco Albariño', 'BEBIDAS', 'si','no', 3),
    (2, 'Vino tinto', 'copa vino tinto Ribera de Duero', 'BEBIDAS', 'si','no',3),
    (3, 'Agua', 'agua mineral', 'BEBIDAS', 'si', 'no', 1.50),
    (4, 'CocaCola', 'coca cola', 'BEBIDAS', 'no', 'no', 1.50),
    (5, 'Naranja', 'naranja', 'BEBIDAS', 'no', 'no', 1.50),
    (6, 'Limon', 'limon', 'BEBIDAS', 'no', 'no',1.50),
    (7, 'café con leche', 'cafe Colombia, leche entera', 'BEBIDAS', 'no', 'no', 1),
    (8, 'café solo', 'cafe Colombia', 'BEBIDAS', 'no', 'no', 1),
    (9, 'café con leche de soja', 'cafe Colombia, leche de soja', 'BEBIDAS', 'no', 'no', 1),
    (10, 'Canonigos ,tomates cherri ,chia ,naranja', 'ensalada de canonigos', 'ENTRANTE', 'si', 'no', 9),
    (11, 'Salchichon, lomo, chorizo ', 'tabla de embutidos ibéricos', 'ENTRANTE', 'no', 'si', 11),
    (12, 'Gouda,curado,roquefort,edam', 'tabla de quesos', 'ENTRANTE', 'no', 'no', 11),
    (13, 'Gambas,aguacate,limon,pan', 'Tosta de gambas sobre cama de aguacate', 'ENTRANTE', 'no', 'si', 10),
    (14, 'Salmon,queso,pan', 'Tosta de salmón sobre cama de philadelphia', 'ENTRANTE', 'no', 'si', 10),
    (15, 'Chocolate,leche,huevo, harina de trigo,frambuesa', 'Brownie de chocolate', 'POSTRES', 'no', 'si', 5.50),
    (16, 'Melon', 'melon', 'POSTRES', 'si', 'no', 3),
    (17, 'Piña', 'Piña', 'POSTRES', 'si', 'no', 3),
    (18, 'Sandia,agua,azucar', 'Smothie Sandia', 'POSTRES', 'si', 'no', 4),
    (19, 'Leche,huevos,harina trigo', 'Tarta con merengue', 'POSTRES', 'no', 'si', 4),
    (20, 'Cafe,huevos,leche ,harina trigo, chocolate', 'Tarta de tiramisu', 'POSTRES', 'no', 'si', 3),
    (21, 'Chuleta ternera', 'Chuletón del Bierzo asado a la piedra', 'PRINCIPAL', 'no', 'no', 19),
    (22, 'Ternera ,patatas,zanahoria,ajo ,cebolla, vino blanco', 'Goulash de ternera al estilo de la abuela', 'PRINCIPAL', 'no', 'si', 10),
    (23, 'Lomo ternera ','Lomo de vaca vieja', 'PRINCIPAL', 'no', 'no', 20),
    (24, 'Macarrones,tomate,nata ,tomates cherri,albahaca', 'Macarrones a la Amatriciana', 'PRINCIPAL', 'si', 'si', 6),
    (25, 'Raviolis,tomate,espinacas,cebollino', 'Raviolis Provenzales a la auténtica receta italiana ', 'PRINCIPAL', 'si', 'si', 6),
    (26, 'Lomos de salmón noruego,lechuga,limon', 'Lomos de salmón noruego a la plancha', 'PRINCIPAL', 'no', 'no', 8),
    (27, 'Solomillos de cerdo,esparragos,tomates cherri','perejil', 'PRINCIPAL', 'no', 'no', 10);

  INSERT INTO mesas (id_mesa,area)
VALUES
     (1,'primera interior'),
     (2,'segunda interior'),
     (3,'tercera interior'),
     (4,'cuarta interior'),
     (5,'primera exterior'),
     (6,'segunda exterior'),
     (7,'tercera exterior');




    

    
