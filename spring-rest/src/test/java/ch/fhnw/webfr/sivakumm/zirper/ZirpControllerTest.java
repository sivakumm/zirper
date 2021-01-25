package ch.fhnw.webfr.sivakumm.zirper;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import ch.fhnw.webfr.sivakumm.zirper.TestUtil.ZirpBuilder;
import ch.fhnw.webfr.sivakumm.zirper.domain.Zirp;
import ch.fhnw.webfr.sivakumm.zirper.persistence.ZirpRepository;
import ch.fhnw.webfr.sivakumm.zirper.web.ZirpController;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ZirpController.class)
public class ZirpControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZirpRepository zirpRepository;

    private final List<Zirp> zirps = new ArrayList<>();
    private final List<String> usernames = List.of("testUser", "anotherTestUser");

    @BeforeEach
    public void setUp() {
        reset(zirpRepository);

        zirps.clear();
        for (int i = 0; i < 6; i++) {
            String username = usernames.get(i % 2);
            zirps.add(new ZirpBuilder(String.valueOf(i)).username(username).zirp("I am " + username + " (" + i + ")").build());
        }
    }

    @Test
    public void get_AllZirps_ShouldReturnOK() throws Exception {
        when(zirpRepository.findAll()).thenReturn(zirps);

        ResultActions result = mockMvc.perform(get("/zirps"));
        result.andExpect(status().isOk());
        for (int i = 0; i < zirps.size(); i++) {
            String username = usernames.get(i % 2);
            result.andExpect(jsonPath("$[" + i + "].id", is(String.valueOf(i))));
            result.andExpect(jsonPath("$[" + i + "].username", is(username)));
            result.andExpect(jsonPath("$[" + i + "].zirp", is("I am " + username + " (" + i + ")")));
        }

        verify(zirpRepository).findAll();
    }
}
