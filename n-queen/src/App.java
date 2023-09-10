import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        int n = (args.length > 0) ? Integer.parseInt(args[0]) : getN();

        NQueen nQueen = new NQueen(n);
        nQueen.solve();
    }

    public static int getN() {

        int n = 4;
        Scanner userIn = new Scanner(System.in);
        System.out.println("Enter a number for (n): ");
        if (userIn.hasNextLine()) {
            n = Integer.parseInt(userIn.nextLine());
        }

        userIn.close();

        return n;
    }
}
