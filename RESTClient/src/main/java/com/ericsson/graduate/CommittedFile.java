package com.ericsson.graduate;

public class CommittedFile {
    private int id;
    private String path;
    private int linesRemoved;
    private int linesAdded;
    private int contributorCount;
    private int minorContributorCount;
    private int linesByHighestContributor;
    private int hunk;
    private String commitHash;

    public CommittedFile(){

    }

    public CommittedFile(int id, String path, int linesRemoved, int linesAdded, int contributorCount, int minorContributorCount, int linesByHighestContributor, int hunks, String commitHash) {
        this.id = id;
        this.path = path;
        this.linesRemoved = linesRemoved;
        this.linesAdded = linesAdded;
        this.contributorCount = contributorCount;
        this.minorContributorCount = minorContributorCount;
        this.linesByHighestContributor = linesByHighestContributor;
        this.hunk = hunks;
        this.commitHash = commitHash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLinesRemoved() {
        return linesRemoved;
    }

    public void setLinesRemoved(int linesRemoved) {
        this.linesRemoved = linesRemoved;
    }

    public int getLinesAdded() {
        return linesAdded;
    }

    public void setLinesAdded(int linesAdded) {
        this.linesAdded = linesAdded;
    }

    public int getContributorCount() {
        return contributorCount;
    }

    public void setContributorCount(int contributorCount) {
        this.contributorCount = contributorCount;
    }

    public int getMinorContributorCount() {
        return minorContributorCount;
    }

    public void setMinorContributorCount(int minorContributorCount) {
        this.minorContributorCount = minorContributorCount;
    }

    public int getLinesByHighestContributor() {
        return linesByHighestContributor;
    }

    public void setLinesByHighestContributor(int linesByHighestContributor) {
        this.linesByHighestContributor = linesByHighestContributor;
    }

    public int getHunk() {
        return hunk;
    }

    public void setHunk(int hunk) {
        this.hunk = hunk;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }
}