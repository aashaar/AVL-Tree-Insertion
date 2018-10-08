public class Node
{
    int value;
    Node leftChild ;
    Node rightChild;
    Node parent ;
    int height;

    Node(int val)
    {
        value = val;
        height = 1;
    }

}
