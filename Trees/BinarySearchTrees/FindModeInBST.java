// Problem Link: https://leetcode.com/problems/find-mode-in-binary-search-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach: Inorder Traversal of BST gives us sorted order of elements. We can keep track of the count of current element and update the mode list accordingly.We can also keep track of the maximum count to avoid unnecessary comparisons.We can use a helper function to perform the inorder traversal and update the mode list.We can also use a variable to keep track of the previous element to compare with the current element and update the count accordingly.

// Code:
class Solution {
    List<Integer> modes;
    int maxCount;
    int count;
    Integer prev;

    public int[] findMode(TreeNode root) {
        modes = new ArrayList<>();
        maxCount = 0;
        count = 0;
        prev = null;

        inorder(root);

        // Convert List to array
        int[] result = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            result[i] = modes.get(i);
        }
        return result;
    }

    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        // Traverse left subtree
        inorder(node.left);

        // Update count and mode list
        if (prev != null && node.val == prev) {
            count++;
        } else {
            count = 1; // Reset count for new value
        }

        if (count > maxCount) {
            maxCount = count;
            modes.clear(); // Clear previous modes
            modes.add(node.val); // Add current value as mode
        } else if (count == maxCount) {
            modes.add(node.val); // Add current value to mode list
        }

        prev = node.val; // Update previous value

        // Traverse right subtree
        inorder(node.right);
    }
}

// Time Complexity: O(n) where n is the number of nodes in the BST. We need to traverse all nodes to find the mode.
// Space Complexity: O(m) where m is the number of modes. In the worst case, all values in the BST could be the same, resulting in m = n. Therefore, the space complexity can be O(n) in the worst case.