/**
 * Intersections among geometric objects
 * CAD, games, movies, virtual reality, databases, ....
 * BST (and extensions) is a efficient solution
 *
 * 1d range search
 * Application: database queries
 *
 * range search/range count
 *
 * geometric interpretation:
 *  keys are point on a line
 *  find/count points in a given 1d interval
 */
public class RangeSearch<Key extends Comparable<Key>, Value> extends RedBlackBST {

    public int size (Key lo, Key hi) {
        if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

    private boolean contains(Key key) {
        return get(key) != null;
    }

}
