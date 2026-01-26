// Problem Link: https://leetcode.com/problems/find-duplicate-subtrees?envType=problem-list-v2&envId=tree

// Approach: Recursive DFS with Serialization.
// We can find duplicate subtrees by serializing each subtree into a string representation and using a hash map to count occurrences of each serialized subtree. We perform a depth-first traversal of the tree, serializing each subtree as we go. If a serialized subtree has been seen before, we add the root of that subtree to our result list. Finally, we return the list of duplicate subtree roots.

// Code:
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        Map<String,Integer> mpp = new HashMap<>();
        serialize(root,mpp,result);
        return result;
    }
    private String serialize(TreeNode node,Map<String,Integer> mpp,List<TreeNode> result){
        if(node == null) return "#";
        String left = serialize(node.left,mpp,result);
        String right = serialize(node.right,mpp,result);
        String serial = node.val + "," + left + "," + right;
        int c = mpp.getOrDefault(serial,0);
        if(c == 1){
            result.add(node);
        }
        mpp.put(serial,c+1);
        return serial;
    }
}

// Time Complexity: O(N) where N is the number of nodes in the tree. Each node is visited once during the DFS traversal.
// Space Complexity: O(N) for the hash map and the recursion stack in the worst case.