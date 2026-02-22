// Problem Link: https://leetcode.com/problems/validate-binary-tree-nodes?envType=problem-list-v2&envId=wo7dda2m

// Intuition:
/* 
What is the structure of a binary tree❓
Simply:
Each node has only one parent (Except for the root).
There is only one root.
Each node has at most 2 children (leaf nodes have no children).
There is no Cycle.🚴‍♀️
*/

/*
Since we have two arrays indicating left and right children for each node then:
-> We will iterate over them to see if a node has two parents.
-> Search for the root that has no parents and if there are multiple roots then return false.
-> Check for cycles.
-> Check for multiple components.

But how to check for cycles and multiple components?🤔
-> We want to traverse the graph to check that but any known traversal algorithms?🤔
-> YES, our today's heros🦸‍♂️ BFS and DFS.
*/

/*
We can use any algorithm of them and while using them we maintain visited array to mark nodes that were visited before and if we visited them again then we are sure there is a cycle.🚴‍♀️
But how to check for multiple componenets.🤨
Simply, if we started BFS or DFS from the root and the given graph is a tree with only one connected component then for sure all nodes in visited array are true otherwise there will be some true elements and false elements.
These are the full steps to solve todays problem.
/*

Approach: BFS or DFS
1. Create an array called childCount of size n to track child which nodes have parents.
2. Update Child Count:
-> Iterate through the leftChild array and for each left child:
   If the left child exists (not -1), mark it as having a parent by setting childCount[child] to true.
-> Iterate through the rightChild array and for each right child:
   If the right child exists (not -1), mark it as having a parent by setting childCount[child] to true.
3. If childCount[child] is true. Then there's multiple parents for this child.
   Determine Root Node:
   Initialize a variable root to -1 (indicating no root found yet).
   Iterate through the childCount array and For each node:
   If the node has no parent (childCount[node] is false):
   If root is -1 (no root assigned yet), set root to the current node.
   If root is not -1 (root already assigned):
   Return false, indicating multiple roots found.
   After that, If root is still -1 (no root found):
   Return false, indicating no root found (not a valid binary tree).

# Breadth-First Search for Valid Binary Tree (isBinaryTreeValid):
1. Initialize a visited array to track visited nodes and queue for BFS traversal.
2. Mark the root node as visited and enqueue it.
3. While the queue is not empty:
   Dequeue a node and mark it as the current node.
   Check the left child of the current node:
   If it exists and is already visited, return false (cycle detected).
   Enqueue the left child and mark it as visited.
   Check the right child of the current node:
   If it exists and is already visited, return false (cycle detected).
   Enqueue the right child and mark it as visited.

After BFS, check if all nodes were visited:
-> If any node is unvisited, return false, indicating there's multiple components.
-> Return true, indicating a valid binary tree.

# Depth-First Search for Valid Binary Tree (isBinaryTreeValid)
It has the same steps like BFS but we will traverse through the tree recursively.
*/

// Code: BFS Solution.
class Solution {
    
    // Breadth-First Search to check if the given nodes form a valid binary tree
    private boolean isBinaryTreeValid(int root, int[] leftChild, int[] rightChild) {
        boolean[] visited = new boolean[leftChild.length]; // Tracks visited nodes
        Queue<Integer> nodeQueue = new LinkedList<>(); // Queue for BFS traversal
        nodeQueue.offer(root);
        visited[root] = true;

        while (!nodeQueue.isEmpty()) {
            int current = nodeQueue.poll();

            // Check left child
            if (leftChild[current] != -1) {
                if (visited[leftChild[current]]) // Check for cycle
                    return false;

                nodeQueue.offer(leftChild[current]);
                visited[leftChild[current]] = true; // Mark left child as visited
            }

            // Check right child
            if (rightChild[current] != -1) {
                if (visited[rightChild[current]]) // Check for cycle
                    return false;

                nodeQueue.offer(rightChild[current]);
                visited[rightChild[current]] = true; // Mark right child as visited
            }
        }

        // Check if there are multiple components
        for (boolean visit : visited) {
            if (!visit)
                return false;
        }

        return true; // All nodes form a valid binary tree
    }

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        boolean[] childCount = new boolean[n]; // Tracks child count for each node

        // Update child count based on leftChild
        for (int child : leftChild) {
            // Check if node has child
            if (child != -1)
                childCount[child] = true; // Mark left child as having a parent
        }

        // Update child count based on rightChild
        for (int child : rightChild) {
            // Check if node has child
            if (child != -1) {
                if (childCount[child]) // Check if the right child already has a parent
                    return false;

                childCount[child] = true; // Mark right child as having a parent
            }
        }

        int root = -1; // Root node
        for (int i = 0; i < n; ++i) {
            if (!childCount[i]) {
                if (root == -1)
                    root = i; // Set root node if not assigned
                else
                    return false; // Multiple roots found, not a valid binary tree
            }
        }

        if (root == -1)
            return false; // No root found, not a valid binary tree

        return isBinaryTreeValid(root, leftChild, rightChild); // Check if the tree is valid
    }

}

// Code: DFS Solution.
class Solution {
    // Depth-First Search to check if the given nodes form a valid binary tree
    private boolean isBinaryTreeValid(int current, int[] leftChild, int[] rightChild, boolean[] visited) {
        // Check left child
        if (leftChild[current] != -1) {
            if (visited[leftChild[current]]) // Check for cycle
                return false;

            visited[leftChild[current]] = true; // Mark left child as visited
            if (!isBinaryTreeValid(leftChild[current], leftChild, rightChild, visited))
                return false;
        }

        // Check right child
        if (rightChild[current] != -1) {
            if (visited[rightChild[current]]) // Check for cycle
                return false;

            visited[rightChild[current]] = true; // Mark right child as visited
            if (!isBinaryTreeValid(rightChild[current], leftChild, rightChild, visited))
                return false;
        }
        return true;
    }

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        boolean[] childCount = new boolean[n]; // Tracks child count for each node

        // Update child count based on leftChild
        for (int child : leftChild) {
            // Check if node has child
            if (child != -1)
                childCount[child] = true; // Mark left child as having a parent
        }

        // Update child count based on rightChild
        for (int child : rightChild) {
            // Check if node has child
            if (child != -1) {
                if (childCount[child]) // Check if the right child already has a parent
                    return false;

                childCount[child] = true; // Mark right child as having a parent
            }
        }

        int root = -1; // Root node
        for (int i = 0; i < n; ++i) {
            if (!childCount[i]) {
                if (root == -1)
                    root = i; // Set root node if not assigned
                else
                    return false; // Multiple roots found, not a valid binary tree
            }
        }

        if (root == -1)
            return false; // No root found, not a valid binary tree

        boolean[] visited = new boolean[n]; // Tracks visited nodes
        visited[root] = true;
        if (!isBinaryTreeValid(root, leftChild, rightChild, visited)) // Check if the tree is valid
            return false;

        // Check if there is multiple components
        for (boolean visit : visited)
            if (!visit)
                return false;

        return true; // All nodes form a valid binary tree
    }
}

// Time Complexity: O(N)
// Since we are Check for parents in leftChild array with cost N then Check for parents in rightChild array with cost N then search for root with cost N then doing BFS or DFS and we will traverse at most all nodes with cost N then we check for multiple compenets with cost N. The total cost is 5 * N which is O(N).

// Space Complexity: O(N)
// Since we are storing if child has parent with cost N and visited array with cost N and the queue at most can have N nodes with cost N. The total cost is 3 * N which is O(N).