// Problem Link: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal?envType=problem-list-v2&envId=tree

// Approach: Recursive Mapping with HashMap.
// We can use the property of preorder and inorder traversals to construct the binary tree. The first element of the preorder traversal is the root of the tree. We can find this root in the inorder traversal to determine the left and right subtrees. We can then recursively construct the left and right subtrees using the corresponding segments of the preorder and inorder arrays.We can optimize the search for the root in the inorder array by using a HashMap to store the indices of the values in the inorder array, allowing us to find the index in O(1) time.

// Code:
class Solution {
    int index = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return helper(preorder,0,inorder.length-1,map);
    }
    private TreeNode helper(int[] preorder, int start, int end, Map<Integer,Integer> map){
        if(start > end) return null;
        int rootVal = preorder[index++];
        TreeNode node = new TreeNode(rootVal);
        int inorderIndex = map.get(rootVal);
        node.left = helper(preorder,start,inorderIndex-1,map);
        node.right = helper(preorder,inorderIndex+1,end,map);
        return node;
    }
}

// Time Complexity: O(n) - We visit each node once to construct the tree.
// Space Complexity: O(n) - The space used by the map and the recursive call stack.