package com.ericsson.graduate.rest_service.commit;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Lazy
public interface GitCommitRepository extends org.springframework.data.repository.Repository<Commit, Integer> {
    List<Commit> findByTimestampBetween(Timestamp from, Timestamp to);
}
