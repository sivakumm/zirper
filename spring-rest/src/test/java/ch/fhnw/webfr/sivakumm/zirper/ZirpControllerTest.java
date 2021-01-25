package ch.fhnw.webfr.sivakumm.zirper;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import ch.fhnw.webfr.sivakumm.zirper.TestUtil.ZirpBuilder;
import ch.fhnw.webfr.sivakumm.zirper.domain.Zirp;
import ch.fhnw.webfr.sivakumm.zirper.persistence.ZirpRepository;
import ch.fhnw.webfr.sivakumm.zirper.web.ZirpController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(ZirpController.class)
public class ZirpControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ZirpRepository zirpRepository;

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

    @Test
    public void get_ZirpById_ShouldReturnOK() throws Exception {
        int idx = 0;
        Zirp zirp = zirps.get(idx);
        when(zirpRepository.findById(String.valueOf(idx))).thenReturn(Optional.of(zirp));

        mockMvc.perform(get("/zirps/" + idx))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(zirp.getId())))
            .andExpect(jsonPath("$.username", is(zirp.getUsername())))
            .andExpect(jsonPath("$.zirp", is(zirp.getZirp())));

        verify(zirpRepository).findById(String.valueOf(idx));
    }

    @Test
    public void get_ZirpById_ShouldReturnNotFound() throws Exception {
        when(zirpRepository.findById("0")).thenReturn(Optional.empty());

        mockMvc.perform(get("/zirps/0")).andExpect(status().isNotFound());

        verify(zirpRepository).findById("0");
    }

    @Test
    public void post_Zirps_ShouldReturnCreated() throws Exception {
        Zirp zirp = zirps.get(0);
        when(zirpRepository.save(ArgumentMatchers.any(Zirp.class))).thenReturn(zirp);

        mockMvc.perform(post("/zirps").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zirp)))
            .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(zirp.getId())))
                .andExpect(jsonPath("$.username", is(zirp.getUsername())))
                .andExpect(jsonPath("$.zirp", is(zirp.getZirp())));

        verify(zirpRepository).save(ArgumentMatchers.any(Zirp.class));
    }

    @Test
    public void post_Zirps_UsernameShort_ShouldReturnPreconditionFailed() throws Exception {
        Zirp zirp = new ZirpBuilder("0").username("0").zirp("This is a zirp").date(new Date()).build();

        mockMvc.perform(post("/zirps").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zirp)))
                .andExpect(status().isPreconditionFailed());

        verify(zirpRepository, times(0)).save(ArgumentMatchers.any(Zirp.class));
    }

    @Test
    public void post_Zirps_UsernameLong_ShouldReturnPreconditionFailed() throws Exception {
        Zirp zirp = new ZirpBuilder("0").username("1234567890123456").zirp("This is a zirp").date(new Date()).build();

        mockMvc.perform(post("/zirps").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zirp)))
                .andExpect(status().isPreconditionFailed());

        verify(zirpRepository, times(0)).save(ArgumentMatchers.any(Zirp.class));
    }

    @Test
    public void post_Zirps_ZirpShort_ShouldReturnPreconditionFailed() throws Exception {
        Zirp zirp = new ZirpBuilder("0").username("user").zirp("").date(new Date()).build();

        mockMvc.perform(post("/zirps").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zirp)))
                .andExpect(status().isPreconditionFailed());

        verify(zirpRepository, times(0)).save(ArgumentMatchers.any(Zirp.class));
    }

    @Test
    public void post_Zirps_ZirpLong_ShouldReturnPreconditionFailed() throws Exception {
        String zirpStr = "";
        for (int i = 0; i < 281; i++) { zirpStr += "a"; }
        Zirp zirp = new ZirpBuilder("0").username("user").zirp(zirpStr).date(new Date()).build();

        mockMvc.perform(post("/zirps").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zirp)))
                .andExpect(status().isPreconditionFailed());

        verify(zirpRepository, times(0)).save(ArgumentMatchers.any(Zirp.class));
    }

    @Test
    public void post_Zirps_ZirpMax_ShouldReturnCreated() throws Exception {
        String zirpStr = "";
        for (int i = 0; i < 280; i++) { zirpStr += "a"; }
        Zirp zirp = zirps.get(0);
        zirp.setZirp(zirpStr);
        when(zirpRepository.save(ArgumentMatchers.any(Zirp.class))).thenReturn(zirp);

        mockMvc.perform(post("/zirps").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zirp)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(zirp.getId())))
                .andExpect(jsonPath("$.username", is(zirp.getUsername())))
                .andExpect(jsonPath("$.zirp", is(zirp.getZirp())));

        verify(zirpRepository).save(ArgumentMatchers.any(Zirp.class));
    }
}
