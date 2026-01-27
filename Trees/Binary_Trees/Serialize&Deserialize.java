// Problem Link: https://leetcode.com/problems/serialize-and-deserialize-binary-tree?envType=problem-list-v2&envId=wo7dda2m

// Approach: Using Preorder Traversal (DFS).
// We will serialize the tree using preorder traversal and use a special marker for null nodes.When deserializing, we will read the values in the same order and reconstruct the tree.We will use a queue to facilitate the reconstruction process.

// Code:
public class Codec {
    private static final String NULL_MARKER = "#";
    private static final String DELIMITER = ",";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root,sb);
        return sb.toString();
    }
    private void serializeHelper(TreeNode node,StringBuilder sb){
        if(node == null){
            sb.append(NULL_MARKER).append(DELIMITER);
            return;
        }
        sb.append(node.val).append(DELIMITER);
        serializeHelper(node.left,sb);
        serializeHelper(node.right,sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] tokens = data.split(DELIMITER);
        Queue<String> queue = new LinkedList<>(Arrays.asList(tokens));
        return deserializeHelper(queue);   
    }
    private TreeNode deserializeHelper(Queue<String> queue){
        String token = queue.poll();
        if(token.equals(NULL_MARKER)){
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(token));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);
        return node;
    }
}

// Time Complexity: O(N), where N is the number of nodes in the tree. We visit each node once during serialization and deserialization.
// Space Complexity: O(N), for storing the serialized string and the recursion stack during deserialization.