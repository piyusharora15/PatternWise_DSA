// Problem Link: https://leetcode.com/problems/two-sum-iv-input-is-a-bst?envType=problem-list-v2&envId=wo7dda2m

// Approach 1: Using HashSet.
// We can use a HashSet to store the values of the nodes we have visited. For each node, we check if the complement (target - current node's value) exists in the HashSet. If it does, we return true. If not, we add the current node's value to the HashSet and continue traversing the tree.We can perform a depth-first search (DFS) or breadth-first search (BFS) to traverse the tree.

// Code:
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }
    
    private boolean dfs(TreeNode node, Set<Integer> set, int k) {
        if (node == null) {
            return false;
        }
        
        if (set.contains(k - node.val)) {
            return true;
        }
        
        set.add(node.val);
        
        return dfs(node.left, set, k) || dfs(node.right, set, k);
    }
}

// Time Complexity: O(n), where n is the number of nodes in the tree, since we visit each node once.
// Space Complexity: O(n), in the worst case, the HashSet can contain all the node values, and the recursion stack can also go as deep as the height of the tree, which can be O(n) in the worst case (for a skewed tree).

// Approach 2: Using Inorder Traversal and Two Pointers.
// We can perform an inorder traversal of the BST to get a sorted list of the node values. Then, we can use the two-pointer technique to find if there are two numbers in the sorted list that add up to the target k.We can initialize two pointers, one at the beginning of the list and one at the end. We can calculate the sum of the values at these two pointers. If the sum is equal to k, we return true. If the sum is less than k, we move the left pointer to the right to increase the sum. If the sum is greater than k, we move the right pointer to the left to decrease the sum. We continue this process until the pointers meet.

// Code:
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        
        int left = 0;
        int right = list.size() - 1;
        
        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
        
        return false;
    }
    
    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
}

// Time Complexity: O(n), where n is the number of nodes in the tree, since we visit each node once during the inorder traversal and then we use two pointers to find the target sum.
// Space Complexity: O(n), in the worst case, the list can contain all the node values, and the recursion stack can also go as deep as the height of the tree, which can be O(n) in the worst case (for a skewed tree).