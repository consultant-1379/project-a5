package com.ericsson.Parser;

import javax.persistence.*;

@Entity
public class CommitFile
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String path;
    private int linesAdded;
    private int linesRemoved;
    private int contributorCount;
    private int minorContributorCount;
    private int linesByHighestContributor;
    private int hunk;
    private String commitHash;

    public int getId() {
        return id;
    }


    public String getPath() {
        return path;
    }

    void setPath(String path) {
        this.path = path;
    }

    public int getLinesAdded() {
        return linesAdded;
    }

    void setLinesAdded(int linesAdded) {
        this.linesAdded = linesAdded;
    }

    public int getLinesRemoved() {
        return linesRemoved;
    }

    void setLinesRemoved(int linesRemoved) {
        this.linesRemoved = linesRemoved;
    }

    public int getContributorCount() {
        return contributorCount;
    }

    void setContributorCount(int contributorCount) {
        this.contributorCount = contributorCount;
    }

    public int getMinorContributorCount() {
        return minorContributorCount;
    }

    void setMinorContributorCount(int minorContributorCount) {
        this.minorContributorCount = minorContributorCount;
    }

    public int getLinesByHighestContributor() {
        return linesByHighestContributor;
    }

    void setLinesByHighestContributor(int linesByHighestContributor) {
        this.linesByHighestContributor = linesByHighestContributor;
    }

    public int getHunk() {
        return hunk;
    }

    void setHunk(int hunk) {
        this.hunk = hunk;
    }

    public String getCommitHash() {
        return commitHash;
    }

    void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }
}
