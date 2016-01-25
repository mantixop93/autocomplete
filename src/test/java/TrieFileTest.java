import com.lab.autocomplete.trie.RWayTrie;
import com.lab.autocomplete.trie.Tuple;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by Mantixop on 1/19/16.
 */

public class TrieFileTest {

    public static RWayTrie <Integer> rWayTrie;
    public static String fileName = "test.txt";
    public static int fileWordCount;
    public String containsWord = "your";
    public String notContainsWord = "qazwsx";
    public String defaultPrefix = "th";
    public Set<String> stringsWithDefaultPrefix;
    public String regEx = "^" + defaultPrefix + "[a-z]*";

    @Before
    public void init() throws FileNotFoundException {
        rWayTrie = new RWayTrie();
        stringsWithDefaultPrefix = new HashSet<String>();

        File file = new File(fileName);
        Scanner input= new Scanner(file);

        String nextWord = input.nextLine();
        fileWordCount = Integer.parseInt(nextWord);

        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher;

        while(input.hasNext()) {
            input.next();
            nextWord = input.next();
            matcher = pattern.matcher(nextWord);
            if (matcher.matches()) {
                stringsWithDefaultPrefix.add(nextWord);
            }
            rWayTrie.add(new Tuple<Integer>(nextWord, nextWord.length()));
            input.nextLine();
        }
    }

    @Test
    public void testSize() {
        assertEquals("Check size", fileWordCount, rWayTrie.size());
    }

    @Test
    public void testContains() {
        assertTrue("Check contains 'word'", rWayTrie.contains("word"));
        assertFalse("Check contains 'qazwsx'", rWayTrie.contains("qazwsx"));
    }

    @Test
    public void testDelete() {
        assertTrue("Check delete " + containsWord, rWayTrie.delete(containsWord));
        assertFalse("Check contains " + containsWord + " after delete", rWayTrie.contains(containsWord));
        assertEquals("Check size after delete", fileWordCount - 1, rWayTrie.size());
        assertFalse("Check delete " + notContainsWord, rWayTrie.delete(notContainsWord));
    }

    @Test
    public void testAdd() {
        rWayTrie.add(new Tuple<Integer>(notContainsWord, notContainsWord.length()));
        assertEquals("Check size after add " + notContainsWord, fileWordCount + 1, rWayTrie.size());
        assertTrue("Check contains after add", rWayTrie.contains(notContainsWord));
    }

    @Test
    public void testWordWithPrefixWithDefaultPostfixLength() {
        Iterable<String> words = rWayTrie.wordsWithPrefix(defaultPrefix);
        for (String str : words) {
            assertTrue("Check " + str + " in set",stringsWithDefaultPrefix.contains(str));
            stringsWithDefaultPrefix.remove(str);
        }
        assertTrue("Check empty set after all returned words was removed", stringsWithDefaultPrefix.isEmpty());
    }
}
