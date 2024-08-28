package com.ericsson.graduate;

import java.time.LocalDateTime;
import java.util.List;

public interface RESTService {
    public List<Commit> getAllCommits(LocalDateTime from, LocalDateTime to);
    public List<CommittedFile> getAllFiles(List<Commit> commits);
}
