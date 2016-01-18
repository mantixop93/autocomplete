/**
 * Created by Mantixop on 1/18/16.
 */
public class Main {
    public static void main(String[] args) {
        RWayTrie<Integer> rWayTrie = new RWayTrie<Integer>();
        String str = "abc";
        String str1 = "abcd";
        String str2 = "abw";
        String str3 = "abcf";
        Tuple<Integer> tuple = new Tuple<Integer>(str, str.length());
        rWayTrie.add(tuple);
        Tuple<Integer> tuple2 = new Tuple<Integer>(str1, str1.length());
        rWayTrie.add(tuple2);
        Tuple<Integer> tuple3 = new Tuple<Integer>(str2, str2.length());
        rWayTrie.add(tuple3);
        Tuple<Integer> tuple4 = new Tuple<Integer>(str3, str3.length());
        rWayTrie.add(tuple4);
        System.out.println(rWayTrie.contains("abc"));
        System.out.println(rWayTrie.words());
        rWayTrie.delete("abc");
        System.out.println(rWayTrie.words());
        System.out.println(rWayTrie.size());
    }
}
