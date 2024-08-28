import com.ericsson.graduate.CommittedFile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = CommittedFile.class)
public class CommittedFileTest {

    private static CommittedFile cf = new CommittedFile(1,"hello", 1, 2, 3, 4, 5, 6, "aaaa");

    @Test
    void testGetId(){
        assertEquals(1, cf.getId());
    }

    @Test
    void testGetPath(){
        assertEquals("hello", cf.getPath());
    }

    @Test
    void testGetLinesRemoved(){
        assertEquals(5, cf.getLinesRemoved());
    }

    @Test
    void testGetLinesAdded(){
        assertEquals(2, cf.getLinesAdded());
    }

    @Test
    void testGetContributorCount(){
        assertEquals(5, cf.getContributorCount());
    }

    @Test
    void testGetMinorContributorCount(){
        assertEquals(4, cf.getMinorContributorCount());
    }

    @Test
    void testGetLinesByHighest(){
        assertEquals(200, cf.getLinesByHighestContributor());
    }

    @Test
    void testGetHunksCount(){
        assertEquals(6, cf.getHunk());
    }

    @Test
    void testSetId(){
        cf.setId(1);
        assertEquals(1, cf.getId());
    }

    @Test
    void testSetPath(){
        cf.setPath("hello");
        assertEquals("hello", cf.getPath());
    }

    @Test
    void testSetLinesRemoved(){
        cf.setLinesRemoved(5);
        assertEquals(5, cf.getLinesRemoved());
    }

    @Test
    void testSetLinesAdded(){
        cf.setLinesAdded(5);
        assertEquals(5, cf.getLinesAdded());
    }

    @Test
    void testSetContributorCount(){
        cf.setContributorCount(5);
        assertEquals(5, cf.getContributorCount());
    }

    @Test
    void testSetMinorContributorCount(){
        cf.setMinorContributorCount(8);
        assertEquals(8, cf.getMinorContributorCount());
    }

    @Test
    void testSetLinesByHighestContributor(){
        cf.setLinesByHighestContributor(200);
        assertEquals(200, cf.getLinesByHighestContributor());
    }

    @Test
    void testSetHunks(){
        cf.setHunk(3);
        assertEquals(3, cf.getHunk());
    }
}
