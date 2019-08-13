import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


// Sequential / Binary search implementation
// Focus on Binary search tree implementation which is BST.java
public class ST<Key extends Comparable<Key>, Value> {
    private int N;
    private Key[] keys;
    private Value[] vals;


    public ST() {

    }

    public void put(Key key, Value val) {

    }

    // use binary search tech
    // need to move all greater element over
    // logN
    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    private int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo ) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0)
                hi = mid - 1;
            else if (cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    public void delete(Key key) {
        put(key, null);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return N;
    }



    public Iterable<Key> keys() {
        return null;
    }





    public static void main(String[] args) {
        ST<String, Integer> st = new ST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }

    /*
        Conventions:
            values are not null
            get() returns null if key not present
            put() overwrites old value with new value

            intended consequences:
                easy to implement contains():
                    public boolean contains(Key key) { return get(key) != null }
                lazy delete():
                    public void delete(Key key) { put(key, null); }
     */

    /*
        Value type: any generic type;

        Key type:
            Comparable types, use compareTo();
            equals()
            hashCode()...

            best practice: immutable types for symbol table keys
     */

    /*
        equals():
            Reflexive:
                x.equals(x) == true
            Symmetric:
                x.equals(y) <=> y.equals(x)
            Transitive:
                x->y->z
            Non-null: x.equals(null) == false (no object equals to null)

            x==y: do x and y refer to the same object?


            Actual implementation:
                1. (y==this) return true (Optimization for reference equality)
                2. (y == null) return false; (check against null)
                3. (y.getClass() != this.getClass()) return false; (Check that two objects are of the same type and cast)
                4. Customized equal conditions  (Compare each significant fiels)
                    primitive type, use ==
                    object, use equals()
                    array, apply to each entry (Arrays.equals(a, b) or Arrays.deepEquals(a, b); but not a.equals(b));
     */

    /*
        BST (binary search trees)
        Nodes with two links..right left

        BST: binary tree in symmetric order

        Binary tree:
            either empty, or two disjoint binary trees(left and right)

        Symmetric order:
            each node has a key;
            larger than all keys in its left subtree
            smaller than all keys in its right subtree
     */


}