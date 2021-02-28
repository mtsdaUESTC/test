import java.util.HashMap;

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
            need.put(c, need.getOrDefault(c, 0)+1);
        }
        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        while (right < sChars.length) {
            char c = sChars[right];
            right ++;
            if (need.containsKey(c)) {
                window.put(c,window.getOrDefault(c,0)+1);
                if (window.get(c) .equals(need.get(c))) valid ++;
            }

            while (valid == need.size()) {
                if(right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = sChars[left];
                left ++;
                if (need.containsKey(d)) {
                    if(window.get(d).equals(need.get(d)))
                        valid --;
                    window.put(d,window.get(d)-1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start,start+len);
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
        HashMap<Character,Integer> need = new HashMap<>();
        HashMap<Character,Integer> window = new HashMap<>();
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();
        for (char s1Char : s1Chars) {
            need.put(s1Char, need.getOrDefault(s1Char, 0) + 1);
        }
        int left = 0,right = 0, valid = 0;
        while(right<s2Chars.length){
            char c = s1Chars[right];
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) valid ++;
            }
            while (right-left >= s1Chars.length) {
                if (valid == need.size()) return true;
                char d = s1Chars[left];
                left++;
                if (window.containsKey(d)){
                    if (window.get(d).equals(need.get(d))) valid--;
                    window.put(d, window.get(d)-1);
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        String s = "ab";
        String t = "a";
        SlidingWindow slidingWindow = new SlidingWindow();
        System.out.println(slidingWindow.minWindow(s, t));
    }
}
