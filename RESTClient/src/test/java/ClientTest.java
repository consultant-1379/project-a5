import com.ericsson.graduate.Client;
import com.ericsson.graduate.RESTService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Client.class)
@AutoConfigureMockMvc
@ActiveProfiles({"dev"})
public class ClientTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RESTService restService;

    @Test
    void testIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("index"));
    }

    @Test
    void testPostTimeRangeGoodInput() throws Exception {
        mockMvc.perform(get("/postTimeRange")
                .param("from", "2020-10-16")
                .param("to", "2020-10-16"))
                .andExpect(view().name("commits"));
    }

    @Test
    void testPostTimeRangeBadInput() throws Exception {
        assertThatThrownBy(() -> mockMvc.perform(get("/postTimeRange")
                                            .param("from", "bad")
                                            .param("to", "input"))
                                            .andExpect(status().isOk())).hasCause(new DateTimeParseException("Text 'bad' could not be parsed at index 0", "bad", 0));
    }

}
