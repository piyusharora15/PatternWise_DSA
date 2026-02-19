// Problem Link: https://leetcode.com/problems/maximum-binary-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach 1: Recursion.
// We find the maximum element in the array and make it the root of the tree. Then we recursively build the left subtree using the elements to the left of the maximum element and the right subtree using the elements to the right of the maximum element.

// Code:
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }
    
    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        
        // Find the index of the maximum element in the current range
        int maxIndex = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        
        // Create a new TreeNode with the maximum element
        TreeNode node = new TreeNode(nums[maxIndex]);
        
        // Recursively build the left and right subtrees
        node.left = build(nums, left, maxIndex - 1);
        node.right = build(nums, maxIndex + 1, right);
        
        return node;
    }
}

// Time Complexity: O(n^2) in the worst case (when the array is sorted in ascending or descending order), O(n log n) on average.
// Space Complexity: O(n) in the worst case (when the tree is skewed), O(log n) on average (when the tree is balanced).

// Approach 2: Using a Stack.
// We can use a stack to build the tree iteratively. We iterate through the array and for each element, we pop elements from the stack until we find an element greater than the current element. The last popped element will be the left child of the current element, and the current element will be the right child of the last popped element. We then push the current element onto the stack. Finally, the bottom element of the stack will be the root of the tree.

// Code:
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Stack<TreeNode> stack = new Stack<>();
        
        for (int num : nums) {
            TreeNode current = new TreeNode(num);
            
            while (!stack.isEmpty() && stack.peek().val < num) {
                current.left = stack.pop();
            }
            
            if (!stack.isEmpty()) {
                stack.peek().right = current;
            }
            
            stack.push(current);
        }
        
        // The root of the tree will be the bottom element of the stack
        while (stack.size() > 1) {
            stack.pop();
        }
        
        return stack.peek();
    }
}
// Time Complexity: O(n) because each element is pushed and popped from the stack at most once.
// Space Complexity: O(n) in the worst case (when the tree is skewed), O(log n) on average (when the tree is balanced).