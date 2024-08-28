package com.ericsson.graduate.rest_service.commit;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="git_commit")
public class Commit implements Serializable {
    @Id
    @Type(type="java.lang.String")
    @Column(length = 64)
    private String hash;

    @Column(name="timestamp")
    private Timestamp timestamp;

    @OneToMany(targetEntity = CommittedFile.class)
    @JoinColumn(name="commit_hash", referencedColumnName = "hash")
    @Fetch(FetchMode.JOIN)
    private Set<CommittedFile> changedFiles;

    @Formula("(select sum(f.lines_added - f.lines_removed) from commit_file f where f.commit_hash = hash group by f.commit_hash)")
    private Integer churn;

    @Formula("(select sum(f.lines_added) from commit_file f where f.commit_hash = hash group by f.commit_hash)")
    private Integer totalAdded;

    @Formula("(select sum(f.lines_removed) from commit_file f where f.commit_hash = hash group by f.commit_hash)")
    private Integer totalRemoved;

    @Formula("(select sum(f.hunk) from commit_file f where f.commit_hash = hash group by f.commit_hash)")
    private Integer totalHunks;

    public Commit() {
        // Empty constructor  --- object initialisation done by Spring/JPA
    }

    public String getHash() {
        return hash;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public Set<CommittedFile> getChangedFiles() {
        return changedFiles;
    }

    public Integer getChurn() {
        return churn;
    }

    public Integer getTotalAdded() {
        return totalAdded;
    }

    public Integer getTotalRemoved() {
        return totalRemoved;
    }

    public Integer getTotalHunks() {
        return totalHunks;
    }

}
