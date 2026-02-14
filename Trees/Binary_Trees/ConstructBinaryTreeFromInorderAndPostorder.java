// Problem Link: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal?envType=problem-list-v2&envId=wo7dda2m

// Approach: HashMap + Recursion.
// We can use the property of postorder traversal that the last element is the root of the tree. We can find the index of this root in the inorder traversal to determine the left and right subtrees. We can then recursively build the left and right subtrees using the corresponding segments of the inorder and postorder arrays. We can use a HashMap to store the indices of the elements in the inorder array for O(1) access. We can also use a variable to keep track of the current index in the postorder array, starting from the end.We can decrement this index as we build the tree.We can stop the recursion when the left index is greater than the right index, which means there are no more nodes to construct.

// Code:
class Solution {
    private HashMap<Integer,Integer> map;
    private int index;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        index = postorder.length - 1;
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return helper(inorder,postorder,0,inorder.length-1);
    }
    private TreeNode helper(int[] inorder, int[] postorder, int start, int end){
        if(start > end) return null;
        int rootVal = postorder[index--];
        TreeNode node = new TreeNode(rootVal);
        int inorderIndex = map.get(rootVal);
        node.right = helper(inorder,postorder,inorderIndex+1,end);
        node.left = helper(inorder,postorder,start,inorderIndex-1);
        return node;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the tree. We visit each node once to build the tree.
// Space Complexity: O(n), where n is the number of nodes in the tree. The space complexity is O(n) due to the HashMap and the recursive call stack. The HashMap takes O(n) space to store the indices of the elements in the inorder array, and the recursive call stack can go up to O(n) in the worst case when the tree is skewed.