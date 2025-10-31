public class SplayTree
{
    SplayNode root;

    SplayTree()
    {
        root = null;
    }

    public SplayNode Splay_insert(int x, SplayNode t) //insert routine
    {
        if (t == null)
        {
            return new SplayNode(x); // creating a new node if the tree is empty
        }

        if (x < t.element)
        {
            t.left = Splay_insert(x, t.left); // inserting into the left subtree
        }
        else if (x > t.element)
        {
            t.right = Splay_insert(x, t.right); // inserting into the right subtree
        }
        else
        {
            ; // Duplicates are not allowed, do nothing
        }

        return splay(t);
    }

    
    public SplayNode Splay_contains(int x, SplayNode t) // searching for a value in the tree
    {
        if (t == null)
        {
            return null; // returning null if the node is not found or empty tree
        }

        if (x < t.element)
        {
            return Splay_contains(x, t.left); // searching in the left subtree
        }
        else if (x > t.element)
        {
            return Splay_contains(x, t.right); // searching in the right subtree
        }
        else
        {
            return splay(t); // return the node if the value is found after you splay
        }
    }

    public SplayNode Splay_delete(int x, SplayNode t) // deleting anode routine. Parameters: the element val and the current node we're at
    {
        if (t == null)
        {
            return t;// if t == null or tree is empty
        }

        t = Splay_contains(x, t); //calling contains to check if element is there in the tree

        if (t == null || t.element != x) // if empty tree or empty
        {
            return t;
        }

        int compareResult = x - t.element; // comparing value of element inside the new node to the value of element inside current node
        if (t.left == null)
        {
            t = t.right;
        }
        else
        {
            SplayNode leftSubtree = t.left;
            leftSubtree = SplayMax(leftSubtree); // bring max of left subtree to the root root
            leftSubtree.right = t.right; // attaching the original right subtree to the new root
            t = leftSubtree;
        }

        return t;
    }

    public SplayNode splay(SplayNode s1) //splaying node to the root
    {
        while (s1.parent != null) //while s1 is not at the root
        {
            if (s1.parent.parent == null) // condition for zig
            {
                if (s1.parent.left == s1)
                {
                    s1 = r_zig(s1);
                }
                else
                {
                    s1 = l_zig(s1);
                }
            }
            else if ((s1.parent.parent.left == s1.parent && s1.parent.left == s1) || (s1.parent.parent.right == s1.parent && s1.parent.right == s1)) //conditions for zigzigs
            {
                if (s1.parent.left == s1)
                {
                    s1 = r_rots_zigzig(s1);
                }
                else
                {
                    s1 = l_rots_zigzig(s1);
                }
            }
            else //else we go for zigzags
            {
                if (s1.parent.left == s1)
                {
                    s1 = rl_rots_zigzag(s1);
                }
                else
                {
                    s1 = lr_rots_zigzag(s1);
                }
            }
        }

        return s1;
    }

    public SplayNode l_zig(SplayNode s1) // right rotation with parent if there is no grandparent for s1
    {
        SplayNode s2 = s1.parent;

        s2.right = s1.left;

        if (s1.left != null)
        {
            s1.left.parent = s2;
        }

        s2.parent = s1;

        s1.left = s2;

        s1.parent = null;

        return s1;
    }

    public SplayNode r_zig(SplayNode s1)// left rotation with parent if there is no grandparent for s1
    {
        SplayNode s2 = s1.parent;

        s2.left = s1.right;

        if (s1.right != null)
        {
            s1.right.parent = s2;
        }

        s2.parent = s1;

        s1.right = s2;

        s1.parent = null;

        return s1;
    }

    public SplayNode l_rots_zigzig(SplayNode s1) // 2 left rotations when node, parent and grandparent are alligned totogether on the right
    {
        SplayNode s2 = s1.parent; //parent
        SplayNode s3 = s2.parent; //grandparent
        SplayNode s4 = s3.parent; // parent of grandparent
        
        // left rotating grandparent and parent using pointer reassignment

        if (s2 == null || s3 == null)
        {
            return s1;
        }

        s3.right = s2.left;
        if (s2.left != null)
        {
            s2.left.parent = s3;
        }

        s2.left = s3;
        s3.parent = s2;
        
        // left rotating parent and node using pointer reassignment

        s2.right = s1.left;
        if (s1.left != null)
        {
            s1.left.parent = s2;
        }

        s1.left = s2;
        s2.parent = s1;
        
        //attching node to the parent of its grandparent if not null

        if (s4 != null)
        {
            s1.parent = s4;
            if (s4.left == s3)
            {
                s4.left = s1;
            }
            else
            {
                s4.right = s1;
            }
        }
        else
        {
            s1.parent = null;
        }

        return s1;
    }

    public SplayNode r_rots_zigzig(SplayNode s1) // 2 right rotations when node, parent and grandparent are alligned totogether on the left
    {
        SplayNode s2 = s1.parent;
        SplayNode s3 = s2.parent;
        SplayNode s4 = s3.parent;
        
        // right rotating grandparent and parent using pointer reassignment

        if (s2 == null || s3 == null)
        {
            return s1;
        }

        s3.left = s2.right;

        if (s2.right != null)
        {
            s2.right.parent = s3;
        }

        s2.right = s3;
        s3.parent = s2;
        
        // right rotating parent and node using pointer reassignment

        s2.left = s1.right;

        if (s1.right != null)
        {
            s1.right.parent = s2;
        }

        s1.right = s2;
        s2.parent = s1;
        
        //attching node to the parent of its grandparent if not null

        if (s4 != null)
        {
            s1.parent = s4;

            if (s4.left == s3)
            {
                s4.left = s1;
            }
            else
            {
                s4.right = s1;
            }
        }
        else
        {
            s1.parent = null;
        }

        return s1;
    }

    private SplayNode SplayMax(SplayNode t) //splaying max of a subtree to the root
    {
        if (t == null)
        {
            return null; // return null if the tree is empty
        }

        while (t.right != null)
        {
            t = t.right;
        }

        return Splay_contains(t.element, t); // splay t to the root
    }

    public SplayNode lr_rots_zigzag(SplayNode s1) // 2 opposite rotations when node, parent and grandparent are not alligned totogether
    {
    
        SplayNode s2 = s1.parent;
        SplayNode s3 = s2.parent;
        SplayNode s4 = s3.parent;
        
         // left rotation on node and parent

        if (s2 == null || s3 == null)
        {
            return s1;
        }

        s1.parent = s3;
        s2.parent = s1;

        s2.right = s1.left;  
        if (s1.left != null)
        {
            s1.left.parent = s2;
        }

        s1.left = s2;  

        // right rotation on node and grandparent
        s3.left = s1.right;
        if (s1.right != null)
        {
            s1.right.parent = s3;
        }

        s1.right = s3;
        s3.parent = s1;

        //attching node to the parent of its grandparent if not null
        if (s4 != null)
        {
            s1.parent = s4;
            if (s4.left == s3)
            {
                s4.left = s1;
            }
            else
            {
                s4.right = s1;
            }
        }
        else
        {
            s1.parent = null;
        }

        return s1;
    }

    public SplayNode rl_rots_zigzag(SplayNode s1) // 2 opposite rotations when node, parent and grandparent are not alligned together
    {
        SplayNode s2 = s1.parent;
        SplayNode s3 = s2.parent;
        SplayNode s4 = s3.parent;

        if (s2 == null || s3 == null)
        {
            return s1;
        }

         // right rotation on parent and node
        s1.parent = s3;
        s2.parent = s1;

        s2.left = s1.right;  
        if (s1.right != null)
        {
            s1.right.parent = s2;
        }

        s1.right = s2;  

         // left rotation on node and grandparent
        s3.right = s1.left;
        if (s1.left != null)
        {
            s1.left.parent = s3;
        }

        s1.left = s3;
        s3.parent = s1;

        // reattach node to the parent of its grandparent
        if (s4 != null)
        {
            s1.parent = s4;
            if (s4.left == s3)
            {
                s4.left = s1;
            }
            else
            {
                s4.right = s1;
            }
        }
        else
        {
            s1.parent = null;
        }

        return s1;
    }


    
    public class SplayNode
    {
        int element; // Value of the node
        SplayNode left; // Left child
        SplayNode right; // Right child
        SplayNode parent; // Parent of the node

    // Constructor for initializing a node
    
        public SplayNode()
        {
            this(0, null, null,null);
        }
    
        public SplayNode(int element)
        {
            this(element, null, null,null); 
        }

        // Constructor for initializing a node with left and right children
        public SplayNode(int element, SplayNode left, SplayNode right, SplayNode parent)
        {
            this.element = element; // Assigns the value to the node
            this.left = left; // Assigns left child
            this.right = right; // Assigns right child
            this.parent = parent; // Initializes the parent 
        }
    }
}
