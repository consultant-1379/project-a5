import com.ericsson.graduate.Commit;
import com.ericsson.graduate.CommittedFile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = Commit.class)
public class CommitTest {

    List<CommittedFile> committedFileList = new ArrayList<>();

    Commit c = new Commit("hashTest", LocalDateTime.of(2020, 10, 15, 16, 30, 0), committedFileList, 0, 0, 0, 0);

    @Test
    void testGetHash(){
        assertEquals("hashTest", c.getHash());
    }

    @Test
    void testGetTimeStamp(){
        assertEquals(LocalDateTime.of(2020, 10, 15, 16, 30, 0), c.getTimestamp());
    }

    @Test
    void testGetChangedFiles(){
        assertEquals(committedFileList, c.getChangedFiles());
    }

    @Test
    void testSetHash(){
        String expected = "hashTestSet";
        c.setHash(expected);
        assertEquals(expected, c.getHash());
    }

    @Test
    void testSetTimeStamp(){
        LocalDateTime expected = LocalDateTime.of(2020, 11, 15, 16, 30, 0);
        c.setTimestamp(expected);
        assertEquals(expected, c.getTimestamp());
    }

    @Test
    void testSetChangedFiles(){
        List<CommittedFile> expected = new ArrayList<>();
        c.setChangedFiles(expected);
        assertEquals(expected, c.getChangedFiles());
    }

}
