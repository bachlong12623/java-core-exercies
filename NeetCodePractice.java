import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NeetCodePractice {
    public static void main(String[] args) {
        System.out.println("12. Algorithm Practice (NeetCode)\n");

        // 1) Buy and Sell Crypto
        int[] prices = {10, 1, 5, 6, 7, 1};
        System.out.println("Buy and Sell Crypto max profit: " + maxProfit(prices));

        // 2) Is Palindrome
        String palindromeInput = "A man, a plan, a canal: Panama";
        System.out.println("Is Palindrome: \"" + palindromeInput + "\" -> " + isPalindrome(palindromeInput));

        // 3) Two Integer Sum
        int[] nums = {3, 4, 5, 6};
        int target = 7;
        System.out.println("Two Integer Sum indices: " + Arrays.toString(twoSum(nums, target)));

        // 4) Is Anagram
        String s = "listen";
        String t = "silent";
        System.out.println("Is Anagram: \"" + s + "\" & \"" + t + "\" -> " + isAnagram(s, t));

        // 5) Duplicate Integer
        int[] duplicateInput = {1, 2, 3, 3};
        System.out.println("Duplicate Integer exists: " + hasDuplicate(duplicateInput));
    }

    // 1) Buy and Sell Crypto
    // Return max profit from one buy and one sell.
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int bestProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else {
                int profit = price - minPrice;
                if (profit > bestProfit) {
                    bestProfit = profit;
                }
            }
        }
        return bestProfit;
    }

    // 2) Is Palindrome
    // Ignore non-alphanumeric chars and case.
    public static boolean isPalindrome(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(text.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(text.charAt(right))) {
                right--;
            }

            char l = Character.toLowerCase(text.charAt(left));
            char r = Character.toLowerCase(text.charAt(right));
            if (l != r) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    // 3) Two Integer Sum
    // Return indices of two numbers that add up to target.
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) {
                return new int[]{seen.get(need), i};
            }
            seen.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }

    // 4) Is Anagram
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }

        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    // 5) Duplicate Integer
    public static boolean hasDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
}
