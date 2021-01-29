public class Palindrome{
    public Deque<Character> wordToDeque(String word){
        Deque<Character> ret = new LinkedListDeque<>();
        for(int i=0;i<word.length();i++) {
            ret.addLast(word.charAt(i));
        }
        return ret;
    }
//    public boolean isPalindrome(String word){
//        if(word.length()==0||word.length()==1) {
//            return true;
//        }
//        int l = 0,r = word.length()-1;
//        while(l<r) {
//            if(word.charAt(l)!=word.charAt(r)) {
//                return false;
//            }
//            l++;
//            r--;
//        }
//        return true;
//    }

    public boolean isPalindrome(String word) {
        return helper(wordToDeque(word));
    }

    private boolean helper(Deque<Character> q) {
        if(q.size()<=1)return true;
        if(q.removeFirst()!=q.removeLast())return false;
        return helper(q);
    }

    public boolean isPalindrome(String word,CharacterComparator comp) {
        return helper(wordToDeque(word),comp);
    }
    private boolean helper(Deque<Character> q,CharacterComparator comp) {
        if(q.size()<=1)return true;
        if(!comp.equalChars(q.removeFirst(),q.removeLast()))return false;
        return helper(q,comp);
    }
}