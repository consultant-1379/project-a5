package com.ericsson.Parser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTests {
    @Autowired
    private MockMvc mock;

    @Test
    void testAllGitCommits() throws Exception
    {
        mock.perform(get("/all_commits")).andExpect(status().isOk());
    }

    @Test
    void testAllCommitFiles() throws Exception
    {
        mock.perform(get("/all_files")).andExpect(status().isOk());
    }
}
