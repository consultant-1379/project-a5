package com.ericsson.graduate.rest_service.commit;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="commit_file")
public class CommittedFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String path;

    @Column(name="lines_removed")
    private int linesRemoved;

    @Column(name="lines_added")
    private int linesAdded;

    @Column(name="contributor_count")
    private int contributorCount;

    @Column(name="minor_contributor_count")
    private int minorContributorCount;

    @Column(name="lines_by_highest_contributor")
    private int linesByHighestContributor;

    @Column(name="hunk")
    private int hunk;

    @Type(type="java.lang.String")
    @Column(name="commit_hash")
    private String commitHash;

    public CommittedFile() {
        // Empty constructor --- Spring/JPA performs initialisation
    }

    public String getPath() {
        return path;
    }

    public int getLinesRemoved() {
        return linesRemoved;
    }

    public int getLinesAdded() {
        return linesAdded;
    }

    public int getContributorCount() {
        return contributorCount;
    }

    public int getMinorContributorCount() {
        return minorContributorCount;
    }

    public int getLinesByHighestContributor() {
        return linesByHighestContributor;
    }

    public int getHunk() {
        return hunk;
    }

    public String getCommitHash() {
        return commitHash;
    }

}
