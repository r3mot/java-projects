import java.util.Scanner;

public class CLI {
    private Scanner userIn;

    public CLI() {
        this.userIn = new Scanner(System.in);
    }

    public String getPath() {
        System.out.println("Please enter a directory path: ");
        if (userIn.hasNext()) {
            return userIn.next();
        }

        return "";
    }

    public void close() {
        System.out.println("Closing CLI...");
        userIn.close();
    }
}
