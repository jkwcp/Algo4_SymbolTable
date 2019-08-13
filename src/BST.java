// guarantee: search-N, insert-N
// average case:search hit-1.39lgN, insert-1.39lgN
// ordered ops
// operations on keys

// Proposition:
//  if N distinct keys into BST random order, expected number of compares for a search/insert is 2lnN
//  if N distinct keys are inserted in random order, expected height of tree is 4.311lnN


import edu.princeton.cs.algs4.Queue;

public class BST<Key extends Comparable<Key>, Value>{
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int count;

        public Node(Key key, Value val) {
            key = key;
            val = val;
        }
    }

    // Associate value with key
    //  key in the tree, reset value
    //  key not in the tree, add new node
    // Using recursive (extends the power)
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    // number of compares: 1 + depth of node
    // the order how keys come in
    // best case: perfectly balance
    // worst case: no difference than a link list
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;

        x.count = 1 + size(x.left) + size(x.right) + 1;
        return x;
    }

    // starting at root, if greater, go right, if less, go left, if equal, search hit
    // Number of compares: 1 + depth of node
    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x.val;
        }
        return null;
    }

    // Delete: make symbol dynamic

    // Lazy approach: put(key, null);
    // Set its value to null
    // Leave the key in tree to guide searches (not not equal in search)
    // Tombstone (memory) overload

    //
    //
    public void delete(Key key) {
        put(key, null);
    }

    private Node delete(Node x, Key key) {
        // BST: Search for node t containing key k (Hibbard)
        // Not symmetric, end up with unbalanced tree
        // => sqrt(N) per op
        if (x == null) return null;
        int cmp = key.compareTo(x.key);

        // search for the key
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        // hit the key
        else {
            // no right child
            if (x.right == null)
                return x.left;
            // no left child
            if (x.left == null)
                return x.right;

            // replace with successor
            Node t = x;
            //x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }

        // update subtree counts
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    // floor
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);

        if (cmp == 0)
            return x;
        if (cmp < 0)
            return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null)
            return t;
        else
            return x;
    }

    // ceiling

    // size
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.count;
    }

    // rank
    //  how many keys < k
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            return rank(key, x.left);
        else if (cmp > 0)
            return 1 + size(x.left) + rank(key, x.right);
        else
            return size(x.left);
    }

    // Travers left subtree
    // Enqueue key
    // Traverse right subtree
    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node x, Queue<Key> q) {
        if (x == null) return;

        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }


}
