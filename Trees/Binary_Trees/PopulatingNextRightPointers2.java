// Problem Link: https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii?envType=problem-list-v2&envId=wo7dda2m

// Approach 1: Level Order Traversal.
// We can use a queue to perform a level order traversal of the tree. For each level, we can connect the nodes using the next pointer and add the children of the nodes to the queue for the next level.We can continue this process until we have processed all the levels of the tree.

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
        }
        
        return root;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the tree. We visit each node exactly once.
// Space Complexity: O(n), where n is the number of nodes in the tree. In the worst case, we may have to store all the nodes of a level in the queue, which can be O(n) in the case of a complete binary tree.

// Approach 2: Using Next Pointers.
// We can use the next pointers to connect the nodes at each level without using extra space. We can start from the leftmost node of each level and connect the nodes using the next pointers. We can also keep track of the next level's leftmost node to move down to the next level after we have connected all the nodes at the current level.We can continue this process until we have processed all the levels of the tree.

// Code:
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        
        Node leftmost = root;
        
        while (leftmost != null) {
            Node current = leftmost;
            Node prev = null;
            leftmost = null;
            
            while (current != null) {
                if (current.left != null) {
                    if (prev != null) {
                        prev.next = current.left;
                    } else {
                        leftmost = current.left;
                    }
                    prev = current.left;
                }
                
                if (current.right != null) {
                    if (prev != null) {
                        prev.next = current.right;
                    } else {
                        leftmost = current.right;
                    }
                    prev = current.right;
                }
                
                current = current.next;
            }
        }
        
        return root;
    }
}

// Time Complexity: O(n), where n is the number of nodes in the tree. We visit each node exactly once.
// Space Complexity: O(1), since we are using only a constant amount of extra space to keep track of the current node and the previous node at each level.