# Proyecto JDBC - Finanzas Familiares

Descripción del Proyecto

Este proyecto implementa una aplicación Java usando JDBC (sin ORM) para conectarse a una base de datos PostgreSQL del sistema Finanzas Familiares, desarrollado originalmente en la Unidad 4.

La aplicación ejecuta:

Conexión JDBC a PostgreSQL

5 consultas SQL basadas en álgebra relacional

Manejo de excepciones

Procesamiento de resultados

Uniones, joins, proyecciones y agrupaciones

El proyecto está diseñado para ejecutarse en Visual Studio Code con Maven y PostgreSQL.

Estructura del Proyecto
finanzas-familiares-jdbc/
│── src/
│   └── main/java/
│       ├── conexion/ConnectionFactory.java
│       ├── dao/FinanzasDAO.java
│       ├── modelo/Miembro.java
│       └── App.java
│── sql/finanzas.sql
│── docs/informe_tecnico.md
│── pom.xml
│── README.md

Tecnologías Utilizadas
Componente	Tecnología
Lenguaje	Java 17
Base de Datos	PostgreSQL
Driver JDBC	org.postgresql:postgresql:42.7.3
Administrador BD	pgAdmin 4 y SQL Shell (psql)
IDE	Visual Studio Code
Compilador	Maven
🗄 Creación de la Base de Datos (PostgreSQL)

En pgAdmin → Query Tool, copia y pega el siguiente contenido (DDL + inserts):

Archivo /sql/finanzas.sql

CREATE TABLE Ciudad(
 idCiudad SERIAL PRIMARY KEY,
 nombreCiudad VARCHAR(100),
 departamento VARCHAR(100)
);

CREATE TABLE Miembro(
 idMiembro SERIAL PRIMARY KEY,
 nombre VARCHAR(100),
 tipoMiembro VARCHAR(50),
 fechaNac DATE,
 idCiudad INT REFERENCES Ciudad(idCiudad)
);

CREATE TABLE Ingreso(
 idIngreso SERIAL PRIMARY KEY,
 idMiembro INT REFERENCES Miembro(idMiembro),
 tipoIngreso VARCHAR(80),
 monto DECIMAL(10,2),
 fechaIngreso DATE
);

CREATE TABLE Gasto(
 idGasto SERIAL PRIMARY KEY,
 idMiembro INT REFERENCES Miembro(idMiembro),
 categoria VARCHAR(80),
 valor DECIMAL(10,2),
 fechaGasto DATE
);

INSERT INTO Ciudad(nombreCiudad, departamento) VALUES
('Cartagena','Bolívar'),
('Medellín','Antioquia'),
('Bogotá','Cundinamarca'),
('Barranquilla','Atlántico'),
('Cali','Valle');

INSERT INTO Miembro(nombre, tipoMiembro, fechaNac, idCiudad) VALUES
('Ana López','Madre','1980-03-05',1),
('Carlos Pérez','Padre','1978-07-12',3),
('María Gómez','Hija','2005-09-20',1),
('Pedro Ruiz','Hijo','2010-11-02',2),
('Laura Díaz','Madre','1990-01-15',4),
('Jorge Torres','Padre','1985-05-30',5);

INSERT INTO Ingreso(idMiembro, tipoIngreso, monto, fechaIngreso) VALUES
(1,'Salario',1500.00,'2024-01-10'),
(2,'Salario',2000.00,'2024-01-05'),
(3,'Beca',300.00,'2024-02-01'),
(1,'Negocio',250.00,'2024-02-15');

INSERT INTO Gasto(idMiembro, categoria, valor, fechaGasto) VALUES
(1,'Alimentación',200.00,'2024-02-10'),
(2,'Transporte',50.00,'2024-02-11'),
(3,'Educación',100.00,'2024-02-12'),
(1,'Servicios',80.00,'2024-02-13'),
(5,'Salud',120.00,'2024-02-14');

🔌 Configuración de la Conexión JDBC

En ConnectionFactory.java,

private static final String URL = "jdbc:postgresql://localhost:5432/finanzas_familiares";
private static final String USER = "postgres";
private static final String PASSWORD = "2424";  //
