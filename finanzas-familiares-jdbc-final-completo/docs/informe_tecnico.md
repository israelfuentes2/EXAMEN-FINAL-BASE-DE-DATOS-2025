# Informe Técnico - Proyecto: Finanzas Familiares (Unidad 4)

**Integrante:** Israel David Fuentes Ramos  
**Asignatura:** Bases de Datos I  
**Profesor:** Jhon Carlos Arrieta  
**Fecha:** (poner la fecha de entrega)

---

## 1. Resumen ejecutivo
Esta entrega contiene una aplicación Java (Maven) que ejecuta consultas SQL sobre la base de datos `finanzas_familiares` en PostgreSQL. Se implementan cinco consultas representativas del trabajo de la Unidad 4 (selección, proyección, unión, join y agregación). El proyecto usa JDBC puro (sin ORM) y está pensado para ejecutarse desde VS Code.

## 2. Tecnologías
- Java 17, Maven, JDBC
- PostgreSQL
- VS Code (recomendado)
- Dependencia: org.postgresql:postgresql:42.7.3

## 3. Estructura del repositorio (incluida en ZIP)
- `pom.xml` - Maven
- `src/main/java/conexion/ConnectionFactory.java`
- `src/main/java/dao/FinanzasDAO.java`
- `src/main/java/modelo/Miembro.java`
- `src/main/java/App.java`
- `sql/finanzas.sql` - DDL y datos de ejemplo
- `README.md`
- `docs/informe_tecnico.md`

## 4. DDL (resumen)
El script `sql/finanzas.sql` crea las tablas: Ciudad, Miembro, Ingreso y Gasto, definiendo claves primarias y foráneas. Incluye inserts de ejemplo para facilitar pruebas.

## 5. Consultas implementadas (explicación)
1. **Miembros mayores de 18 años**  
   ```sql
   SELECT idMiembro, nombre, fechaNac
   FROM Miembro
   WHERE EXTRACT(YEAR FROM AGE(CURRENT_DATE, fechaNac)) >= 18;
   ```
   Uso: filtros por edad (SELECT con condición y función de fecha).

2. **Proyección nombre y ciudad**  
   ```sql
   SELECT nombre, idCiudad FROM Miembro;
   ```
   Uso: mostrar solo columnas necesarias.

3. **Unión Ingreso ∪ Gasto**  
   ```sql
   SELECT idMiembro, monto AS valor, 'Ingreso' AS tipo FROM Ingreso
   UNION
   SELECT idMiembro, valor AS valor, 'Gasto' AS tipo FROM Gasto;
   ```
   Uso: combinar dos fuentes de valores financieros.

4. **Join Miembro - Ciudad**  
   ```sql
   SELECT m.nombre, c.nombreCiudad
   FROM Miembro m
   JOIN Ciudad c ON c.idCiudad = m.idCiudad;
   ```
   Uso: combinar información relacional en una sola consulta.

5. **Total de gastos por miembro (agregación)**  
   ```sql
   SELECT idMiembro, SUM(valor) AS totalGastos FROM Gasto GROUP BY idMiembro;
   ```
   Uso: funciones agregadas y GROUP BY para reportes.

## 6. Código y patrones
- `ConnectionFactory` centraliza la conexión JDBC.
- `FinanzasDAO` contiene métodos que ejecutan las consultas, usan `try-with-resources` para cerrar recursos y capturan `SQLException`.
- `App.java` demuestra la ejecución de las cinco consultas y muestra resultados por consola.

## 7. Procedimiento para ejecutar (reproducibilidad)
1. Crear la base de datos en PostgreSQL:
   ```sql
   CREATE DATABASE finanzas_familiares;
   ```
2. Ejecutar el script `sql/finanzas.sql` usando psql o PgAdmin.
3. Ajustar credenciales en `ConnectionFactory`.
4. Desde VS Code o línea de comandos: `mvn compile` y `mvn exec:java -Dexec.mainClass="App"` (configurar plugin exec si se desea).
5. Revisar la salida por consola (App imprime resultados).

## 8. Manejo de errores
- Todas las operaciones JDBC capturan `SQLException` y muestran mensajes resumidos.
- Recomendado: habilitar logs y no mostrar credenciales en errores.
- Para operaciones de modificación (INSERT/UPDATE/DELETE) usar transacciones y rollback en caso de error.

## 9. Evidencia y pruebas
En `sql/finanzas.sql` se incluyen datos de ejemplo para pruebas. El proyecto al ejecutar `App` imprimirá resultados que pueden capturarse como evidencia (pantallazos o video).

## 10. Enlaces
- Video de sustentación (ejemplo): https://youtu.be/Ufvna-rqED8  
- Repo GitHub del estudiante: https://github.com/israelfuentes2/Base-de-datos-UNIDAD4_APRENDIZAJE.git

---

## 11. Conclusiones
El proyecto demuestra la integración entre Java (JDBC) y PostgreSQL para ejecutar consultas representativas del álgebra relacional, con buen manejo de recursos y estructuras limpias. Se cumple la consigna de no usar ORM y de implementar consultas SELECT representativas del caso de estudio.
