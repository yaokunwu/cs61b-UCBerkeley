public class Palindrome {
    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> palindrome = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            palindrome.addLast(word.charAt(i));
        }
        return palindrome;
    }

    public static boolean isPalindrome(String word) {
        Deque<Character> test = wordToDeque(word);
        if (test.size() <= 1) {
            return true;
        }
        while (test.size() > 1) {
            if (test.removeLast() != test.removeFirst()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> test = wordToDeque(word);
        if (test.size() <= 1) {
            return true;
        }
        while (test.size() > 1) {
            if (!cc.equalChars(test.removeFirst(), test.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
