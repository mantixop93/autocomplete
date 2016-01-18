import java.util.*;

/**
 * Created by Mantixop on 1/18/16.
 */
public class RWayTrie <T> implements Trie <T> {

    private static int R = 26;
    private Node root;

    private class Node{
        T value;
        Object[] next = new Object[R];
    }

    public void add(Tuple<T> tuple) {
        root = add(root, tuple, 0);
    }

    private Node add(Node x, Tuple<T> tuple, int d)
    {
        if (x == null) x = new Node();
        if (d == tuple.getKey().length()) {
            x.value = tuple.getValue();
            return x;
        }
        int index = tuple.getKey().charAt(d) - 'a';
        x.next[index] = add((Node) x.next[index], tuple, d + 1);
        return x;
    }

    public boolean contains(String word) {
        Node x = contains(root, word, 0);
        if ((x == null) || (x.value == null)) {
            return false;
        }
        return true;
    }

    private Node contains(Node x, String key, int d){
        if (x == null) return null;
        if (d == key.length()) return x;
        int index = key.charAt(d) - 'a';
        return contains((Node) x.next[index], key, d+1);
    }

    public boolean delete(String word) {
        if (contains(word)) {
            root = delete(root, word, 0);
            return true;
        }
        return false;
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            x.value = null;
        }
        else {
            int c = key.charAt(d) - 'a';
            x.next[c] = delete((Node) x.next[c], key, d + 1);
        }
        if (x.value != null) return x;
        for (char c = 0; c < R; c++)
            if (x.next[c] != null) return x;
        return null;
    }

    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        Queue<String> q = new LinkedList<String>();
        collect(contains(root, pref, 0), pref, q);
        return q;
    }

    private void collect(Node x, String pre, Queue<String> q)
    {
        if (x == null) return;
        if (x.value != null) q.add(pre);
        for (char c = 0; c < R ; c++)
            collect((Node) x.next[c], pre + (char)(c + 'a'), q);
    }

    public int size() {
        int size = 0;
        Iterator iterator = words().iterator();
        while (iterator.hasNext()){
            size++;
            iterator.next();
        }
        return size;
    }
}
