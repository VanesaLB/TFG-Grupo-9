create database proyectos_empleados_2024;
use proyectos_empleados_2024;
    
create table empleados 
( id_empleado int not null auto_increment primary key,
nombre varchar(20) not null,
email varchar(50) not null unique,
sueldo dec (9,2),
categoria int
);


create table proyectos
( id_proyecto int not null auto_increment primary key,
descripcion varchar(45) not null,
fecha_inicio date,
dias_previstos int,
director int not null,
estado varchar(15),
foreign key(director) references empleados(id_empleado)
);


create table empleados_en_proyecto
( id_entrada int auto_increment primary key,
id_proyecto int not null,
id_empleado int not null,
dias_previstos int not null,
fecha_incorporacion date,
foreign key(id_proyecto) references proyectos(id_proyecto),
foreign key(id_empleado) references empleados(id_empleado)
);


 