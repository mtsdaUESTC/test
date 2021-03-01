import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 滑动窗口
 */
public class SlidingWindow {
    /**
     * 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     *
     * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
     * 示例 1：
     *
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * 示例 2：
     *
     * 输入：s = "a", t = "a"
     * 输出："a"
     *  
     * 提示：
     *
     * 1 <= s.length, t.length <= 105
     * s 和 t 由英文字母组成
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        char[] tChars = t.toCharArray();
        char[] sChars = s.toCharArray();
        for (char c : tChars) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        while (right < sChars.length) {
            char c = sChars[right];
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) valid++;
            }

            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = sChars[left];
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d)))
                        valid--;
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    /**
     * leetcode 567 字符串的排列
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
     *
     * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
     *
     *  
     *
     * 示例 1：
     *
     * 输入: s1 = "ab" s2 = "eidbaooo"
     * 输出: True
     * 解释: s2 包含 s1 的排列之一 ("ba").
     * 示例 2：
     *
     * 输入: s1= "ab" s2 = "eidboaoo"
     * 输出: False
     *
     * 链接：https://leetcode-cn.com/problems/permutation-in-string
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();
        for (char s1Char : s1Chars) {
            need.put(s1Char, need.getOrDefault(s1Char, 0) + 1);
        }
        int left = 0, right = 0, valid = 0;
        while (right < s2Chars.length) {
            char c = s2Chars[right];
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) valid++;
            }
            while (right - left >= s1Chars.length) {
                if (valid == need.size()) return true;
                char d = s2Chars[left];
                left++;
                if (window.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) valid--;
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return false;
    }

    /**
     * leetcode 159 至多包含两个不同字符的最长子串
     * 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。
     *
     * 示例 1:
     *
     * 输入: "eceba"
     * 输出: 3
     * 解释: t 是 "ece"，长度为3。
     * 示例 2:
     *
     * 输入: "ccaabbb"
     * 输出: 5
     * 解释: t 是 "aabbb"，长度为5。
     *
     * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        HashMap<Character, Integer> need = new HashMap<>();
        char[] chars = s.toCharArray();
        int left = 0, right = 0, len = 0;
        while (right < chars.length) {
            char c = chars[right];

            need.put(c, need.getOrDefault(c, 0) + 1);
            right++;
            while (need.size() > 2) {
                char d = chars[left];
                if (need.get(d).equals(1)) {
                    need.remove(d);
                } else {
                    need.put(d, need.get(d) - 1);
                }
                left++;
            }
            len = Math.max(right - left, len);
        }
        return len;
    }
    /**
     * leetcode 438 找到字符串中所有字母异位词
     * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     *
     * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
     *
     * 说明：
     *
     * 字母异位词指字母相同，但排列不同的字符串。
     * 不考虑答案输出的顺序。
     * 示例 1:
     *
     * 输入:
     * s: "cbaebabacd" p: "abc"
     *
     * 输出:
     * [0, 6]
     *
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
     *  示例 2:
     *
     * 输入:
     * s: "abab" p: "ab"
     *
     * 输出:
     * [0, 1, 2]
     *
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        char[] sChar = s.toCharArray();
        char[] pChar = p.toCharArray();
        for (char pc : pChar) {
            need.put(pc, need.getOrDefault(pc, 0) + 1);
        }
        int left = 0, right = 0, valid = 0;
        List<Integer> res = new ArrayList<>();
        while (right < sChar.length) {
            char c = sChar[right];
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) valid++;
            }
            while (valid == need.size()) {
                if (right - left == pChar.length) {
                    res.add(left);
                }
                char d = sChar[left];
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }

                left++;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        String s = "ab";
        String t = "a";
        SlidingWindow slidingWindow = new SlidingWindow();
        System.out.println(slidingWindow.minWindow(s, t));
    }
}
