// Problem Link: https://leetcode.com/problems/delete-node-in-a-bst?envType=problem-list-v2&envId=wo7dda2m

// Approach: Recursive 
// We will first find the node to be deleted and then we will check for the 3 cases:
// 1. If the node to be deleted is a leaf node, we can simply remove it.
// 2. If the node to be deleted has only one child, we can replace it with its child.
// 3. If the node to be deleted has two children, we can replace it with its in-order successor (the smallest node in its right subtree) and then delete the in-order successor.

// Code:
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(key < root.val){
            root.left = deleteNode(root.left,key);
        }
        else if(key > root.val){
            root.right = deleteNode(root.right,key);
        }
        else{
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;
            TreeNode successor = findMin(root.right);
            root.val = successor.val;
            root.right = deleteNode(root.right,successor.val);
        }
        return root;
    }
    private TreeNode findMin(TreeNode node){
        while(node.left != null) node = node.left;
        return node;
    }
}

// Time Complexity: O(h) where h is the height of the tree.
// Space Complexity: O(h) due to recursive stack space.