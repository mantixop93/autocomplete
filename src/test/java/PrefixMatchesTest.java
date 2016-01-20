import com.lab.autocomplete.PrefixMatches;
import com.lab.autocomplete.RWayTrie;
import com.lab.autocomplete.Tuple;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Iterator;

import static org.mockito.Mockito.*;

/**
 * Created by Mantixop on 1/20/16.
 */

public class PrefixMatchesTest {
    @Mock
    private RWayTrie<Integer> rWayTrie;

    @InjectMocks
    private PrefixMatches prefixMatches = new PrefixMatches();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSize() {
        prefixMatches.size();
        verify(rWayTrie).size();
    }

    @Test
    public void testContains() {
        prefixMatches.contains("test");
        verify(rWayTrie).contains("test");
    }

    @Test
    public void testDelete() {
        prefixMatches.delete("test");
        verify(rWayTrie).delete("test");
    }

    @Test
    public void testAddWord() {
        prefixMatches.add("test");
        verify(rWayTrie).add(new Tuple<Integer>("test", "test".length()));
    }

    @Test
    public void testAddSeveralWordsInSrtring() {
        prefixMatches.add("test testt testtt");
        verify(rWayTrie, times(1)).add(new Tuple<Integer>("test", "test".length()));
        verify(rWayTrie, times(1)).add(new Tuple<Integer>("testt", "testt".length()));
        verify(rWayTrie, times(1)).add(new Tuple<Integer>("testtt", "testtt".length()));
    }

    @Test
    public void testAddArray() {
        prefixMatches.add(new String[]{"test", "testt", "testtt"});
        verify(rWayTrie, times(1)).add(new Tuple<Integer>("test", "test".length()));
        verify(rWayTrie, times(1)).add(new Tuple<Integer>("testt", "testt".length()));
        verify(rWayTrie, times(1)).add(new Tuple<Integer>("testtt", "testtt".length()));
    }

    @Test
    public void testAddShortWord() {
        prefixMatches.add("te");
        prefixMatches.add("t");
        prefixMatches.add("");
        verify(rWayTrie, never()).add(any(Tuple.class));
    }

    @Test
    public void testWordsWithPrefixWithShortPrefix() {
        prefixMatches.wordsWithPrefix("");
        prefixMatches.wordsWithPrefix("q");
        verify(rWayTrie, never()).wordsWithPrefix(anyString());
    }

    @Test
    public void testWordsWithPrefix() {
        when(rWayTrie.wordsWithPrefix("te")).thenReturn(Arrays.asList(new String[]{"te", "tes", "test", "testt"}));
        Iterator<String> iterator =  prefixMatches.wordsWithPrefix("te", 2).iterator();
        assertEquals("te", iterator.next());
        assertEquals(iterator.hasNext(), false);
    }

    @Test
    public void testWordsWithPrefixDefaultPostfixLength() {
        when(rWayTrie.wordsWithPrefix("te")).thenReturn(Arrays.asList(new String[]{"te", "tes", "test", "testt"}));
        Iterator<String> iterator =  prefixMatches.wordsWithPrefix("te").iterator();
        assertEquals("te", iterator.next());
        assertEquals("tes", iterator.next());
        assertEquals("test", iterator.next());
        assertEquals(iterator.hasNext(), false);
    }
}
