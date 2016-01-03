package com.gaurav;

/**
 * Created by gauravwadhwa on 02/01/16.
 */
public class BSTAlgo {


    public static class Node {

        private int key;
        private Node parent;
        private Node left;
        private Node right;

        public int getKey() {
            return key;
        }

        public Node getParent() {
            return parent;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        @Override
        public String toString() {
            return "key: " + key;
        }

        public static String print(Node node, int space) {
            System.out.println();
            for (int i = 0; i < space; i++) {
                System.out.print("  ");
            }
            if (node == null) {
                System.out.print("null");
                return null;
            }
            System.out.print(node.getKey());
            print(node.left, space + 1);
            print(node.right, space + 1);
            return null;
        }

    }

    public static Node createBST(int arr[], int left, int right) {
        if (arr == null || arr.length == 0 || left > right) {
            return null;
        }
        int root = left + (right - left) / 2;
        Node node = new Node();
        node.key = arr[root];
        node.left = createBST(arr, left, root - 1);
        node.right = createBST(arr, root + 1, right);
        if (node.left != null) {
            node.left.parent = node;
        }
        if (node.right != null) {
            node.right.parent = node;
        }
        return node;
    }

    public static void printInOrder(Node node) {
        if (node == null) {
            return;
        }
        printInOrder(node.getLeft());
        System.out.print(node.getKey() + "   ");
        printInOrder(node.getRight());
    }


    public static void printPreOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getKey() + "   ");
        printPreOrder(node.getLeft());
        printPreOrder(node.getRight());
    }


    public static void printPostOrder(Node node) {
        if (node == null) {
            return;
        }
        printPostOrder(node.getLeft());
        printPostOrder(node.getRight());
        System.out.print(node.getKey() + "   ");
    }


    public static boolean isBST(Node node) {
        if (node == null) {
            return true;
        }
        if (node.left != null && node.left.key > node.key) {
            return false;
        }
        if (node.right != null && node.right.key < node.key) {
            return false;
        }
        return isBST(node.left) && isBST(node.right);
    }


    public static Node bstMax(Node node) {
        if (node == null) {
            return null;
        }
        Node right = node;
        while (right.right != null) {
            right = right.right;
        }
        return right;
    }

    public static Node bstMin(Node node) {
        if (node == null) {
            return null;
        }
        Node left = node;
        while (left.left != null) {
            left = left.left;
        }
        return left;
    }

    public static Node search(Node node, int value) {
        if (node == null) {
            return node;
        }
        if (node.key == value) {
            return node;
        }
        if (node.key > value) {
            return search(node.left, value);
        } else {
            return search(node.right, value);
        }
    }

    public static Node successor(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return bstMin(node.right);
        }
        // jab tak we have element as a right node
        // when found element as left break.
        Node parent = node.parent;
        while (parent != null && node == parent.right) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }


    public static Node predecessor(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            return bstMax(node.left);
        }
        Node parent = node.parent;
        while (parent != null && node == parent.left) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    /**
     * if value is equal goes to right side of the tree.
     *
     * @param node  can't be null.
     * @param value
     * @return
     */
    public static boolean insert(Node node, int value) {
        if (node == null) {
            return false;
        }
        Node newNode = new Node();
        newNode.key = value;
        Node parent = null;
        while (node != null) {
            parent = node;
            if (node.key > value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        newNode.parent = parent;
        if (parent.key > value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        return true;
    }


    /**
     * @param node Node to delet
     * @return
     */
    public static boolean delete(Node node) {
        if (node == null) {
            return false;
        }
        if (node.left == null) {
            transplant(node, node.right);
        } else if (node.right == null) {
            transplant(node, node.left);
        } else {
            Node successor = bstMin(node.right);
            if (node.right != successor) {
                transplant(successor, successor.right);
                successor.right = node.right;
                successor.right.parent = successor;
            }
            transplant(node, successor);
            successor.left = node.left;
            if (successor.left != null) {
                successor.left.parent = successor;
            }
        }
        return true;
    }


    /**
     * @param node1 de-attach node.
     * @param node2 replaced by this node.
     */
    public static void transplant(Node node1, Node node2) {
        System.out.println("\n trans: " + node1 + " - > " + node2);
        // remove link of node2 from parent.
        if (node2 != null && node2.parent != null) {
            if (node2.parent.left == node2) {
                node2.parent.left = null;
            } else {
                node2.parent.right = null;
            }
        }
        // replace and removing.
        Node parent = node1.parent;
        if (node2 != null) {
            node2.parent = parent;
        }
        if (parent != null) {
            if (parent.left == node1) {
                parent.left = node2;
            } else {
                parent.right = node2;
            }
        }
        node1.parent = null;
    }


}
