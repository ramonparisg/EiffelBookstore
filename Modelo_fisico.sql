drop database if exists biblioteca;
create database biblioteca;
use biblioteca;


create table publicacion(
id_publicacion int auto_increment,
desc_publicacion varchar(45),
primary key (id_publicacion)
);

create table editorial(
id_editorial int auto_increment,
desc_editorial varchar(45),
primary key (id_editorial)
);

create table titulo(
isbn bigint,
nombre varchar(45),
anyo_publicacion date,
precio_referencia int,
nro_paginas int,
id_Editorial int,
id_Publicacion int,
primary key (isbn),
constraint fk_editorial foreign key(id_Editorial) references editorial(id_editorial),
constraint fk_publicacion foreign key(id_publicacion) references publicacion(id_publicacion)
);

create table autor(
id_autor int auto_increment,
nombre varchar(45),
ape_pat varchar(45),
ape_mat varchar(45),
primary key (id_autor)
);

create table autor_titulo(
id int,
isbn bigint
);

alter table autor_titulo add constraint fk_autor_titulo_id foreign key(id) references autor(id_autor);
alter table autor_titulo add constraint fk_autor_titulo_isbn foreign key(isbn) references titulo(isbn);

create table categoria(
id_categoria int auto_increment,
desc_categoria varchar(45),
primary key (id_categoria)
);

create table titulo_categoria(
id_categoria int,
isbn bigint,
constraint fk_titulo_categoria_categoria foreign key(id_categoria) references categoria(id_categoria),
constraint fk_titulo_categoria_titulo foreign key(isbn) references titulo(isbn)
);

create table idioma(
id_idioma int auto_increment,
desc_idioma varchar(45),
primary key(id_idioma)
);

create table libro_idioma(
id_idioma int,
nro_serie int
);

alter table libro_idioma add constraint fk_libro_idioma_idioma foreign key(id_idioma) references idioma(id_idioma);

create table estado(
id_estado int auto_increment,
desc_estado varchar(20),
primary key(id_estado)
);

create table libro(
nro_serie int auto_increment,
id_estado int,
isbn bigint,
primary key (nro_serie),
constraint fk_libro_estado foreign key (nro_serie) references estado(id_estado),
constraint fk_libro_titulo foreign key (isbn) references titulo(isbn)
);

alter table libro_idioma add constraint fk_libro_idioma_libro foreign key(nro_serie) references libro(nro_serie);

create table metodo_pago(
id_metodo_pago int auto_increment,
desc_metodo_pago varchar(45),
primary key(id_metodo_pago)
);

create table factura(
folio int auto_increment,
f_compra date,
precio_iva int,
iva int,
precio_neto int,
metodo_pago int,
primary key(folio),
constraint fk_factura_metodo_pago foreign key(metodo_pago) references metodo_pago(id_metodo_pago)
);

create table distribuidor(
rut varchar(11) primary key,
nombre varchar(45),
direccion varchar(45),
telefono varchar(20),
anyos_venta int
);

create table compra(
id_compra int auto_increment,
rut_distribuidor varchar(11),
folio int,
primary key(id_compra),
constraint fk_compra_distribuidor foreign key(rut_distribuidor) references distribuidor(rut),
constraint fk_compra_factura foreign key(folio) references factura(folio)
);

create table compra_libro(
id_compra int,
nro_serie int,
constraint fk_compra_libro_compra foreign key(id_compra) references compra(id_compra),
constraint fk_compra_libro_libro foreign key(nro_serie) references libro(nro_serie)
);

create table boleta(
folio int auto_increment,
f_venta date,
precio_iva int,
iva int,
precio_neto int,
metodo_pago int,
primary key(folio),
constraint fk_boleta_metodo_pago foreign key(metodo_pago) references metodo_pago(id_metodo_pago)
);

create table trabajador(
rut int,
nombre varchar(20),
ape_pat varchar(20),
ape_mat varchar(20),
f_nac date,
primary key(rut)
);

create table telefono_trabajador(
nro int primary key,
rut int,
constraint fk_telefono_trabajador_trabajador foreign key(rut) references trabajador(rut)
);

create table correo_trabajador(
correo varchar(50) primary key,
rut int,
constraint fk_correo_trabajador_trabajador foreign key(rut) references trabajador(rut)
);

create table direccion_trabajador(
id_direccion int auto_increment,
nombre_calle varchar(30),
nro_calle int,
rut int,
primary key(id_direccion),
constraint fk_direccion_trabajador_trabajador foreign key(rut) references trabajador(rut)
);

create table cliente(
rut int,
nombre varchar(20),
ape_pat varchar(20),
ape_mat varchar(20),
f_nac date,
primary key(rut)
);

create table telefono_cliente(
nro int primary key,
rut int,
constraint fk_telefono_cliente_cliente foreign key(rut) references cliente(rut)
);

create table correo_cliente(
correo int primary key,
rut int,
constraint fk_correo_cliente_cliente foreign key(rut) references cliente(rut)
);

create table direccion_cliente(
id_direccion int auto_increment,
nombre_calle varchar(30),
nro_calle int,
rut int,
primary key(id_direccion),
constraint fk_direccion_cliente_cliente foreign key(rut) references cliente(rut)
);

create table venta(
id_venta int auto_increment,
nro_serie int,
folio int,
rut_trabajador int,
rut_cliente int,
primary key (id_venta),
constraint fk_venta_libro foreign key(nro_serie) references libro(nro_serie),
constraint fk_venta_cliente foreign key(rut_cliente) references cliente(rut),
constraint fk_venta_trabajador foreign key(rut_trabajador) references trabajador(rut),
constraint fk_venta_boleta foreign key(folio) references boleta(folio)
);


create table arriendo(
id_arriendo int auto_increment,
nro_serie int,
rut_cliente int,
rut_trabajador int,
primary key(id_arriendo),
constraint fk_arriendo_libro foreign key(nro_serie) references libro(nro_serie),
constraint fk_arriendo_cliente foreign key(rut_cliente) references cliente(rut),
constraint fk_arriendo_trabajador foreign key(rut_trabajador) references trabajador(rut)
);


create table detalle_arriendo(
id_detalle_arriendo int auto_increment,
f_devolucion_estimada date,
f_retorno_real date,
f_arriendo date,
costo_arriendo int,
multa int,
costo_total int,
id_arriendo int,
primary key (id_detalle_arriendo),
constraint fk_detalle_arriendo_arriendo foreign key(id_arriendo) references arriendo(id_arriendo)
);


insert into publicacion(desc_publicacion) values('Libro');
insert into editorial(desc_editorial) values('Salesiana');
insert into titulo values(9789750720475,'Relato de un naufrago','1967-06-01',10000,400,1,1);
insert into titulo values(9789631420494,'Cien anos de soledad','1967-06-01',121212,600,1,1);
insert into titulo values(9789631420494,'Cien anos de soledad','1967-06-01',121212,600,1,1);