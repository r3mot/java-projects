package registrar.tree;

public class Node<K, V> {
    public enum BalanceType {
        BALANCED,
        LEFT_LEFT,
        LEFT_RIGHT,
        RIGHT_RIGHT,
        RIGHT_LEFT
    }

    public K key;
    public V value;
    public Node<K, V> mLeft;
    public Node<K, V> mRight;
    public Node<K, V> mParent;
    public int mHeight;
    public int MAXHEIGHT;

    public Node() {
        mLeft = null;
        mRight = null;
        mParent = null;
        mHeight = 0;
        MAXHEIGHT = 0;
    }

    public Node(K key, V value) {
        this();
        this.key = key;
        this.value = value;
    }

    /**
     * Determines the balance factor of the node (subtree).
     * balance must be in range [-1, 1]
     * 
     * @return BalanceType enum
     */
    public BalanceType getBalanceType() {
        int left = (mLeft != null) ? mLeft.mHeight : -1;
        int right = (mRight != null) ? mRight.mHeight : -1;

        if (right - left >= 2) { // right child height - left child height
            int rightLeft = (mRight != null && mRight.mLeft != null) ? mRight.mLeft.mHeight : -1;
            int rightRight = (mRight != null && mRight.mRight != null) ? mRight.mRight.mHeight : -1;

            if (rightLeft > rightRight)
                return BalanceType.RIGHT_LEFT;
            else
                return BalanceType.RIGHT_RIGHT;

        } else if (left - right >= 2) { // left child height - right child height
            int leftLeft = (mLeft != null && mLeft.mLeft != null) ? mLeft.mLeft.mHeight : -1;
            int leftRight = (mLeft != null && mLeft.mRight != null) ? mLeft.mRight.mHeight : -1;

            if (leftLeft > leftRight)
                return BalanceType.LEFT_LEFT;
            else
                return BalanceType.LEFT_RIGHT;
        }

        return BalanceType.BALANCED;
    }
}