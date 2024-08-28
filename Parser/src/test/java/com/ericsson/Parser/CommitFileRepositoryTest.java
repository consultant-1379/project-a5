package com.ericsson.Parser;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CommitFileRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CommitFileRepository repository;

    @BeforeAll
    static void setup()
    {
        Scheduler.isTesting=true;
    }

    @AfterAll
    static void tearDown()
    {
        Scheduler.isTesting=false;
    }

    @Test
    void testInsertion()
    {
        CommitFile commitFile1 = new CommitFile();
        commitFile1.setPath("RESTClient\\src\\main\\java\\com\\ericsson\\graduate\\Client.java");
        commitFile1.setLinesAdded(1);
        commitFile1.setLinesRemoved(5);
        commitFile1.setContributorCount(1);
        commitFile1.setMinorContributorCount(0);
        commitFile1.setLinesByHighestContributor(100);
        commitFile1.setHunk(2);
        commitFile1.setCommitHash("e871346bc854144703b91e38137f31db97b687f8");

        CommitFile commitFile2 = new CommitFile();
        commitFile2.setPath("PythonScript\\getStats.py");
        commitFile2.setLinesAdded(62);
        commitFile2.setLinesRemoved(49);
        commitFile2.setContributorCount(1);
        commitFile2.setMinorContributorCount(0);
        commitFile2.setLinesByHighestContributor(100);
        commitFile2.setHunk(12);
        commitFile2.setCommitHash("570449410775794ca3f3d89b965347f3bfc01420");

        entityManager.persist(commitFile1);
        entityManager.persist(commitFile2);
        int count = 0;
        for(CommitFile gitCommit : repository.findAll())
            count++;
        assertEquals(2, count);
    }

    @Test
    void testGetters()
    {
        String path = "PythonScript\\getStats.py";
        int linesAdded = 62;
        int linesRemoved = 49;
        int contributorCount = 1;
        int minorContributorCount = 0;
        int linesByHighestContributor = 100;
        int hunkCount = 12;
        String commitHash = "e871346bc854144703b91e38137f31db97b687f8";
        CommitFile commitFile = new CommitFile();
        commitFile.setPath(path);
        commitFile.setLinesAdded(linesAdded);
        commitFile.setLinesRemoved(linesRemoved);
        commitFile.setContributorCount(contributorCount);
        commitFile.setMinorContributorCount(minorContributorCount);
        commitFile.setLinesByHighestContributor(linesByHighestContributor);
        commitFile.setHunk(hunkCount);
        commitFile.setCommitHash(commitHash);
        entityManager.persist(commitFile);

        Optional<CommitFile> optionalCommitFile = repository.findById(1);
        assert(optionalCommitFile.isPresent());

        CommitFile cF = optionalCommitFile.get();
        assertEquals(1, cF.getId());
        assertEquals(path, cF.getPath());
        assertEquals(commitHash, cF.getCommitHash());
        assertEquals(linesAdded, cF.getLinesAdded());
        assertEquals(linesRemoved, cF.getLinesRemoved());
        assertEquals(contributorCount, cF.getContributorCount());
        assertEquals(minorContributorCount, cF.getMinorContributorCount());
        assertEquals(linesByHighestContributor, cF.getLinesByHighestContributor());
        assertEquals(hunkCount, cF.getHunk());
    }
}
