package dao;

import conexion.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FinanzasDAO: métodos JDBC que implementan las 5 consultas seleccionadas
 * basadas en la actividad de la Unidad 4 (Finanzas Familiares).
 */
public class FinanzasDAO {

    // 1. Miembros mayores de 18 años
    public List<String> consulta1() {
        String sql = "SELECT idMiembro, nombre, fechaNac FROM Miembro WHERE EXTRACT(YEAR FROM AGE(CURRENT_DATE, fechaNac)) >= 18";
        List<String> r = new ArrayList<>();
        try (Connection c = ConnectionFactory.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            while (rs.next()) {
                r.add(rs.getInt("idMiembro") + " | " + rs.getString("nombre") + " | " + rs.getDate("fechaNac"));
            }
        } catch (SQLException e) {
            System.out.println("ERROR consulta1(): " + e.getMessage());
        }
        return r;
    }

    // 2. Proyección: nombre y ciudad
    public List<String> consulta2() {
        String sql = "SELECT nombre, idCiudad FROM Miembro";
        List<String> r = new ArrayList<>();
        try (Connection c = ConnectionFactory.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            while (rs.next()) {
                r.add(rs.getString("nombre") + " | CiudadId: " + rs.getInt("idCiudad"));
            }
        } catch (SQLException e) {
            System.out.println("ERROR consulta2(): " + e.getMessage());
        }
        return r;
    }

    // 3. Unión: ingresos y gastos (tipo marcado)
    public List<String> consulta3() {
        String sql = "SELECT idMiembro, monto AS valor, 'Ingreso' AS tipo FROM Ingreso UNION SELECT idMiembro, valor AS valor, 'Gasto' AS tipo FROM Gasto";
        List<String> r = new ArrayList<>();
        try (Connection c = ConnectionFactory.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            while (rs.next()) {
                r.add("Miembro " + rs.getInt("idMiembro") + " | " + rs.getBigDecimal("valor") + " | " + rs.getString("tipo"));
            }
        } catch (SQLException e) {
            System.out.println("ERROR consulta3(): " + e.getMessage());
        }
        return r;
    }

    // 4. Join Miembro - Ciudad
    public List<String> consulta4() {
        String sql = "SELECT m.nombre, c.nombreCiudad FROM Miembro m JOIN Ciudad c ON c.idCiudad = m.idCiudad";
        List<String> r = new ArrayList<>();
        try (Connection c = ConnectionFactory.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            while (rs.next()) {
                r.add(rs.getString(1) + " | " + rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("ERROR consulta4(): " + e.getMessage());
        }
        return r;
    }

    // 5. Total de gastos por miembro (agregación)
    public List<String> consulta5() {
        String sql = "SELECT idMiembro, SUM(valor) AS totalGastos FROM Gasto GROUP BY idMiembro";
        List<String> r = new ArrayList<>();
        try (Connection c = ConnectionFactory.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            while (rs.next()) {
                r.add("Miembro " + rs.getInt("idMiembro") + " | Total: " + rs.getBigDecimal("totalGastos"));
            }
        } catch (SQLException e) {
            System.out.println("ERROR consulta5(): " + e.getMessage());
        }
        return r;
    }
}
