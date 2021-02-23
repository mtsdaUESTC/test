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

    public static void main(String[] args) {
        String s = "ab";
        String t = "a";
        SlidingWindow slidingWindow = new SlidingWindow();
        System.out.println(slidingWindow.minWindow(s, t));
    }
}
