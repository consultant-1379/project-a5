package com.ericsson.Parser;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SchedulerTest {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private GitCommitRepository gitCommitRepository;

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
    void testValidDirPath() {
        String dirPath = "./src/test/resources/";
        scheduler.setDirPath(dirPath);
        assertEquals(dirPath, scheduler.getDirPath());

        String fileName = "gitStats.csv";
        scheduler.setFileName(fileName);
        assertEquals(fileName, scheduler.getFileName());

        scheduler.update();

        int count = 0;
        for(GitCommit gitCommit : gitCommitRepository.findAll())
            count++;
        assertThat(count, greaterThan(0));
    }

    @Test
    void testInvalidDirPath()
    {
        String invalidDirPath = "/invalid/path/";
        scheduler.setFileName(invalidDirPath);

        assertThrows(InvalidPathException.class, () -> scheduler.execute());
    }

    @Test
    void testInvalidFileName()
    {
        String dirPath = "./src/test/resources/";
        scheduler.setDirPath(dirPath);
        String fileName = "invalidFileName.csv";
        scheduler.setFileName(fileName);

        assertThrows(NoSuchFileException.class, () -> scheduler.execute());
    }

    @Test
    void testInitialize()
    {
        String dirPath = "./src/test/resources/";
        scheduler.setDirPath(dirPath);
        String fileName = "gitStats.csv";
        scheduler.setFileName(fileName);

        scheduler.initialize();
        int count = 0;
        for(GitCommit gitCommit : gitCommitRepository.findAll())
            count++;
        assertThat(count, greaterThan(0));
    }
}
