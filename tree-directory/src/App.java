import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner userIn = new Scanner(System.in);
        String path = userIn.nextLine();

        TreeNode root = new TreeNode(path);
        if (!root.buildTree(path)) {
            System.err.println("Failed to build tree");
            return;
        }

        root.print();

        userIn.close();
    }
}
