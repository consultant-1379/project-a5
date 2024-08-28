package com.ericsson.Parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController
{
    @Autowired
    private GitCommitRepository gitCommitRepository;

    @Autowired
    private CommitFileRepository commitFileRepository;

    @GetMapping(path="/all_commits")
    public @ResponseBody Iterable<GitCommit> getAllCommits()
    {
        return gitCommitRepository.findAll();
    }

    @GetMapping(path="/all_files")
    public @ResponseBody Iterable<CommitFile> getAllCommitFiles()
    {
        return commitFileRepository.findAll();
    }
}
