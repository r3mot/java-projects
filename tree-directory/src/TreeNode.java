import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A tree node that represents a directory
 * 
 * @value The name of the directory
 * @children The children of the directory
 * 
 */

public class TreeNode {
    private String value;
    private List<TreeNode> children;

    /**
     * Create a new tree node
     * 
     * @param value of the node ( directory name )
     */
    public TreeNode(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    /**
     * Add a child node to the current node
     * 
     * @param child The child node to add
     * @return true if the child node was added, false otherwise
     */
    public boolean addChild(TreeNode child) {
        return children.add(child);
    }

    public boolean buildTree(File root) {
        File[] files = root.listFiles();
        for (File file : files) {

            if (!Filter.excluded(file)) { // if excluding, dont care about adding child
                TreeNode child = new TreeNode(file.getName());

                if (!addChild(child)) {
                    System.err.println("Failed to add child node");
                    return false;
                }

                if (file.isDirectory()) {
                    child.buildTree(new File(file.getPath()));
                }
            }

        }

        return true;
    }

    /**
     * Print the directory tree
     */
    public void print() {
        print("", true);
    }

    private void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "\u2514\u2500\u2500 " : "\u2502\u2500\u2500 ") + value);
        for (int i = 0; i < children.size() - 1; i++) {
            children.get(i).print(prefix + (isTail ? "    " : "\u2502   "), false);
        }
        if (children.size() > 0) {
            children.get(children.size() - 1).print(prefix + (isTail ? "    " : "\u2502   "), true);
        }
    }
}
