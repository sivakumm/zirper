package ch.fhnw.webfr.sivakumm.zirper.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.fhnw.webfr.sivakumm.zirper.domain.Zirp;

public interface ZirpRepository extends MongoRepository<Zirp, String>  {
    List<Zirp> findByUsername(String username);
}
