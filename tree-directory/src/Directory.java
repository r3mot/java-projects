import java.io.File;
import java.nio.file.Paths;

public class Directory {

    /**
     * Parse a directory and print the tree
     * 
     * @param path The path to parse
     */
    public void parse(String path) {

        try {

            File directory = this.resolve(path);
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

    /**
     * Get a directory from a given path
     * Checks if the path is absolute or relative
     * 
     * @param path The path to get the directory from
     * @return File object representing the directory
     */
    private File resolve(String path) {
        String dir = System.getProperty("user.dir"); // working directory
        return Paths.get(path).isAbsolute() ? new File(path) : new File(dir, path);
    }

}
