import com.ericsson.graduate.Commit;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class JSONTest {

    @Test
    void testCanDeserializeCommitFromRESTJsonSample() throws IOException {
        final String sampleJson ="[{\"hash\":\"a\",\"timestamp\":\"2020-10-13T23:00:00.000+00:00\",\"changedFiles\":[{\"path\":\"src/file_one.c\",\"linesRemoved\":10,\"linesAdded\":100,\"contributorCount\":1,\"minorContributorCount\":0,\"linesByHighestContributor\":90,\"hunk\":1,\"commitHash\":\"a\"},{\"path\":\"src/file_three.c\",\"linesRemoved\":10,\"linesAdded\":100,\"contributorCount\":1,\"minorContributorCount\":0,\"linesByHighestContributor\":90,\"hunk\":1,\"commitHash\":\"a\"},{\"path\":\"src/file_two.c\",\"linesRemoved\":80,\"linesAdded\":20,\"contributorCount\":1,\"minorContributorCount\":0,\"linesByHighestContributor\":90,\"hunk\":1,\"commitHash\":\"a\"}]},{\"hash\":\"b\",\"timestamp\":\"2020-10-14T23:00:00.000+00:00\",\"changedFiles\":[{\"path\":\"src/file_one.c\",\"linesRemoved\":10,\"linesAdded\":100,\"contributorCount\":1,\"minorContributorCount\":0,\"linesByHighestContributor\":90,\"hunk\":1,\"commitHash\":\"b\"}]},{\"hash\":\"d\",\"timestamp\":\"2020-10-13T23:00:00.000+00:00\",\"changedFiles\":[]}]";

        List<Commit> commits = new ObjectMapper().readValue(sampleJson.getBytes(), new TypeReference<List<Commit>>(){});
        assertTrue(true);

    }
}
