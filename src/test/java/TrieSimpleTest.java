import com.lab.autocomplete.trie.RWayTrie;
import com.lab.autocomplete.trie.Tuple;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by Mantixop on 1/22/16.
 */

public class TrieSimpleTest {

    private RWayTrie<Integer> empty;
    private RWayTrie<Integer> one;
    private RWayTrie<Integer> several;
    private String[] wordsInSeveral;

    @Before
    public void init() {
        empty = new RWayTrie<Integer>();
        one = new RWayTrie<Integer>();
        several = new RWayTrie<Integer>();
        wordsInSeveral = new String[]{"th", "one", "tha", "two", "five", "four", "three"};

        one.add(new Tuple<Integer>("one", "one".length()));
        for (int i = 0; i < wordsInSeveral.length; i++) {
            several.add(new Tuple<Integer>(wordsInSeveral[i], wordsInSeveral[i].length()));
        }
    }

    @Test
    public void testContains() {
        assertFalse("Check empty is empty", empty.contains("one"));
        assertTrue("Check one contains 'one'", one.contains("one"));
        for (String element : wordsInSeveral) {
            assertTrue("Check " + element + "in several", several.contains(element));
        }
        assertFalse("Check 'six' not in several", several.contains("six"));
        assertFalse("Check 'seven' not in several", several.contains("seven"));
        assertFalse("Check 'two' in one", one.contains("two"));
    }


    @Test
    public void testSize() {
        assertEquals("Check size of empty", 0, empty.size());
        assertEquals("Check size of one", 1, one.size());
        assertEquals("Check size of several", wordsInSeveral.length, several.size());
    }

    @Test
    public void testDelete() {
        empty.delete("one");
        assertTrue("Empty is still empty", empty.size() == 0);
        one.delete("one");
        assertFalse("'One' removed from one", one.contains("one"));
        assertTrue("One decreased", one.size() == 0);
        several.delete("two");
        assertFalse("'Two' removed from several", several.contains("two"));
        assertTrue("Several decreased", several.size() == wordsInSeveral.length - 1);
    }

    @Test
    public void testDuplicates() {
        several.add(new Tuple<Integer>("five", "five".length()));
        assertTrue("Check if several size not changed", several.size() == wordsInSeveral.length);
    }

    @Test
    public void testWordsWithPref() {
        assertTrue("Check word with pref 't' in several", checkIterable(several.wordsWithPrefix("t"), new String[]{"th", "tha", "two", "three",}));
        assertTrue("Check word with pref 'th' in several", checkIterable(several.wordsWithPrefix("th"), new String[]{"th", "tha", "three"}));
        assertTrue("Check word with pref 'thr' in several", checkIterable(several.wordsWithPrefix("thr"), new String[]{"three"}));
    }

    @Test
    public void testWords() {
        assertTrue("Check word in empty", checkIterable(empty.words(), new String[]{}));
        assertTrue("Check word in one", checkIterable(one.words(), new String[]{"one"}));
        assertTrue("Check word in several", checkIterable(several.words(), wordsInSeveral));
    }

    private boolean checkIterable(Iterable<String> iterable, String[] elements) {
        int i = 0;
        for (Iterator<String> iterator = iterable.iterator(); iterator.hasNext(); ) {
            String str = iterator.next();
            if (!str.equals(elements[i])) {
                return false;
            }
            i++;
        }
        if (i != elements.length) {
            return false;
        }
        return true;
    }
}
