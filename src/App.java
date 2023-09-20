import registrar.Registrar;

public class App {
    public static void main(String[] args) throws Exception {

        int numCourses = 10;
        Registrar registrar = new Registrar(numCourses);

        registrar.dumpStudents();
        registrar.dumpCourses();
    }
}
