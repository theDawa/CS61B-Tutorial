
import java.util.Locale;

public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> a = new LinkedListDeque<Character>();
        for(int i = 0; i < word.length(); i = i + 1){
            a.addLast(word.charAt(i));
        }
        return a;
    }

    public boolean isPalindrome(String word){
        if((word.length() == 1) || (word.isEmpty())){
            return true;
        }
        Character[] a = new Character[word.length()];
        Character[] b = new Character[word.length()];
        for(int i = 0; i < word.length(); i = i + 1){
            a[i] = word.charAt(i);
        }
        for(int i = word.length() - 1; i >= 0; i = i - 1){
            b[word.length() - 1- i] = word.charAt(i);
        }
        for(int i = 0; i < word.length(); i = i + 1){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if((word.length() == 1) || (word.isEmpty())){
            return true;
        }
        for(int i = 0; i < (word.length()/2); i = i + 1){
            if(!(cc.equalChars(word.charAt(i), word.charAt(word.length() - i - 1)))){
                return false;
            }
        }
        return true;

    }

}
