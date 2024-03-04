
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Directory directory = new Directory();

        if (args.length > 0) { // cmd line args

            directory.parse(args[0]);
            return;

        } else {

            Scanner userIn = new Scanner(System.in);
            System.out.println("Enter a path to parse: ");
            directory.parse(userIn.nextLine());
            userIn.close();

        }
    }

}
