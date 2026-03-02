// Problem Link: https://leetcode.com/problems/intersection-of-two-arrays-ii?envType=problem-list-v2&envId=wh88bf73

// Approach 1: Using HashMap.
// We can use a HashMap to store the frequency of each element in the first array. Then, we can iterate through the second array and check if the element exists in the HashMap. If it does, we add it to the result list and decrease its frequency in the HashMap. This way, we can ensure that we only add the common elements as many times as they appear in both arrays.

// Code:
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        // Count the frequency of each element in nums1
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Check for common elements in nums2
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                result.add(num);
                map.put(num, map.get(num) - 1); // Decrease the frequency
            }
        }

        // Convert the result list to an array
        int[] intersection = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            intersection[i] = result.get(i);
        }

        return intersection;
    }
}

// Time Complexity: O(n + m), where n is the length of nums1 and m is the length of nums2.
// Space Complexity: O(min(n, m)), where n and m are the lengths of the two arrays. We use a HashMap to store the frequency of elements from the smaller array, and a list to store the result.

// Approach 2: Using Sorting and Two Pointers.
// We can sort both arrays and use two pointers to find the common elements. We can iterate through both arrays simultaneously, and if the elements at the pointers are equal, we add it to the result list and move both pointers. If the element in the first array is smaller, we move the pointer of the first array. If the element in the second array is smaller, we move the pointer of the second array. We continue this process until we reach the end of either array. This approach is efficient and does not require extra space for a HashMap, but it does require sorting the arrays first.

// Code:
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        // Convert the result list to an array
        int[] intersection = new int[result.size()];
        for (int k = 0; k < result.size(); k++) {
            intersection[k] = result.get(k);
        }

        return intersection;
    }
}

// Time Complexity: O(n log n + m log m), where n and m are the lengths of nums1 and nums2 respectively, due to the sorting step.
// Space Complexity: O(min(n, m)), where n and m are the lengths of the two arrays. We use a list to store the result, which can be at most the size of the smaller array.