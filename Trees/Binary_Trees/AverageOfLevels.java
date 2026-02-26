// Problem Link: https://leetcode.com/problems/average-of-levels-in-binary-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach 1: BFS(Level Order Traversal).
// We can use a queue to perform a level order traversal of the binary tree. For each level, we can calculate the sum of the node values and count the number of nodes at that level. Finally, we can compute the average for each level and store it in a list.We can repeat this process until we have traversed all levels of the tree.

// Code:
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double sum = 0;
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                sum += currentNode.val;
                
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            
            result.add(sum / levelSize);
        }
        
        return result;
    }
}

// Time Complexity: O(N), where N is the number of nodes in the binary tree. We visit each node exactly once.
// Space Complexity: O(M), where M is the maximum number of nodes at any level in the binary tree. In the worst case, this can be O(N/2) for a complete binary tree, which simplifies to O(N).

// Approach 2: DFS(Depth First Search).
// We can use a depth-first search (DFS) approach to traverse the binary tree. We can maintain two lists: one for the sum of node values at each level and another for the count of nodes at each level. As we traverse the tree, we can update these lists accordingly. Finally, we can compute the average for each level by dividing the sum by the count and store it in a result list.

// Code:
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        List<Double> sumList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        
        dfs(root, 0, sumList, countList);
        
        for (int i = 0; i < sumList.size(); i++) {
            result.add(sumList.get(i) / countList.get(i));
        }
        
        return result;
    }
    
    private void dfs(TreeNode node, int level, List<Double> sumList, List<Integer> countList) {
        if (node == null) {
            return;
        }
        
        if (level < sumList.size()) {
            sumList.set(level, sumList.get(level) + node.val);
            countList.set(level, countList.get(level) + 1);
        } else {
            sumList.add((double) node.val);
            countList.add(1.0);
        }
        
        dfs(node.left, level + 1, sumList, countList);
        dfs(node.right, level + 1, sumList, countList);
    }
}

// Time Complexity: O(N), where N is the number of nodes in the binary tree. We visit each node exactly once.
// Space Complexity: O(H), where H is the height of the binary tree. In the worst case, this can be O(N) for a skewed binary tree, and O(log N) for a balanced binary tree. Additionally, we use O(L) space for the sumList and countList, where L is the number of levels in the binary tree, which can also be O(N) in the worst case.