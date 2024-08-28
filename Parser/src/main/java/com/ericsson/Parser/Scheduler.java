package com.ericsson.Parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
class Scheduler
{
    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private GitCommitRepository gitCommitRepository;

    @Autowired
    private CommitFileRepository commitFileRepository;

    private String dirPath;
    private String fileName;

    static boolean isTesting = false;
    private static boolean initialized = false;

    Scheduler()
    {
        setup();
    }

    private void setup()
    {
        if(!isTesting) {
            new Thread(() -> {
                try {
                    Thread.sleep(20_000);
                    initialize();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }

    String getFileName()
    {
        return fileName;
    }

    void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    String getDirPath()
    {
        return dirPath;
    }

    void setDirPath(String dirPath)
    {
        this.dirPath = dirPath;
    }

    void initialize()
    {
        log.info("INITIALIZING DB...");
        if(!isTesting) {
            dirPath = "/initialData/";
            fileName = "initialData.csv";
        }
        try
        {
            execute();
            initialized = true;
        }
        catch (NoSuchFileException e)
        {
            log.error(e.getMessage());
        }
        finally
        {
            dirPath = null;
            fileName = null;
        }

    }

    @Scheduled(cron = "0 0/5 * * * *")
    void update()
    {
        log.info("UPDATING...");
        if(!initialized && !isTesting)
        {
            initialize();
        }
        try
        {
            execute();
        }
        catch (NoSuchFileException | InvalidPathException ex)
        {
            log.error(ex.getMessage());
        }
    }

    void execute() throws NoSuchFileException
    {
        log.info("EXECUTING...");
        if(dirPath == null)
            dirPath = "/data/";
        File dir = new File(dirPath);
        if(!dir.exists())
        {
            throw new InvalidPathException(dirPath, "Path does not exist");
        }

        if(fileName == null)
        {
            fileName = "gitStats.csv";
        }

        File file = new File(dirPath +fileName);
        if(!file.exists())
        {
            throw new NoSuchFileException(file.getName());
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(dirPath + fileName)))
        {
            String line;
            while((line = reader.readLine()) != null)
            {
                String [] elements = line.split(",");
                parseElements(elements);
            }
        }
        catch (IOException e)
        {
            log.error(e.getMessage());
        }
    }

    private void parseElements(String [] elements)
    {
        int index = 0;
        String hash = elements[index++];
        String datetimeStr = elements[index++];
        if(gitCommitRepository.findById(hash).isPresent())
        {
            log.info("Commit with hash {} is already present, skipping.", hash);
        }
        else
        {
            GitCommit gitCommit = new GitCommit(hash, LocalDateTime.parse(datetimeStr.replace(' ', 'T'), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
            gitCommitRepository.save(gitCommit);

            while (index < elements.length)
            {
                try
                {
                    if(index + 8 < elements.length) {
                        CommitFile commitFile = new CommitFile();
                        commitFile.setCommitHash(hash);
                        commitFile.setPath(elements[index++]);
                        commitFile.setLinesAdded((int) Double.parseDouble(elements[index++]));
                        commitFile.setLinesRemoved((int) Double.parseDouble(elements[index++]));
                        commitFile.setContributorCount((int) Double.parseDouble(elements[index++]));
                        commitFile.setMinorContributorCount((int) Double.parseDouble(elements[index++]));
                        commitFile.setLinesByHighestContributor((int) Double.parseDouble(elements[index++]));
                        commitFile.setHunk((int) Double.parseDouble(elements[index++]));
                        commitFileRepository.save(commitFile);
                    }
                    else {
                        break;
                    }
                } catch (NumberFormatException ex)
                {
                    log.error(ex.getMessage());
                }
            }
            log.info("Commit, {},added to DB.", hash);
        }
    }
}