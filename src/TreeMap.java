import java.util.Arrays;

/**
 * A TreeMap that stores key/value pairs in a binary search tree (BST)
 * @author  Maya Rao
 * @version 11-5-24
 *
 * @param <K>   keys stored in the TreeMap; must be Comparable
 * @param <V>   values mapped to the keys
 */

public class TreeMap<K extends Comparable<K>, V> implements TreeMapInterface<K, V> {
    // instance variables
    /** Number of elements in the tree */
    private int size;

    /** Reference to the first TreeMapNode in the tree */
    private TreeMapNode<K, V> root;

    // constructor
    /** Constructs an empty Tree Map with no elements/nodes */
    public TreeMap() {
        root = null;
    }

    // methods
    /**
     * Retrieves a count of elements being maintained by the tree
     *
     * @return  the size of the tree (count of elements)
     */
    public int size() {
        return size;
    }

    /** Clears the entire tree */
    public void clear() {
        // making root null will get rid of the rest of the nodes
        root = null;
        size = 0;
    }

    /**
     * Retrieves the corresponding value for the specified key.
     *
     * @param key key of interest.
     * @return value corresponding to the specified key, or null if the key is not found.
     */
    @Override
    public V get(K key) {
        // check if key is null
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }

        // return result of private recursive method
        return get(root, key);
    }

    /**
     * Retrieves the corresponding value for the specified key
     * Clients don't use this method
     *
     * @param currentNode   keeps track of the journey down the tree
     * @param key   key of interest
     * @return  value corresponding to the specified key, or null if the key is not found
     */
    private V get(TreeMapNode<K, V> currentNode, K key) {
        // if currentNode is null
        if (currentNode == null) {
            // return null because the value wasn't found
            return null;
        }

        // if key equals currentNode key
        if (key.compareTo(currentNode.key) == 0) {
            // return currentNode value
            return currentNode.value;
        }
        // if key is less than currentNode key
        else if (key.compareTo(currentNode.key) < 0) {
            // travel through left subtree
            return get(currentNode.left, key);
        } else {
            // travel through right subtree (key is greater than currentNode key)
            return get(currentNode.right, key);
        }
    }

    /**
     * Adds a key/value pair to the tree map.
     *
     * @param key   the key. If not already in the tree, the key/value pair is added.  If already in the
     *              tree, the existing value is replaced with the one specified here.
     * @param value the value in the key/value pair.
     */
    @Override
    public void put(K key, V value) {
        // check if key or value is null
        if (key == null || value == null) {
            throw new IllegalArgumentException("key and value must not be null");
        }

        // ensure the root is correctly updated after adding a node
        root = put(root, key, value);
    }

    /**
     * Adds a key/value pair to the tree map
     * Clients don't use this method
     *
     * @param currentNode keeps track of the journey down the tree
     * @param key the key. If not already in the tree, the key/value pair is added.  If already in the
     *            tree, the existing value is replaced with the one specified here.
     * @param value the value in the key/value pair
     * @return added node with specified key and value
     */
    private TreeMapNode<K, V> put(TreeMapNode<K, V> currentNode, K key, V value) {
        // if currentNode is null
        if (currentNode == null) {
            // currentNode is now a new TreeMapNode with specified key and value
            currentNode = new TreeMapNode<K, V>(key, value);

            // increment size after adding node to tree
            size++;
        }
        // if key equals currentNode key
        else if (key.compareTo(currentNode.key) == 0) {
            // replace currentNode's value with specified value
            currentNode.value = value;
        }
        // if key is less than currentNode key
        else if (key.compareTo(currentNode.key) < 0) {
            // ensure the left subtree is correctly updated after adding a node
            currentNode.left = put(currentNode.left, key, value);
        } else {
            // ensure the right subtree is correctly updated after adding a node (key is greater than currentNode key)
            currentNode.right = put(currentNode.right, key, value);
        }
        return currentNode;
    }

    /**
     * Checks the tree to see if it contains the specified key.
     *
     * @param key the key to search for.
     * @return true, if the key is in the tree map; false, if not.
     */
    @Override
    public boolean containsKey(K key) {
        // check if key is null
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }

        // return result of private recursive method
        return containsKey(root, key);
    }

    /**
     * Checks the tree to see if it contains the specified key
     * Clients don't use this method
     *
     * @param currentNode keeps track of the journey down the tree
     * @param key   key to search for
     * @return  true if key is in the tree map; false if not
     */
    private boolean containsKey(TreeMapNode<K, V> currentNode, K key) {
        // if currentNode is null
        if (currentNode == null) {
            // return false because there is no key to be found
            return false;
        } else {
            int compare = key.compareTo(currentNode.key);
            if (compare == 0) {
                // key has been found
                return true;
            } else if (compare < 0) {
                // return result of travelling through left subtree
                return containsKey(currentNode.left, key);
            } else {   // compare > 0
                // return result of travelling through right subtree
                return containsKey(currentNode.right, key);
            }
        }
    }

    /**
     * Retrieves an array of key data from the map in order
     *
     * @param array to fill in. If smaller than the map's size, a new array will be created.  If larger than the
     *              map's size, data will be filled in from index 0, with a null reference just after the copied-in data
     * @return a reference to the filled-in array; may be a different array than the one passed in.
     */
    @Override
    public K[] toKeyArray(K[] array) {
        // check if key array template is null
        if (array == null) {
            throw new IllegalArgumentException("key array template must not be null");
        }

        // if size of key array is less than size of tree map
        if (array.length < size) {
            // create new array to fit all the keys if given array is too small
            array = Arrays.copyOf(array, size);
        }

        // start filling the key array from the root, index starts at 0
        fillKeyArray(root, array, 0);

        // if given array is greater than the size of tree map
        if (size < array.length) {
            // fill extra space with null
            array[size] = null;
        }

        // return key array
        return array;
    }

    /**
     * Retrieves an array of key data from the map in order
     * Clients don't use this method
     *
     * @param currentNode   keeps track of the journey down the tree
     * @param array to fill in. If smaller than the map's size, a new array will be created.  If larger than the
     *              map's size, data will be filled in from index 0, with a null reference just after the copied-in data
     * @param currentIndex  next available spot in the array after a key is added
     * @return  value to keep track of the next position in the array where a key should be placed
     */
    private int fillKeyArray(TreeMapNode<K, V> currentNode, K[] array, int currentIndex) {
        // base case: if currentNode is null
        if (currentNode == null) {
            // return currentIndex (nothing to do)
            return currentIndex;
        }

        // recursive case: going through left subtree, add keys to key array and update currentIndex
        currentIndex = fillKeyArray(currentNode.left, array, currentIndex);

        // another base case: put currentNode's key in key array and increase currentIndex
        array[currentIndex] = currentNode.key;
        currentIndex++;

        // recursive case: going through right subtree, add keys to key array from updated currentIndex
        // going through the right subtree is the final part of the in-order tree traversal algorithm, is returned
        return fillKeyArray(currentNode.right, array, currentIndex);
    }


    /**
     * A node in the Tree Map with a key/value pair
     *
     * @param <K>   keys stored in the node
     * @param <V>   values mapped to the keys
     */
    private static class TreeMapNode<K, V> {
        // instance variables
        /** Key of generic type K for this node */
        public K key;

        /** Data of a generic type V stored in this node */
        public V value;

        /** Left subtree */
        public TreeMapNode<K, V> left;

        /** Right subtree */
        public TreeMapNode<K, V> right;

        // constructors
        /**
         * Constructs a leaf node with given data
         *
         * @param key   key associated with the node
         * @param value value associated with the key and node
         */
        public TreeMapNode(K key, V value) {
            this(key, value, null, null);
        }

        /**
         * Constructs a node with the given data and links
         *
         * @param key   key associated with the node
         * @param value value associated with the key and node
         * @param left  left subtree
         * @param right right subtree
         */
        public TreeMapNode(K key, V value, TreeMapNode<K, V> left,
                           TreeMapNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
