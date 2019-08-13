/**
 * left-leaning red-black balanced search trees
 *
 * Represent 2-3 trees as a BST
 * Use internal left-leaning links as glue for 3-nodes
 *
 * Properties:
 * No node has two red links connect to it (2-3 nodes, red link represent a 3 node)
 * Every path from root to nul link has the same number of black links
 * Red links lean left (larger key is the root)
 *
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> extends BST {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        boolean color; // color of parent link

        Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    // null nodes are black
    private boolean isRed(Node x) {
        if (x == null) return false; // null links are black
        return x.color == RED;
    }


    // Right child red, left child black: rotate left
    // Left child, left-left grandchild red: rotate right
    // Both children red: flip colors
    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, RED); // insert at the bottom
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }


        // these few lines provides near-perfect balance

        // leaf left
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);

        // balance 4-node
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);

        // split 4-node
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        return h;
    }

    private Node rotateLeft(Node h) {
        //assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        assert isRed(h);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;

    }

    private void flipColors(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);

        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

}
