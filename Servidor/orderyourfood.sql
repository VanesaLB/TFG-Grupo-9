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
    numero_comensales INT not null,
    area VARCHAR(50) not null
);


CREATE TABLE Pedidos (
    id_pedido INT not null AUTO_INCREMENT PRIMARY KEY,
    id_mesa INT not null,
    id_plato INT not null,
    cantidad INT not null,
    fecha DATE not null,
    servido VARCHAR (2) , check (servido in ('si', 'no')),
    FOREIGN KEY (id_mesa) REFERENCES Mesas(id_mesa),
    FOREIGN KEY (id_plato) REFERENCES Productos(id_plato)
);