public class AvlTree
{
    public AvlNode root; // Root of the AVL tree

    // Constructor for the AVL tree
    public AvlTree()
    {
        root = null; // Initializes root to null
    }

   
    public AvlNode Avl_insert(int x, AvlNode t)
    {
        if (t == null)
        {
            return new AvlNode(x, null, null); // Create a new node if the tree is empty
        }

        if (x < t.element)
        {
            t.left = Avl_insert(x, t.left); // Insert into the left subtree
        }
        else if (x > t.element)
        {
            t.right = Avl_insert(x, t.right); // Insert into the right subtree
        }
        else
        {
            ; // Duplicates are not allowed, do nothing
        }

        // Balance the tree after insertion
        return Avl_balance(t); // Rebalance the tree
    }

    // Method to check if the tree contains a value
    public AvlNode Avl_contains(int x, AvlNode t)
    {
        if (t == null)
        {
            return null; // Return null if the node is not found
        }

        if (x < t.element)
        {
            return Avl_contains(x, t.left); // Search in the left subtree
        }
        else if (x > t.element)
        {
            return Avl_contains(x, t.right); // Search in the right subtree
        }
        else
        {
            return t; // Return the node if the value is found
        }
    }
    
    public AvlNode Avl_delete(int x, AvlNode t) //inserting and Avl node routine. Parameters: the element val and the current node we're at
    {
        if (t == null) 
        {
            return t; 
        }

        int compareResult = x - t.element; // comparing value of element inside the new node to the value of element inside current node
        if (compareResult < 0) //if the new node's element is less than the current nodes element
        {
            t.left = Avl_delete(x, t.left); // recursively apply the remove function on the left child of current node
        }
        else if (compareResult > 0) //if the new node's element is greater than the current nodes element
        {
            t.right = Avl_delete(x, t.right); // recursively apply the remove function on the right child of current node
        }
        else if (t.left != null && t.right != null) //t has 2 children
        {
            t.element = findMin(t.right).element; // finding the minimum value of the right subtree and replacing it with t (in order successor replacement) 
            t.right = Avl_delete(t.element, t.right); //removing the smallest element on the right subtree
        }
        else
        {
            t = (t.left != null) ? t.left : t.right; // either replace with the left or right child, whichever is non-null
        }

        return Avl_balance(t); // balancing the tree to preserve the invariant
    }

    // Method to balance the AVL tree
    private AvlNode Avl_balance(AvlNode t)
    {
        if (t == null)
        {
            return t; // Return if the node is null
        }

        // Check if the left subtree is heavier than the right
        if (Avl_height(t.left) - Avl_height(t.right) > 1)
        {
            if (Avl_height(t.left.left) >= Avl_height(t.left.right))
            {
                t = rotateWithLeftChild(t); // Perform a single rotation
            }
            else
            {
                t = doubleWithLeftChild(t); // Perform a double rotation
            }
        }
        // Check if the right subtree is heavier than the left
        else if (Avl_height(t.right) - Avl_height(t.left) > 1)
        {
            if (Avl_height(t.right.right) >= Avl_height(t.right.left))
            {
                t = rotateWithRightChild(t); // Perform a single rotation
            }
            else
            {
                t = doubleWithRightChild(t); // Perform a double rotation
            }
        }

        t.height = Math.max(Avl_height(t.left), Avl_height(t.right)) + 1; // Update the height
        return t; // Return the balanced node
    }

    // Helper method to get the height of a node
    private int Avl_height(AvlNode t)
    {
        return t == null ? -1 : t.height; // Return -1 for null nodes, else return height
    }

    // Helper method for rotating with the left child
    private AvlNode rotateWithLeftChild(AvlNode k2)
    {
        AvlNode k1 = k2.left; // Set k1 to the left child of k2
        k2.left = k1.right; // Move k1's right child to k2's left
        k1.right = k2; // Set k2 as the right child of k1
        k2.height = Math.max(Avl_height(k2.left), Avl_height(k2.right)) + 1; // Update k2's height
        k1.height = Math.max(Avl_height(k1.left), Avl_height(k1.right)) + 1; // Update k1's height
        return k1; // Return k1 as the new root
    }

    // Helper method for performing a double rotation with the left child
    private AvlNode doubleWithLeftChild(AvlNode k3)
    {
        k3.left = rotateWithRightChild(k3.left); // Perform a right rotation on k3's left child
        return rotateWithLeftChild(k3); // Perform a left rotation on k3
    }

    // Helper method for rotating with the right child
    private AvlNode rotateWithRightChild(AvlNode k6)
    {
        AvlNode k7 = k6.right; // Set k7 to the right child of k6
        k6.right = k7.left; // Move k7's left child to k6's right
        k7.left = k6; // Set k6 as the left child of k7
        k6.height = Math.max(Avl_height(k6.left), Avl_height(k6.right)) + 1; // Update k6's height
        k7.height = Math.max(Avl_height(k7.left), Avl_height(k7.right)) + 1; // Update k7's height
        return k7; // Return k7 as the new root
    }

    // Helper method for performing a double rotation with the right child
    private AvlNode doubleWithRightChild(AvlNode k7)
    {
        k7.right = rotateWithLeftChild(k7.right); // Perform a left rotation on k7's right child
        return rotateWithRightChild(k7); // Perform a right rotation on k7
    }
    
    private AvlNode findMin(AvlNode t)
    {
        if (t == null) 
        {
            return null; // Return null if the tree is empty
        }
    
        if (t.left == null) 
        {
            return t; // The minimum is the leftmost node
        }

        return findMin(t.left); // Recursively find the minimum in the left subtree
    }

    // AVL Node class for Integer type
    public class AvlNode
    {
        int element; // Value of the node
        AvlNode left; // Left child
        AvlNode right; // Right child
        int height; // Height of the node

    // Constructor for initializing a node
    
        public AvlNode()
        {
            this(0, null, null);
        }
    
        public AvlNode(int element)
        {
            this(element, null, null); // Calls the second constructor
        }

        // Constructor for initializing a node with left and right children
        public AvlNode(int element, AvlNode left, AvlNode right)
        {
            this.element = element; // Assigns the value to the node
            this.left = left; // Assigns left child
            this.right = right; // Assigns right child
            this.height = 0; // Initializes the height as 0
        }
    }
}










