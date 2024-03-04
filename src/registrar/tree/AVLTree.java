package registrar.tree;

import java.util.List;

public class AVLTree<K, V> {

    private Node<K, V> mHead;
    private boolean debug = false;

    public Node<K, V> getHead() {
        return mHead;
    }

    /**
     * Check if a key is in the tree.
     * 
     * @param key
     * @complexity
     */
    public boolean contains(K key) {
        return privateFind(key) != null;
    }

    /**
     * Find a node in the tree.
     * 
     * @param key - the key to search for
     * @return the value of the node with the given key, or null if not found
     * @complexity
     */
    public V find(K key) {
        return privateFind(key).value;
    }

    /**
     * Dump the tree into a list.
     * 
     * @param leftToRight - the list to dump into
     * @complexity
     */
    public void dump(List<V> leftToRight) {
        dumpTree(leftToRight, mHead);
        if (debug)
            print("", mHead, false);
    }

    /**
     * Print the tree.
     */
    public void printTree() {
        print("", mHead, false);
    }

    /**
     * Trigger to debug the tree.
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * Insert a node to the tree.
     * 
     * Example: insert(courseId, Course);
     * 
     * @param key   - the key to insert
     * @param value - the value to insert
     * @complexity
     */
    public void insert(K key, V value) {
        if (mHead == null) { // empty tree
            mHead = new Node<K, V>(key, value);
            return;
        }

        Node<K, V> found = privateFind(key);

        if (found.key.equals(key)) {
            return; // no dupes
        } else if (key.toString().compareTo(found.key.toString()) < 0) {
            found.mLeft = new Node<K, V>(key, value);
            found.mLeft.mParent = found;
        } else {
            found.mRight = new Node<K, V>(key, value);
            found.mRight.mParent = found;
        }

        loopToHead(found); // update heights and balance
    }

    /**
     * Starting at a given node (a node that was just inserted or removed),
     * loop up to the head of the tree and update heights and balance.
     * 
     * @param found the node that was inserted / removed
     * @complexity
     */
    private void loopToHead(Node<K, V> found) {
        while (found != null) {
            measureNode(found);
            balance(found);
            found = found.mParent; // keep going up
        }
    }

    /**
     * Update the height of a node based on the height of its children.
     * 
     * @param node the node to update
     * @complexity
     */
    private void measureNode(Node<K, V> node) {
        int left = node.mLeft != null ? node.mLeft.mHeight : -1;
        int right = node.mRight != null ? node.mRight.mHeight : -1;
        node.mHeight = left > right ? left + 1 : right + 1;
    }

    /**
     * Traverse the tree and balance it.
     * 
     * @param node the node to start at
     * @return true if a rotation was performed, false otherwise
     * @complexity
     */
    private boolean balance(Node<K, V> node) {
        if (node == null) {
            return false;
        }

        while (node != null) {
            Node.BalanceType tCheck = node.getBalanceType();
            if (tCheck != Node.BalanceType.BALANCED) {
                boolean mHeadCheck = mHead == node;

                if (tCheck == Node.BalanceType.LEFT_LEFT) {
                    node = rotateLeft(node);
                } else if (tCheck == Node.BalanceType.LEFT_RIGHT) {
                    node.mLeft = rotateRight(node.mLeft);
                    node = rotateLeft(node);
                } else if (tCheck == Node.BalanceType.RIGHT_RIGHT) {
                    node = rotateRight(node);
                } else if (tCheck == Node.BalanceType.RIGHT_LEFT) {
                    node.mRight = rotateLeft(node.mRight);
                    node = rotateRight(node);
                }

                if (mHeadCheck) {
                    mHead = node;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Rotate a node to the right.
     * 
     * @param node the node to rotate
     * @return the new root of the subtree
     * @complexity
     */
    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> P = node;
        Node<K, V> Q = node.mRight;

        P.mRight = Q.mLeft;
        if (Q.mLeft != null) {
            Q.mLeft.mParent = P;
        }

        Q.mLeft = P;

        Q.mParent = P.mParent;
        P.mParent = Q;

        measureNode(P);
        if (Q.mParent != null) {
            if (Q.mParent.mRight == P)
                Q.mParent.mRight = Q;
            else
                Q.mParent.mLeft = Q;
        }

        return Q;
    }

    /**
     * Rotate a node to the left.
     * 
     * @param node the node to rotate
     * @return the new root of the subtree
     * @complexity
     */
    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> P = node;
        Node<K, V> Q = P.mLeft;

        P.mLeft = Q.mRight;
        if (Q.mRight != null)
            Q.mRight.mParent = P;
        Q.mRight = P;

        Q.mParent = P.mParent;
        P.mParent = Q;

        measureNode(P);
        if (Q.mParent != null) {
            if (Q.mParent.mRight == P)
                Q.mParent.mRight = Q;
            else
                Q.mParent.mLeft = Q;
        }
        return Q;
    }

    /**
     * Private method to find a node in the tree with a given key.
     * 
     * @param key the key to search for
     * @return the node with the given key, or null if not found
     * @complexity
     */
    private Node<K, V> privateFind(K key) {
        if (mHead == null)
            return null;

        Node<K, V> walker = mHead;
        while (true) {

            // compare the keys
            int cmp = key.toString().compareTo(walker.key.toString());

            if (cmp == 0)
                return walker;
            else if (cmp < 0) { // no left child
                if (walker.mLeft == null)
                    return walker;
                else
                    walker = walker.mLeft;
            } else {
                if (walker.mRight == null) // no right child
                    return walker;
                else
                    walker = walker.mRight;
            }
        }
    }

    /**
     * Dump the tree into a list.
     * 
     * @param leftToRight - the list to dump into
     * @param node        - the node to dump
     * @complexity
     */
    private void dumpTree(List<V> leftToRight, Node<K, V> node) {
        if (node != null) {
            if (node.mLeft != null) {
                dumpTree(leftToRight, node.mLeft);
            }
            if (leftToRight != null) {
                leftToRight.add(node.value);
            }

            if (node.mRight != null) {
                dumpTree(leftToRight, node.mRight);
            }
        }
    }

    /**
     * Print the tree.
     * 
     * @param space  - the space to print before each node
     * @param node   - the node to print
     * @param isLeft - true if the node is a left child, false otherwise
     * @complexity
     */
    private void print(String space, Node<K, V> node, boolean isLeft) {

        if (node != null) {
            System.out.print(space);
            System.out.print(isLeft ? "|-- " : "\\-- ");
            System.out.println(node.key + "(" + node.mHeight + ")");

            print(space + (isLeft ? "|   " : "    "), node.mLeft, true);
            print(space + (isLeft ? "|   " : "    "), node.mRight, false);
        }
    }
}
