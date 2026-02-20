// Problem Link: https://leetcode.com/problems/merge-two-binary-trees?envType=problem-list-v2&envId=wo7dda2m

// Approach 1: Recursive.
// We can merge the two trees by traversing both trees simultaneously and adding the values of the nodes. If one of the nodes is null, we can simply return the other node. We can use a helper function to perform the merging process recursively.

// Code:
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        
        TreeNode merged = new TreeNode(root1.val + root2.val);
        merged.left = mergeTrees(root1.left, root2.left);
        merged.right = mergeTrees(root1.right, root2.right);
        
        return merged;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the larger tree. We visit each node once to merge the trees.
// Space Complexity: O(n), where n is the number of nodes in the larger tree.

// Approach 2: Iterative using a stack.
// We can also merge the two trees iteratively using a stack. We can push pairs of nodes from both trees onto the stack and process them until the stack is empty. For each pair of nodes, we can add their values and push their left and right children onto the stack for further processing.

// Code:
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        if (root1 == null) return root2;
        if (root2 == null) return root1;

        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{root1, root2});

        while (!stack.isEmpty()) {

            TreeNode[] pair = stack.pop();
            TreeNode node1 = pair[0];
            TreeNode node2 = pair[1];

            if (node1 == null || node2 == null) continue;

            // merge values
            node1.val += node2.val;

            // LEFT CHILD
            if (node1.left == null) {
                node1.left = node2.left;
            } else if (node2.left != null) {
                stack.push(new TreeNode[]{node1.left, node2.left});
            }

            // RIGHT CHILD
            if (node1.right == null) {
                node1.right = node2.right;
            } else if (node2.right != null) {
                stack.push(new TreeNode[]{node1.right, node2.right});
            }
        }

        return root1;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the larger tree. We visit each node once to merge the trees.
// Space Complexity: O(n), where n is the number of nodes in the larger tree.