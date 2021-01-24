package ch.fhnw.webfr.sivakumm.zirper.util;

import ch.fhnw.webfr.sivakumm.zirper.domain.Zirp;
import ch.fhnw.webfr.sivakumm.zirper.persistence.ZirpRepository;

public class ZirpInitializer {

    public ZirpRepository initRepoWithTestData() {
        ZirpRepository repo = ZirpRepository.getInstance();

        repo.save(new Zirp("FHNW HT", "Due to COVID-19, the exams in autmn semester 2020 will be skipped and every student just get a pass."));
        repo.save(new Zirp("FHNW webfr", "Dear students you now should have been informed that the exam would not be taken place."));
        repo.save(new Zirp("Donald Trump", "It is a conspiracy against me! I won the election!!! Make America Great Again!"));

        return repo;
    }
    
}
