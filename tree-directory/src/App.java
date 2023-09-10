import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        CLI cli = new CLI();

        File directory = new File(cli.getPath());
        TreeNode root = new TreeNode(directory.getName());
        if (!root.buildTree(directory)) {
            System.err.println("Failed to build tree");
            return;
        }

        root.print();

        cli.close();
    }

}
