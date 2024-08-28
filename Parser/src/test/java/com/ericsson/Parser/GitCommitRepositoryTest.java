package com.ericsson.Parser;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class GitCommitRepositoryTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GitCommitRepository repository;

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
        entityManager.persist(new GitCommit("ff05689e52a9028b6914acb1bbf4aff139812b29",
                LocalDateTime.parse("2020-08-04T17:31:31+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
        entityManager.persist(new GitCommit("e871346bc854144703b91e38137f31db97b687f8",
                LocalDateTime.parse("2020-08-04T17:31:38+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
        entityManager.persist(new GitCommit("570449410775794ca3f3d89b965347f3bfc01420",
                LocalDateTime.parse("2020-10-12T11:39:31+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
        int count = 0;
        for(GitCommit gitCommit : repository.findAll())
            count++;
        assertEquals(3, count);
    }

    @Test
    void testFindById()
    {
        String hash = "ff05689e52a9028b6914acb1bbf4aff139812b29";
        LocalDateTime timestamp = LocalDateTime.parse("2020-08-04T17:31:31+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        entityManager.persist(new GitCommit(hash, timestamp));
        Optional<GitCommit> gitCommit = repository.findById(hash);
        assertTrue(gitCommit.isPresent());
    }

    @Test
    void testGetters()
    {
        String hash = "ff05689e52a9028b6914acb1bbf4aff139812b29";
        LocalDateTime timestamp = LocalDateTime.parse("2020-08-04T17:31:31+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        entityManager.persist(new GitCommit(hash, timestamp));
        Optional<GitCommit> gitCommit = repository.findById(hash);
        assert(gitCommit.isPresent());
        GitCommit gC = gitCommit.get();
        assertEquals(hash, gC.getHash());
        assertEquals(timestamp, gC.getTimestamp());
    }

    @Test
    void testSetters()
    {
        String hash = "ff05689e52a9028b6914acb1bbf4aff139812b29";
        LocalDateTime timestamp = LocalDateTime.parse("2020-08-04T17:31:31+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        GitCommit gitCommit = new GitCommit(hash, timestamp);
        LocalDateTime newTimestamp = LocalDateTime.parse("2020-08-04T17:31:38+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        gitCommit.setTimestamp(newTimestamp);
        entityManager.persist(gitCommit);
        Optional<GitCommit> optionalGitCommit = repository.findById(hash);
        assert(optionalGitCommit.isPresent());
        GitCommit gC = optionalGitCommit.get();
        assertEquals(newTimestamp, gC.getTimestamp());
    }
}
