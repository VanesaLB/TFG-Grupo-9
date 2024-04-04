create database orderyourfood;
use orderyourfood;



CREATE TABLE Productos (
    id_plato INT not null AUTO_INCREMENT PRIMARY KEY ,
    ingredientes VARCHAR (255)not null,
    descripcion VARCHAR (255) not null,
    tipo VARCHAR (50)not null, CHECK (tipo IN ('ENTRANTE', 'PRINCIPAL', 'POSTRES','BEBIDAS')),
    vegano VARCHAR (2) CHECK (tipo IN ('si', 'no')),
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

INSERT INTO productos (id_plato, ingredientes, descripcion, tipo, vegano, precio) 
VALUES 
    (1, 'Vino blanco', 'copa vino blanco Albariño', 'BEBIDAS', 'si', 3),
    (2, 'Vino tinto', 'copa vino tinto Ribera de Duero', 'BEBIDAS', 'si', 3),
    (3, 'Agua', 'agua mineral', 'BEBIDAS', 'si', 1.50),
    (4, 'CocaCola', 'coca cola', 'BEBIDAS', 'no', 1.50),
    (5, 'Naranja', 'naranja', 'BEBIDAS', 'no', 1.50),
    (6, 'Limon', 'limon', 'BEBIDAS', 'no', 1.50),
    (7, 'Cafe', 'cafe Colombia', 'BEBIDAS', 'no', 1),
    (8, 'Canonigos ,tomates cherri ,chia ,naranja', 'ensalada de canonigos', 'ENTRANTE', 'si', 9),
    (9, 'Salchichon , lomo , chorizo ', 'tabla de embutidos ibéricos', 'ENTRANTE', 'no', 11),
    (10, 'Gouda,curado,roquefort,edam', 'tabla de quesos', 'ENTRANTE', 'no', 11),
    (11, 'Gambas,aguacate,limon,pan', 'Tosta de gambas sobre cama de aguacate', 'ENTRANTE', 'no', 10),
    (12, 'Salmon,queso,pan', 'Tosta de salmón sobre cama de philadelphia', 'ENTRANTE', 'no', 10),
    (13, 'Chocolate,leche,huevo, harina de trigo,frambuesa', 'Brownie de chocolate', 'POSTRES', 'no', 5.50),
    (14, 'Melon', 'melon', 'POSTRES', 'si', 3),
    (15, 'Piña', 'Piña', 'POSTRES', 'si', 3),
    (16, 'Sandia,agua,azucar', 'Smothie Sandia', 'POSTRES', 'si', 4),
    (17, 'Leche,huevos,harina trigo', 'Tarta con merengue', 'POSTRES', 'no', 4),
    (18, 'Cafe,huevos,leche ,harina trigo, chocolate', 'Tarta de tiramisu', 'POSTRES', 'no', 3),
    (19, 'Chuleta ternera', 'Chuletón del Bierzo asado a la piedra', 'PRINCIPAL', 'no', 19),
    (20, 'Ternera ,patatas,zanahoria,ajo ,cebolla, vino blanco', 'Goulash de ternera al estilo de la abuela', 'PRINCIPAL', 'no', 10),
    (21, 'Lomo ternera ','Lomo de vaca vieja', 'PRINCIPAL', 'no', 20),
    (22, 'Macarrones,tomate,nata ,tomates cherri,albahaca', 'Macarrones a la Amatriciana', 'PRINCIPAL', 'si', 6),
    (23, 'Raviolis,tomate,espinacas,cebollino', 'Raviolis Provenzales a la auténtica receta italiana ', 'PRINCIPAL', 'si', 6),
    (24, 'Lomos de salmón noruego,lechuga,limon', 'Lomos de salmón noruego a la plancha', 'PRINCIPAL', 'no', 8),
    (25, 'Solomillos de cerdo,esparragos,tomates cherri','perejil', 'PRINCIPAL', 'no', 10);

  INSERT INTO mesas (id_mesa,area)
VALUES
     (1,'primera interior'),
     (2,'segunda interior'),
     (3,'tercera interior'),
     (4,'cuarta interior'),
     (5,'primera exterior'),
     (6,'segunda exterior'),
     (7,'tercera exterior');




    

    






