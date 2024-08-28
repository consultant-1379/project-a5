package com.ericsson.Parser;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class GitCommit
{
    @Id
    @Column(length = 64)
    private String hash;
    private LocalDateTime timestamp;

    private GitCommit()
    { }

    public GitCommit(String hash, LocalDateTime timestamp)
    {
        this.setHash(hash);
        this.setTimestamp(timestamp);
    }

    public String getHash() {
        return hash;
    }

    private void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
