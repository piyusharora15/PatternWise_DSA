// Problem Link: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach: We can use the divide and conquer approach to solve this problem. We can find the middle element of the array and make it the root of the BST. Then we can recursively do the same for the left half and right half of the array to construct the left and right subtrees respectively.We can use the following steps to solve this problem:
// 1. Find the middle element of the array and make it the root of the BST.
// 2. Recursively do the same for the left half of the array to construct the left subtree.
// 3. Recursively do the same for the right half of the array to construct the right subtree.

// Code:
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return constructBST(nums, 0, nums.length - 1);
    }

    private TreeNode constructBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = constructBST(nums, left, mid - 1);
        node.right = constructBST(nums, mid + 1, right);
        return node;
    }
}

// Time Complexity: O(n), where n is the number of elements in the input array. We visit each element once to construct the BST.
// Space Complexity: O(log n) on average, due to the recursive call stack. In the worst case (when the tree is skewed), it can go up to O(n).