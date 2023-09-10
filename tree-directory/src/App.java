
public class App {
    public static void main(String[] args) throws Exception {
        CLI cli = new CLI();
        String path = cli.getPath();

        TreeNode root = new TreeNode(path);
        if (!root.buildTree(path)) {
            System.err.println("Failed to build tree");
            return;
        }

        root.print();

        cli.close();
    }
}
