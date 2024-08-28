package com.ericsson.graduate.rest_service.commit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

@RestController
public class CommitController {

    @Autowired
    private GitCommitRepository repository;

    @GetMapping("/commits")
    public ResponseEntity<List<Commit>> getCommits(@RequestParam("from") String from, @RequestParam("to") String to) {

        try {
            LocalDateTime fromDate = LocalDateTime.parse(from);
            LocalDateTime toDate = LocalDateTime.parse(to);

            Timestamp f = Timestamp.from(fromDate.toInstant(ZoneOffset.UTC));
            Timestamp t = Timestamp.from(toDate.toInstant(ZoneOffset.UTC));

            return ResponseEntity.ok(repository.findByTimestampBetween(f, t));
        } catch (DateTimeParseException ex) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

    }
}
