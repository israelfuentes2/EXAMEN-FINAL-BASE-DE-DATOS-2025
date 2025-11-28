-- DDL: esquema finanzas_familiares (PostgreSQL)
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

-- Datos de ejemplo (5 ciudades, 6 miembros, varios ingresos/gastos)
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
