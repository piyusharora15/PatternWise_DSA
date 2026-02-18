// Problem Link: https://leetcode.com/problems/populating-next-right-pointers-in-each-node?envType=problem-list-v2&envId=wo7dda2m

// Approach 1: BFS(Level Order Traversal).
// We can use a queue to perform a level order traversal of the tree. For each level, we can connect the nodes using the next pointer. The last node of each level will have its next pointer set to null.We can repeat this process until we have traversed all levels of the tree.

// Code:
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null;
            
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                
                if (prev != null) {
                    prev.next = current;
                }
                prev = current;
                
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            // The last node of each level will have its next pointer set to null by default.
        }
        
        return root;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the tree. We visit each node exactly once.
// Space Complexity: O(n), in the worst case, the queue can hold all the nodes at the last level of the tree, which can be O(n) in the case of a complete binary tree.

// Approach 2: Using already established next pointers.
// We can use the already established next pointers to connect the nodes at the next level. We can start from the leftmost node of each level and connect the children of the nodes at that level using the next pointers. This way, we can avoid using extra space for a queue.We can repeat this process until we have connected all levels of the tree.

// Code:
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        
        Node leftmost = root;
        
        while (leftmost.left != null) {
            Node current = leftmost;
            
            while (current != null) {
                // Connect the left child to the right child
                current.left.next = current.right;
                
                // Connect the right child to the next node's left child, if it exists
                if (current.next != null) {
                    current.right.next = current.next.left;
                }
                
                // Move to the next node in the current level
                current = current.next;
            }
            
            // Move to the leftmost node of the next level
            leftmost = leftmost.left;
        }
        
        return root;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the tree. We visit each node exactly once.
// Space Complexity: O(1), we are using only a constant amount of extra space for the pointers.