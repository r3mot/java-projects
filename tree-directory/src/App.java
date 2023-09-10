import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        if (args.length > 0) {
            parseDirectory(args[0]);
            return;
        } else {
            Scanner userIn = new Scanner(System.in);
            System.out.println("Enter a path to parse: ");
            if (userIn.hasNextLine()) {
                parseDirectory(userIn.nextLine());
                userIn.close();
                return;
            }
            userIn.close();
        }
    }

    public static void parseDirectory(String path) {

        try {

            File directory = new File(path);
            TreeNode root = new TreeNode(directory.getName());

            if (!root.buildTree(directory)) {
                System.err.println("Failed to build tree");
                return;
            }

            root.print();

        } catch (Exception e) {
            System.err.println("Path does not exist");
        }
    }

}
