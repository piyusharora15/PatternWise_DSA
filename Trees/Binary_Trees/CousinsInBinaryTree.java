// Problem Link: https://leetcode.com/problems/cousins-in-binary-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach 1: Iterative BFS.
// We perform a level-order traversal of the binary tree using a queue.For each node, we check if it matches either of the target values x or y.If we find a match, we record the parent and depth of that node.After processing all nodes at the current level, we check if both target nodes were found at the same depth with different parents.If so, they are cousins, and we return true.If only one of them was found, we return false as they cannot be cousins.If neither was found, we continue to the next level of the tree.

// Code:
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root == null) return false;
        Queue<Pair<TreeNode,TreeNode>> queue = new LinkedList<>();
        queue.add(new Pair<>(root,null));
        while(!queue.isEmpty()){
            int size = queue.size();
            TreeNode parentX = null,parentY = null;
            for(int i=0;i<size;i++){
                Pair<TreeNode,TreeNode> p = queue.poll();
                TreeNode node = p.getKey();
                TreeNode parent = p.getValue();
                if(node.val == x) parentX = parent;
                if(node.val == y) parentY = parent;
                if(node.left != null) queue.add(new Pair<>(node.left,node));
                if(node.right != null) queue.add(new Pair<>(node.right,node));
            }
            if(parentX != null && parentY != null){
                return parentX != parentY;
            }
            if(parentX != null || parentY != null){
                return false;
            }
        }
        return false;
    }
}

// Time Complexity: O(N), where N is the number of nodes in the binary tree. In the worst case, we may need to visit all nodes to determine if x and y are cousins.
// Space Complexity: O(W), where W is the maximum width of the binary tree. This space is used by the queue to store nodes at the current level during the BFS traversal. In the worst case, the width can be proportional to N/2 for a complete binary tree, leading to O(N) space complexity.

// Approach 2: Recursive DFS.
// We perform a depth-first search (DFS) traversal of the binary tree to find the target nodes x and y.For each node, we check if it matches either of the target values.If we find a match, we record the parent and depth of that node.After traversing the entire tree, we check if both target nodes were found at the same depth with different parents.If so, they are cousins, and we return true.Otherwise, we return false.

// Code:
class Solution {
    private int depthX = -1,depthY = -1;
    private TreeNode parentX = null,parentY = null;
    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root,null,0,x,y);
        return (depthX == depthY) && (parentX != parentY);
    }
    private void dfs(TreeNode node,TreeNode parent,int depth,int x,int y){
        if(node == null) return;
        if(node.val == x){
            parentX = parent;
            depthX = depth;
        }
        else if(node.val == y){
            parentY = parent;
            depthY = depth;
        }
        dfs(node.left,node,depth+1,x,y);
        dfs(node.right,node,depth+1,x,y);
    }
}

// Time Complexity: O(N), where N is the number of nodes in the binary tree. In the worst case, we may need to visit all nodes to determine if x and y are cousins.
// Space Complexity: O(H), where H is the height of the binary tree. This space is used by the recursion stack during the DFS traversal. In the worst case, for a skewed tree, the height can be O(N), leading to O(N) space complexity.