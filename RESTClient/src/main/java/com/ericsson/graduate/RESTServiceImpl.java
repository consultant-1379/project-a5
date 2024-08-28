package com.ericsson.graduate;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class RESTServiceImpl implements RESTService{

    @Override
    public List<Commit> getAllCommits(LocalDateTime from, LocalDateTime to) {

        RestTemplate restTemplate = new RestTemplate();

        ParameterizedTypeReference<List<Commit>> responseType =
                new ParameterizedTypeReference<List<Commit>>() {};
        ResponseEntity<List<Commit>> response = restTemplate.exchange("http://restservice:8080" + "/commits" + "?from=" + from + "&to=" + to,
                HttpMethod.GET, null, responseType);

        return response.getBody();
    }

    @Override
    public List<CommittedFile> getAllFiles(List<Commit> commits) {
        List<CommittedFile> committedFiles = new ArrayList<>();
        for (Commit c : commits) {
            committedFiles = c.getChangedFiles();
        }
        return  committedFiles;
    }
}
