public class AVLTreeInsert
{
    Node root;


    //****** Function to insert node in the AVL Tree
    static Node insertNode(int value,Node node)
    {

        //****** Normal insertion logic
        if(node == null)
        {
            return (new Node(value));
        }
        if(value > node.value)
        {
            node.rightChild = insertNode(value,node.rightChild);
            node.rightChild.parent = node;
        }
        else if (value < node.value)
        {
            node.leftChild = insertNode(value,node.leftChild);
            node.leftChild.parent = node;
        }
        else
        {
            return node;
        }

        //****** Update height of the node - will update height till the root node b'coz of recursive calls above
        node.height = 1 + Math.max(getNodeHeight(node.leftChild),getNodeHeight(node.rightChild));

        //****** Call the function to calculate height difference of the right & left subtrees
        int difference = getHeightDifference(node);

        if (difference > 1) // left subtree
        {
            if(value > node.leftChild.value)
            {
                // left right
                node.leftChild = rotateLeft(node.leftChild);
                return rotateRight(node);
            }
            if(value < node.leftChild.value)
            {
                //left left
                return rotateRight(node);
            }

        }
        if (difference < -1) // right subtree
        {
            if(value > node.rightChild.value)
            {
                // right right
                return rotateLeft(node);
            }
            if(value < node.rightChild.value)
            {
                // right left
                node.rightChild = rotateRight(node.rightChild);
                return rotateLeft(node);
            }
        }

        return node;  // returning the root node
    }

    void preOrder(Node node)
    {
        if (node != null) {

            System.out.print(node.value + " ");
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }

    static Node rotateRight(Node node)
    {
        Node T1 = node.leftChild;
        Node T2 = T1.rightChild; // right child(subtree) of temp
        //rotation logic :
        T1.rightChild = node;
        node.leftChild = T2;
        // change parents :
        T1.parent = node.parent;
        node.parent = T1;
        if(T2 != null)
        {
            T2.parent = node;
        }
        //re-calculate heights :
        node.height = Math.max(getNodeHeight(node.leftChild),getNodeHeight(node.rightChild)) + 1;
        T1.height = Math.max(getNodeHeight(T1.leftChild),getNodeHeight(T1.rightChild)) + 1;

        return T1; // return the new root node
    }

    static Node rotateLeft(Node node)
    {
        Node T1 = node.rightChild;
        Node T2 = T1.leftChild;
        //rotation logic :
        T1.leftChild = node;
        node.rightChild = T2;
        //change parents :
        T1.parent = node.parent;
        node.parent = T1;
        if(T2 != null)
        {
            T2.parent = node;
        }
        //re-calculate heights :
        node.height = Math.max(getNodeHeight(node.leftChild),getNodeHeight(node.rightChild)) + 1;
        T1.height = Math.max(getNodeHeight(T1.leftChild),getNodeHeight(T1.rightChild)) + 1;

        return T1; // return the new root node

    }

    //******* Function to return height of the node - 0 for leaf nodes - to avoid null pointer exception
    static int getNodeHeight(Node node)
    {
        if (node == null)
            return 0;

        return node.height;
    }

    //******* Function to return difference of height of the left & right sub-trees of the node
    static int getHeightDifference(Node node)
    {
        if(node == null)
        {
            return 0;
        }
        return getNodeHeight(node.leftChild) - getNodeHeight(node.rightChild);
    }

    static void printTreeHeight(Node node)
    {
        if(node == null)
        {
            System.out.println("********Reached the root node of the tree********");
            return;
        }
        System.out.println("      "+getNodeHeight(node.leftChild)+"  -   "+node.value+"  -    "+getNodeHeight(node.rightChild));
        printTreeHeight(node.parent);
    }

    static Node fetchNode(Node node,int value)
    {
        if(node == null)
        {
            return null;
        }
        if(value == node.value)
        {
            return node;
        }
        else if (value > node.value)
        {
            return fetchNode(node.rightChild,value);
        }
        else if (value < node.value)
        {
            return fetchNode(node.leftChild,value);
        }
        return null;
    }

    public static void main(String[] args)
    {
        AVLTreeInsert tree = new AVLTreeInsert();

        tree.root = insertNode(5,tree.root);
        tree.root = insertNode(2,tree.root);
        tree.root = insertNode(10,tree.root);
        tree.root = insertNode(15,tree.root);
        tree.root = insertNode(21,tree.root);
        tree.root = insertNode(56,tree.root);
        tree.root = insertNode(78,tree.root);
        tree.root = insertNode(11,tree.root);
        tree.root = insertNode(1,tree.root);
        tree.root = insertNode(64,tree.root);
        tree.root = insertNode(4,tree.root);
        tree.root = insertNode(22,tree.root);
        tree.root = insertNode(89,tree.root);
        tree.root = insertNode(18,tree.root);
        tree.root = insertNode(101,tree.root);
        tree.root = insertNode(45,tree.root);
        tree.root = insertNode(65,tree.root);
        tree.root = insertNode(34,tree.root);
        tree.root = insertNode(102,tree.root);
        Node last = fetchNode(tree.root,102);
        System.out.println("Left height - Node - Right height");
        printTreeHeight(last);
        System.out.println("\nPre-order traversal of the AVL Tree : ");
        tree.preOrder(tree.root);

    }

}
