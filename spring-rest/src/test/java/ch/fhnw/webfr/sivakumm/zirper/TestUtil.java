package ch.fhnw.webfr.sivakumm.zirper;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.fhnw.webfr.sivakumm.zirper.domain.Zirp;

public class TestUtil {

    public static class ZirpBuilder {
        private Zirp zirp;

        public ZirpBuilder(String id) {
            zirp = new Zirp();
            zirp.setId(id);
        }

        public ZirpBuilder username(String username) {
            zirp.setUsername(username);
            return this;
        }

        public ZirpBuilder zirp(String zirpMsg) {
            zirp.setZirp(zirpMsg);
            return this;
        }

        public ZirpBuilder date(Date date) {
            zirp.setDate(date);
            return this;
        }

        public Zirp build() {
            return zirp;
        }
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }    
}
