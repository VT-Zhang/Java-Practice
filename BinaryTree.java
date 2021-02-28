package com.jianzhang.BinaryTree;

import java.util.*;

// Given int[] {1, 2, 3, 4, 5, 6, 7}
// To construct the following binary tree
//       1
//      / \
//     2   3
//    / \ / \
//   4  5 6  7
// Or given the same binary tree
// Deconstruct the tree back to the int[] {1, 2, 3, 4, 5, 6, 7}

/**
 * Created by Jian Zhang 02/28/2021
 */
public class BinaryTree {

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    TreeNode root;

    /**
     * Function to construct the Binary Tree pre-order according to the order of the input int array.
     */
    public TreeNode constructTree(TreeNode root, int[] inputArray, int pointer) {
        if (pointer < inputArray.length) {
            root = new TreeNode(inputArray[pointer]);
            root.left = constructTree(root.left, inputArray, pointer * 2 + 1);
            root.right = constructTree(root.right, inputArray, pointer * 2 + 2);
        }
        return root;
    }

    /**
     * Function to deconstruct a Binary Tree pre-order to an int array.
     */
    public int[] deConstructTree(TreeNode root, int[] outputArray, int pointer) {
        if (root == null) {
            return outputArray;
        }
        outputArray[pointer] = root.value;
        deConstructTree(root.left, outputArray, pointer * 2 + 1);
        deConstructTree(root.right, outputArray, pointer * 2 + 2);
        return outputArray;
    }

    /**
     * Helper function to return binary tree by each level in a list.
     * This is the problem #57 from laicode
     * https://app.laicode.io/app/problem/57
     */
    public static List<List<Integer>> binaryTreeByLayer(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> curLayer = new ArrayList<Integer>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                curLayer.add(cur.value);
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            list.add(curLayer);
        }
        return list;
    }

    /**
     * Main function to run test cases;
     */
    public static void main(String[] args) {
        // setup the tree and the int array
        BinaryTree tree = new BinaryTree();
        int[] array = {1, 2, 3, 4, 5, 6, 7};

        // call construct the binary tree function
        tree.root = tree.constructTree(tree.root, array, 0);

        // print out the result as the layers of the tree.
        System.out.println("The constructed binary tree from the int array is " +
                binaryTreeByLayer(tree.root).toString());

        // call the deconstruct method to print out the result as the int array
        System.out.println("The deconstructed binary tree to the int array is "
                + Arrays.toString(tree.deConstructTree(tree.root, new int[7], 0)));
    }

    // The print out from the console should be as following:
    // The constructed binary tree from the int array is [[1], [2, 3], [4, 5, 6, 7]]
    // The deconstructed binary tree to the int array is [1, 2, 3, 4, 5, 6, 7]
}
