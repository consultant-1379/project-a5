package com.ericsson.graduate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.List;

public class Commit {
    private String hash;

    @JsonDeserialize(using = OffsetDeserializer.class)
    private LocalDateTime timestamp;
    private List<CommittedFile> changedFiles;

    private int churn;
    private int totalAdded;
    private int totalRemoved;
    private int totalHunks;

    public Commit(){

    }

    public Commit(String hash, LocalDateTime timestamp, List<CommittedFile> changedFiles, int churn, int totalAdded, int totalRemoved, int totalHunks) {
        this.hash = hash;
        this.timestamp = timestamp;
        this.changedFiles = changedFiles;
        this.churn = churn;
        this.totalAdded = totalAdded;
        this.totalRemoved = totalRemoved;
        this.totalHunks = totalHunks;
    }

    public String getHash() {
        return hash;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<CommittedFile> getChangedFiles() {
        return changedFiles;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setChangedFiles(List<CommittedFile> changedFiles) {
        this.changedFiles = changedFiles;
    }

    public int getChurn() {
        return churn;
    }

    public void setChurn(int churn) {
        this.churn = churn;
    }

    public int getTotalAdded() {
        return totalAdded;
    }

    public void setTotalAdded(int totalAdded) {
        this.totalAdded = totalAdded;
    }

    public int getTotalRemoved() {
        return totalRemoved;
    }

    public void setTotalRemoved(int totalRemoved) {
        this.totalRemoved = totalRemoved;
    }

    public int getTotalHunks() {
        return totalHunks;
    }

    public void setTotalHunks(int totalHunks) {
        this.totalHunks = totalHunks;
    }
}
