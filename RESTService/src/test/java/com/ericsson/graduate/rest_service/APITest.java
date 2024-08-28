package com.ericsson.graduate.rest_service;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class APITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testReceive400ErrorWithNoInput() throws Exception {
        mockMvc.perform(get("/commits"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testReceive400ErrorWithBadInput() throws Exception {
        mockMvc.perform((get("/commits")
                .param("from", "hello")
                .param("to", "bbb")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testReceive200StatusWithCorrectInput() throws Exception {
        mockMvc.perform((get("/commits")
                .param("from", "2020-10-13T00:00:00")
                .param("to", "2020-10-15T00:00:00")))
                .andExpect(status().isOk()).andDo(print());
    }


    @Test
    void testReceiveCorrectResultsWithCorrectDateRange() throws Exception {
        mockMvc.perform((get("/commits")
                .param("from", "2020-10-13T00:00")
                .param("to", "2020-10-16T00:00")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*.hash", Matchers.containsInAnyOrder("x", "b", "c")));
    }

    @Test
    void testReceiveCorrectlySummedLinesAdded() throws Exception {
        mockMvc.perform((get("/commits")
                .param("from", "2020-10-13T00:00")
                .param("to", "2020-10-16T00:00")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].totalAdded").value(220));
    }

}
