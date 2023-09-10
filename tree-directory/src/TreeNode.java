import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private String value;
    private List<TreeNode> children;

    public TreeNode(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    // Add a child node
    public boolean addChild(TreeNode child) {

        return children.add(child);
    }

    public boolean buildTree(String path) {
        File[] files = new File(path).listFiles();
        for (File file : files) {
            TreeNode child = new TreeNode(file.getName());
            if (!addChild(child)) {
                System.err.println("Failed to add child node");
                return false;
            }

            if (file.isDirectory()) {
                child.buildTree(file.getPath());
            }
        }

        return true;
    }

    // Print the tree structure
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
