Akihabara Market:

Este proyecto es un sistema de gestión de inventario para una tienda, desarrollado en Java utilizando MySQL como base de datos.


Características de mi programa:

- Añadir productos
- Consultar productos por ID
- Ver todos los productos
- Eliminar productos por ID
- Actualizar nombre de productos por ID
- Usar una IA para que te ayude


Base de Datos:

Se ha utilizado MySQL Workbench para crear la base de datos:

drop database if exists AkihabaraDB;
create database AkihabaraDB;
use AkihabaraDB;
drop table if exists productos;
create table productos (
id int auto_increment primary key,
nombre varchar(255),
categoria varchar(100),
precio decimal(10, 2),
stock int
);


Requisitos:

Para este programa se necesita tener MYSQL instalado y fue creado usando la versión 21.0.5 de Java


Estructura de las clases:
MainApp
DatabaseConnection
ProductoDAO
Productootaku
LlmService
SetupDatos
InterfazConsola