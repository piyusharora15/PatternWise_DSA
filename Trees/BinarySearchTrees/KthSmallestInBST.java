// Problem Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst?envType=problem-list-v2&envId=wo7dda2m

// Approach 1: Recursive Inorder Traversal.
// We can perform an inorder traversal of the BST, which visits nodes in ascending order.We store the values in a list and return the k-1 index value.

// Code:
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root,list);
        return list.get(k-1);
    }
    private void inorder(TreeNode node,List<Integer> list){
        if(node == null) return;
        inorder(node.left,list);
        list.add(node.val);
        inorder(node.right,list);
    }
}

// Time Complexity: O(N) in the worst case, where N is the number of nodes in the BST.
// Space Complexity: O(N) for storing the values in the list.

// Approach 2: Optimized Recursive Inorder Traversal.
// We can optimize the previous approach by keeping track of the count of nodes visited during the inorder traversal. Once we reach the k-th node, we can return its value immediately without storing all values.

// Code:
class Solution {
    private int count = 0;
    private int ans = -1;
    public int kthSmallest(TreeNode root, int k) {
        inorder(root,k);
        return ans;
    }
    private void inorder(TreeNode node,int k){
        if(node == null) return;
        inorder(node.left,k);
        if(ans != -1) return;
        if(++count == k){
            ans = node.val;
            return;
        }
        inorder(node.right,k);
    }
}

// Time Complexity: O(H + k) where H is the height of the tree. In the worst case, we may have to traverse H nodes to reach the leftmost node and then k nodes to find the k-th smallest.
// Space Complexity: O(H) for the recursion stack, where H is the height of the tree.