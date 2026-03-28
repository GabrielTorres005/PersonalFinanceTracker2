package com.tracker;
import java.util.*;

/**
 * TransactionSearchAndSort provides advanced search and sorting algorithms
 * for Transaction objects. Features include binary search, linear search,
 * merge sort, and quick sort - optimized for resume portfolio.
 * 
 * Algorithms included:
 * - Search: Binary Search (O(log n)), Linear Search (O(n))
 * - Sort: Merge Sort (O(n log n)), Quick Sort (O(n log n) avg)
 * 
 * @author GabrielTorres005
 * @version 1.0
 */
public class TransactionSearchAndSort 
{
        /**
     * Enumeration for sorting criteria
     */
        public enum SortCriteria {
            BY_NAME, BY_DATE, BY_CATEGORY, BY_AMOUNT
        }
    
        // ======================== SORTING ALGORITHMS ========================
    
        /**
         * Merge Sort - Divide and Conquer Algorithm
         * Time Complexity: O(n log n) - Guaranteed stable sort
         * Space Complexity: O(n) - Requires extra space
         * 
         * Advantages: 
         * - Guaranteed O(n log n) performance
         * - Stable sort (maintains relative order of equal elements)
         * - Excellent for linked lists
         * - Predictable performance
         * 
         * @param transactions List of transactions to sort
         * @param criteria The attribute to sort by
         * @param ascending If true, sorts in ascending order; false for descending
         * @return Sorted list of transactions
         */
        public static LinkedList<Transactions> mergeSort(LinkedList<Transactions> transactions, 
                                                           SortCriteria criteria, 
                                                           boolean ascending) {
            if (transactions == null || transactions.size() <= 1) {
                return new LinkedList<>(transactions);
            }
            
            List<Transactions> list = new ArrayList<>(transactions);
            mergeSortHelper(list, 0, list.size() - 1, criteria, ascending);
            return new LinkedList<>(list);
        }
    
        /**
         * Helper method for merge sort - performs recursive divide and conquer
         * 
         * @param list The list being sorted
         * @param left Left boundary index
         * @param right Right boundary index
         * @param criteria Sort criteria
         * @param ascending Sort order
         */
        private static void mergeSortHelper(List<Transactions> list, int left, int right, 
                                           SortCriteria criteria, boolean ascending) {
            if (left < right) {
                int mid = left + (right - left) / 2; // Prevents integer overflow
                
                // Divide: Sort left half
                mergeSortHelper(list, left, mid, criteria, ascending);
                
                // Divide: Sort right half
                mergeSortHelper(list, mid + 1, right, criteria, ascending);
                
                // Conquer: Merge sorted halves
                merge(list, left, mid, right, criteria, ascending);
            }
        }
    
        /**
         * Merges two sorted subarrays into one sorted array
         * 
         * @param list The list containing subarrays
         * @param left Left boundary
         * @param mid Middle boundary
         * @param right Right boundary
         * @param criteria Sort criteria
         * @param ascending Sort order
         */
        private static void merge(List<Transactions> list, int left, int mid, int right, 
                                 SortCriteria criteria, boolean ascending) {
            List<Transactions> temp = new ArrayList<>();
            int i = left;      // Pointer for left subarray
            int j = mid + 1;   // Pointer for right subarray
    
            // Compare and merge elements from both subarrays
            while (i <= mid && j <= right) {
                int comparison = compareTransactions(list.get(i), list.get(j), criteria);
                
                if ((ascending && comparison <= 0) || (!ascending && comparison >= 0)) {
                    temp.add(list.get(i++));
                } else {
                    temp.add(list.get(j++));
                }
            }
    
            // Add remaining elements from left subarray
            while (i <= mid) {
                temp.add(list.get(i++));
            }
    
            // Add remaining elements from right subarray
            while (j <= right) {
                temp.add(list.get(j++));
            }
    
            // Copy sorted elements back to original list
            for (int k = 0; k < temp.size(); k++) {
                list.set(left + k, temp.get(k));
            }
        }
    
        /**
         * Quick Sort - Efficient Divide and Conquer Algorithm
         * Time Complexity: O(n log n) average, O(n²) worst case
         * Space Complexity: O(log n) due to recursion stack
         * 
         * Advantages:
         * - Very fast in practice (better cache locality)
         * - In-place sorting (minimal extra space)
         * - Efficient for most real-world data
         * - Used in many standard library implementations
         * 
         * @param transactions List of transactions to sort
         * @param criteria The attribute to sort by
         * @param ascending If true, sorts in ascending order; false for descending
         * @return Sorted list of transactions
         */
        public static LinkedList<Transactions> quickSort(LinkedList<Transactions> transactions, 
                                                          SortCriteria criteria, 
                                                          boolean ascending) {
            if (transactions == null || transactions.size() <= 1) {
                return new LinkedList<>(transactions);
            }
    
            List<Transactions> list = new ArrayList<>(transactions);
             quickSortHelper(list, 0, list.size() - 1, criteria, ascending);
            return new LinkedList<>(list);
        }
    
        /**
         * Helper method for quick sort - performs recursive partitioning
         * 
         * @param list The list being sorted
         * @param low Lower boundary index
         * @param high Upper boundary index
         * @param criteria Sort criteria
         * @param ascending Sort order
         */
        private static void quickSortHelper(List<Transactions> list, int low, int high, 
                                           SortCriteria criteria, boolean ascending) {
            if (low < high) {
                // Partition and get pivot index
                int partitionIndex = partition(list, low, high, criteria, ascending);
                
                // Recursively sort left partition
                quickSortHelper(list, low, partitionIndex - 1, criteria, ascending);
                
                // Recursively sort right partition
                quickSortHelper(list, partitionIndex + 1, high, criteria, ascending);
            }
        }
    
        /**
         * Partitions the list around a pivot element
         * Elements smaller than pivot go left, larger go right
         * 
         * @param list The list being partitioned
         * @param low Lower boundary
         * @param high Upper boundary (pivot)
         * @param criteria Sort criteria
         * @param ascending Sort order
         * @return Index of pivot in its final sorted position
         */
        private static int partition(List<Transactions> list, int low, int high, 
                                    SortCriteria criteria, boolean ascending) {
            Transactions pivot = list.get(high);
            int i = low - 1; // Index of smaller element
    
            for (int j = low; j < high; j++) {
                int comparison = compareTransactions(list.get(j), pivot, criteria);
                
                // If element is smaller than pivot, swap it
                if ((ascending && comparison < 0) || (!ascending && comparison > 0)) {
                    i++;
                    Collections.swap(list, i, j);
                }
            }
            
            // Place pivot in its correct position
            Collections.swap(list, i + 1, high);
            return i + 1;
        }
    
        // ======================== SEARCH ALGORITHMS ========================
    
        /**
         * Binary Search - Fast search on sorted data
         * Time Complexity: O(log n)
         * Space Complexity: O(1) - Iterative approach
         * 
         * Prerequisites: Data must be sorted by the search criteria
         * 
         * Advantages:
         * - Extremely fast for large datasets
         * - Logarithmic time complexity
         * - Works on sorted arrays/lists
         * 
         * @param transactions Sorted list of transactions
         * @param searchValue Value to search for
         * @param criteria The attribute to search by
         * @return Index of found transaction, or -1 if not found
         */
        public static int binarySearch(LinkedList<Transactions> transactions, 
                                       String searchValue, 
                                       SortCriteria criteria) {
            if (transactions == null || transactions.isEmpty()) {
                return -1;
            }
    
            List<Transactions> list = new ArrayList<>(transactions);
            int left = 0;
            int right = list.size() - 1;
    
            while (left <= right) {
                // Calculate middle index (prevents integer overflow)
                int mid = left + (right - left) / 2;
                String midValue = getAttributeValue(list.get(mid), criteria);
                int comparison = midValue.compareTo(searchValue);
    
                if (comparison == 0) {
                    // Found exact match
                    return mid;
                } else if (comparison < 0) {
                    // Search in right half
                    left = mid + 1;
                } else {
                    // Search in left half
                    right = mid - 1;
                }
            }
            
            // Not found
            return -1;
        }
    
        /**
         * Binary Search for Amount (numeric values)
         * Time Complexity: O(log n)
         * Space Complexity: O(1)
         * 
         * Prerequisites: List must be sorted by amount
         * 
         * @param transactions Sorted list of transactions by amount
         * @param amount Amount to search for
         * @return Index of found transaction, or -1 if not found
         */
        public static int binarySearchAmount(LinkedList<Transactions> transactions, double amount) {
            if (transactions == null || transactions.isEmpty()) {
                return -1;
            }
    
            List<Transactions> list = new ArrayList<>(transactions);
            int left = 0;
            int right = list.size() - 1;
    
            while (left <= right) {
                int mid = left + (right - left) / 2;
                double midAmount = list.get(mid).getAmount();
    
                if (midAmount == amount) {
                    // Found exact match
                    return mid;
                } else if (midAmount < amount) {
                    // Search in right half
                    left = mid + 1;
                } else {
                    // Search in left half
                    right = mid - 1;
                }
            }
            
            // Not found
            return -1;
        }
    
        /**
         * Binary Search - Find all occurrences closest to target value
         * Time Complexity: O(log n)
         * 
         * @param transactions Sorted list of transactions
         * @param searchValue Value to search for
         * @param criteria The attribute to search by
         * @return Transactions with values close to target (within same position range)
         */
        public static LinkedList<Transactions> binarySearchClosest(LinkedList<Transactions> transactions,
                                                                   String searchValue,
                                                                   SortCriteria criteria) {
            LinkedList<Transactions> results = new LinkedList<>();
            
            if (transactions == null || transactions.isEmpty()) {
                return results;
            }
    
            List<Transactions> list = new ArrayList<>(transactions);
            int left = 0;
            int right = list.size() - 1;
            int closestIndex = -1;
    
            while (left <= right) {
                int mid = left + (right - left) / 2;
                String midValue = getAttributeValue(list.get(mid), criteria);
                int comparison = midValue.compareTo(searchValue);
    
                if (comparison == 0) {
                    closestIndex = mid;
                    break;
                } else if (comparison < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
    
            if (closestIndex != -1) {
                results.add(list.get(closestIndex));
            }
            
            return results;
        }
    
        /**
         * Linear Search - Simple search on unsorted data
         * Time Complexity: O(n)
         * Space Complexity: O(m) where m is number of matches
         * 
         * Advantages:
         * - Works on unsorted data
         * - Finds all matches
         * - Simple and straightforward
         * 
         * @param transactions List of transactions
         * @param searchValue Value to search for (exact match)
         * @param criteria The attribute to search by
         * @return List of all matching transactions
         */
        public static LinkedList<Transactions> linearSearch(LinkedList<Transactions> transactions, 
                                                            String searchValue, 
                                                            SortCriteria criteria) {
            LinkedList<Transactions> results = new LinkedList<>();
            
            if (transactions == null) {
                return results;
            }
    
            for (Transactions t : transactions) {
                if (getAttributeValue(t, criteria).equalsIgnoreCase(searchValue)) {
                    results.add(t);
                }
            }
            
            return results;
        }
    
        /**
         * Linear Search - Find all transactions with amounts in specified range
         * Time Complexity: O(n)
         * Space Complexity: O(m) where m is number of matches
         * 
         * @param transactions List of transactions
         * @param minAmount Minimum amount (inclusive)
         * @param maxAmount Maximum amount (inclusive)
         * @return List of transactions within the amount range
         */
        public static LinkedList<Transactions> linearSearchAmountRange(LinkedList<Transactions> transactions, 
                                                                       double minAmount, 
                                                                       double maxAmount) {
            LinkedList<Transactions> results = new LinkedList<>();
            
            if (transactions == null) {
                return results;
            }
    
            for (Transactions t : transactions) {
                if (t.getAmount() >= minAmount && t.getAmount() <= maxAmount) {
                    results.add(t);
                }
            }
            
            return results;
        }
    
        /**
         * Linear Search with Partial String Matching (Fuzzy Search)
         * Time Complexity: O(n * m) where m is pattern length
         * Space Complexity: O(k) where k is number of matches
         * 
         * @param transactions List of transactions
         * @param pattern Partial string to search for (case-insensitive)
         * @param criteria The attribute to search by
         * @return List of transactions with partial matches
         */
        public static LinkedList<Transactions> linearSearchFuzzy(LinkedList<Transactions> transactions, 
                                                                 String pattern, 
                                                                 SortCriteria criteria) {
            LinkedList<Transactions> results = new LinkedList<>();
            
            if (transactions == null || pattern == null) {
                return results;
            }
    
            String lowerPattern = pattern.toLowerCase();
    
            for (Transactions t : transactions) {
                if (getAttributeValue(t, criteria).toLowerCase().contains(lowerPattern)) {
                    results.add(t);
                }
            }
            
            return results;
        }
    
        /**
         * Linear Search - Multiple filter criteria
         * Time Complexity: O(n)
         * Space Complexity: O(m) where m is number of matches
         * 
         * @param transactions List of transactions
         * @param category Category to filter by (null to skip)
         * @param minAmount Minimum amount (use -1 to skip)
         * @param maxAmount Maximum amount (use Double.MAX_VALUE to skip)
         * @return Filtered list of transactions
         */
        public static LinkedList<Transactions> linearSearchFilter(LinkedList<Transactions> transactions, 
                                                                  String category, 
                                                                  double minAmount, 
                                                                  double maxAmount) {
            LinkedList<Transactions> results = new LinkedList<>();
            
            if (transactions == null) {
                return results;
            }
    
            for (Transactions t : transactions) {
                boolean categoryMatch = (category == null || t.getCategory().equalsIgnoreCase(category));
                boolean amountMatch = (t.getAmount() >= minAmount && t.getAmount() <= maxAmount);
    
                if (categoryMatch && amountMatch) {
                    results.add(t);
                }
            }
            
            return results;
        }
    
        // ======================== HELPER METHODS ========================
    
        /**
         * Compares two transactions based on specified criteria
         * 
         * @param t1 First transaction
         * @param t2 Second transaction
         * @param criteria Comparison criteria
         * @return Negative if t1 < t2, 0 if equal, positive if t1 > t2
         */
        private static int compareTransactions(Transactions t1, Transactions t2, SortCriteria criteria) {
            switch (criteria) {
                case BY_NAME:
                    return t1.getName().compareTo(t2.getName());
                case BY_DATE:
                    return t1.getDate().compareTo(t2.getDate());
                case BY_CATEGORY:
                    return t1.getCategory().compareTo(t2.getCategory());
                case BY_AMOUNT:
                    return Double.compare(t1.getAmount(), t2.getAmount());
                default:
                    return 0;
            }
        }
    
        /**
         * Extracts the string value of a transaction attribute
         * 
         * @param transaction The transaction object
         * @param criteria The attribute to retrieve
         * @return String representation of the attribute
         */
        private static String getAttributeValue(Transactions transaction, SortCriteria criteria) {
            switch (criteria) {
                case BY_NAME:
                    return transaction.getName();
                case BY_DATE:
                    return transaction.getDate();
                case BY_CATEGORY:
                    return transaction.getCategory();
                case BY_AMOUNT:
                    return String.valueOf(transaction.getAmount());
                default:
                    return "";
            }
        }
    
        /**
         * Checks if a list is sorted by given criteria
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         * 
         * Useful for validating prerequisites before binary search
         * 
         * @param transactions List to check
         * @param criteria Sorting criteria
         * @param ascending Expected sort order
         * @return True if sorted according to criteria, false otherwise
         */
        public static boolean isSorted(LinkedList<Transactions> transactions, 
                                       SortCriteria criteria, 
                                       boolean ascending) {
            if (transactions == null || transactions.size() <= 1) {
                return true;
            }
    
            List<Transactions> list = new ArrayList<>(transactions);
    
            for (int i = 0; i < list.size() - 1; i++) {
                int comparison = compareTransactions(list.get(i), list.get(i + 1), criteria);
                
                if ((ascending && comparison > 0) || (!ascending && comparison < 0)) {
                    return false;
                }
            }
            
            return true;
        }
    
        /**
         * Calculates statistics for transactions in a specific category
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         * 
         * @param transactions List of transactions
         * @param category Category to analyze
         * @return Array containing [count, sum, average, min, max]
         */
        public static double[] getCategoryStatistics(LinkedList<Transactions> transactions, String category) {
            double count = 0;
            double sum = 0;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
    
            if (transactions == null) {
                return new double[]{0, 0, 0, 0, 0};
            }
    
            for (Transactions t : transactions) {
                if (t.getCategory().equalsIgnoreCase(category)) {
                    count++;
                    sum += t.getAmount();
                    min = Math.min(min, t.getAmount());
                    max = Math.max(max, t.getAmount());
                }
            }
    
            double average = (count > 0) ? sum / count : 0;
            
            return new double[]{count, sum, average, min, max};
        } 
}
