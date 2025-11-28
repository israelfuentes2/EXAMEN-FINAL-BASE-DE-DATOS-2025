import dao.FinanzasDAO;
import java.util.List;

/**
 * Clase principal para ejecutar y probar las consultas.
 */
public class App {
    public static void main(String[] args) {
        FinanzasDAO dao = new FinanzasDAO();

        System.out.println("---- CONSULTA 1: MIEMBROS MAYORES DE 18 ----");
        List<String> c1 = dao.consulta1();
        c1.forEach(System.out::println);

        System.out.println("\n---- CONSULTA 2: PROYECCIÓN (NOMBRE, CIUDAD) ----");
        List<String> c2 = dao.consulta2();
        c2.forEach(System.out::println);

        System.out.println("\n---- CONSULTA 3: UNION INGRESOS/GASTOS ----");
        List<String> c3 = dao.consulta3();
        c3.forEach(System.out::println);

        System.out.println("\n---- CONSULTA 4: JOIN MIEMBRO - CIUDAD ----");
        List<String> c4 = dao.consulta4();
        c4.forEach(System.out::println);

        System.out.println("\n---- CONSULTA 5: TOTAL GASTOS POR MIEMBRO ----");
        List<String> c5 = dao.consulta5();
        c5.forEach(System.out::println);
    }
}
