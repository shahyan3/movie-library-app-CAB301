// Let's create a Binary Tree data structure
public class BinarySearchTree implements StringParser {

    Node root;

        @Override
    // Converts a string value and parses it to its ACSII double equivelent
    public int parseStringToASCIIValue(String buffer) {
        int keyID = -1;
        for(int i=0; i<buffer.length(); i++)
        {
            keyID += (int)buffer.charAt(i);
        }
//        System.out.println("Movie id: "+ keyID);

        return keyID;
    }

    public boolean remove(String key) {
        Node focusNode = root;
        Node parent = root;

        boolean isItALeftChild = true;

        while(focusNode.key != key) {
            parent = focusNode;

            // key < focusNode.key
            if(focusNode.alphabeticallyWeight(key, focusNode.key) == -1) {  // doesn't work #TODO
                isItALeftChild = true;

                focusNode = focusNode.leftChild;
            } else {
                isItALeftChild = false;
                focusNode = focusNode.rightChild;
            }

            if(focusNode == null)
                return  false;
        }

        // deleting part.
        // at this point the node doesn't have children.
        // now we're in the situation where we're going to delete these guys
        if(focusNode.leftChild == null && focusNode.rightChild == null) {
            if(focusNode == null) {
                root = null;
            } else if(isItALeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
        }
        // in a situation where there is no right child
        else if(focusNode.rightChild == null) {
            if(focusNode == root) {
                root = focusNode.leftChild;
            } else if(isItALeftChild) {
                parent.leftChild = focusNode.leftChild;
            } else {
                parent.rightChild = focusNode.leftChild;
            }
        }

        else if(focusNode.leftChild == null) {
            if(focusNode == root) {
                root = focusNode.rightChild;
            } else if(isItALeftChild) {
                parent.leftChild = focusNode.rightChild;
            } else {
                parent.rightChild = focusNode.leftChild;
            }
        }

        else { // two children are involved
            Node replacement = getReplacementNode(focusNode);

            if(focusNode == root) {
                root = replacement;
            } else if(isItALeftChild) {
                parent.leftChild = replacement;
            } else
                parent.rightChild = replacement;

            replacement.leftChild = focusNode.leftChild;
        }
        return true;
    }

    public Node getReplacementNode(Node replacedNode) {
        Node replacementParent = replacedNode;
        Node replacement = replacedNode;

        Node focusNode = replacedNode.rightChild;

        while (focusNode != null) {
            replacementParent = replacement;
            replacement = focusNode;

            focusNode = focusNode.leftChild;
        }

        if(replacement != replacedNode.rightChild) {
            replacementParent.leftChild = replacement.rightChild;
            replacement.rightChild = replacedNode.rightChild;
        }

        return replacement;
    }

    // returns true if movie added to tree
    public void addNode(String key, Movie movie) {
        Node newNode = new Node(key, movie);

        if (root == null) { // first time
            root = newNode;
            return;
        } else {
            // root node already created. Test for children nodes (lhs and rhs childs)
            Node focusNode = root;

            Node parent;

            while (true) {
                parent = focusNode; // save the root as parent and play with node in "focus" i.e. focusNode

//                if( key < focusNode.key) { // LEFT child node test in bst: is the root's key is less than given node's key add to left child of root/parent node in tree                    focusNode =  focusNode.leftChild;
                if (newNode.alphabeticallyWeight(newNode.key, focusNode.key) == -1) { // LEFT child node test in bst: is the root's key is less than given node's key add to left child of root/parent node in tree                    focusNode =  focusNode.leftChild;

                    focusNode = focusNode.leftChild; // focus node is now "left child node"

                    if (focusNode == null) { // if left child node of root or parent in null add newNode to left child
                        parent.leftChild = newNode;
                        return;

                    }
                } else { // RIGHT child node test in bst: the newNode's key is greater than parent node, add to the right child of parent (as is in bst structure)
                    focusNode = focusNode.rightChild; // focus on the right child of root/parent node

                    if (focusNode == null) { // no nodes in the right child of root/parent add newNode then
                        parent.rightChild = newNode;
                        return;
                    }

                }
            }
        }
    }

    // find node
    public Node findNode(String key) {
        Node focusNode = root;

        while(focusNode.alphabeticallyWeight(focusNode.key, key) != 2) { // its not roots key they want
            if(focusNode.alphabeticallyWeight(key, focusNode.key) == -1) { // shift the focusNode to the left child -> (left subtree has keys less than parents key value)
//                if(key < focusNode.key) { // shift the focusNode to the left child -> (left subtree has keys less than parents key value)
                focusNode = focusNode.leftChild;
            } else { // shift the focusNode to the right child -> (right subtree has keys greater than parents key value)
                focusNode = focusNode.rightChild;
            }

            if(focusNode == null) // no nodes including root exists i.e. no bst tree
                return null;
        }

        return focusNode;
    }

    // DFS - in order variant traversal
    // rules - traverse left subtree where the nodes are the smallest values of parent, and then move to right subtree
    // result - in order traversal the smallest key node value is printed first in asc
    public void inOrderTraverseTree(Node focusNode) {
        if(focusNode != null) { // recursively traverse left child nodes first than right
            inOrderTraverseTree(focusNode.leftChild);

//            System.out.println("\n\n--Node: " + focusNode.toString());  // print recursively inorder node (or return!)
            System.out.println(" ");
            System.out.println("Title: " + focusNode.movie.getTitle());
            System.out.println("Starring: " + focusNode.movie.getStarring());
            System.out.println("Director: " + focusNode.movie.getDirector());
            System.out.println("Genre: " + focusNode.movie.getGenre());
            System.out.println("Classification: " + focusNode.movie.getClassification());
            System.out.println("Duration: " + focusNode.movie.getDuration());
            System.out.println("Release Date: " + focusNode.movie.getReleaseDate());
            System.out.println("Copies Available: " + focusNode.movie.getCopiesAvailable());
            System.out.println("Times Rented: " + focusNode.movie.getTimesRented());

            inOrderTraverseTree(focusNode.rightChild);

        }
    }


    // DFS - pre order variant traversal
    public void preOrderTraverseTree(Node focusNode) {
        if (focusNode != null) { // recursively traverse left child nodes first than right

            System.out.println(focusNode);  // print recursively pre-order traversal (or return!)

            preOrderTraverseTree(focusNode.leftChild);

            preOrderTraverseTree(focusNode.rightChild);

        }
    }

    // DFS - pre order variant traversal
    // ??
    public void postOrderTraverseTree(Node focusNode) {
        if (focusNode != null) { // recursively traverse left child nodes first than right

            postOrderTraverseTree(focusNode.leftChild);
            postOrderTraverseTree(focusNode.rightChild);

            System.out.println(focusNode);  // print recursively pre-order traversal (or return!)

        }
    }

    public Node getRoot() {
         return this.root ;
    }

    // traverse BST in order and return an array of movies (unsorted by times rented)
    public Movie[] returnBSTAsArray(int totalMovies) {
        Movie[] list = new Movie[totalMovies];
        int index = 0;
        storeInOrder(this.getRoot(), list, index);

        return list;
    }

    /**
     * insert the elements of the bst (denoted by root) into array,
     * starting at index (the first available position in array) and
     * returns the first available position after insertion)
     * pre-conditions :
     *    - root is a bst containing n elements
     *    - array contains enough available cells
     *    - index = i0
     * post-conditions
     *    - tree is unchanged
     *    - array[i0..i0+n-1] contains elements of the bst
     *    - functions returns i0+n
     */
    private int storeInOrder(Node root, Movie[] array, int index) {
        if (root == null)
            return index;
        // then call on left, add root, call on right...
        int i = storeInOrder(root.leftChild, array, index);
        array[i] = root.movie;
        return storeInOrder(root.rightChild, array, i+1);
    }
}
class Node {
//    public int key;
    public  String key;
    public  Movie movie;

    // max children nodes a node have have 2
    Node leftChild;
    Node rightChild;

    Node(String key, Movie movie) {
        this.key = key;
        this.movie = movie;
    }

    public String toString() {
        return movie.getTitle() + " has a key " + key;
    }

    public int alphabeticallyWeight(String s1, String s2){
//        System.out.println("Comparing \"" + s1 + "\" to \"" + s2 + "\"...");

        int comparisonResult = s1.compareTo(s2);
//        System.out.println("The result of the comparison was " + comparisonResult);

//        System.out.print("This means that \"" + s1 + "\" ");
        if(comparisonResult < 0){
//            System.out.println("lexicographically comes (before) \"" + s2 + "\".");
            return -1;
        }else if(comparisonResult > 0){
//            System.out.println("lexicographically comes (after) \"" + s2 + "\".");
            return 1;
        }else{
//            System.out.println("equals \"" + s2 + "\".");
            return 2;
        }
    }

}


