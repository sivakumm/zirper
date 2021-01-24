package ch.fhnw.webfr.sivakumm.twitter.util;

import ch.fhnw.webfr.sivakumm.twitter.domain.Tweet;
import ch.fhnw.webfr.sivakumm.twitter.persistence.TweetRepository;

public class TweetInitializer {

    public TweetRepository initRepoWithTestData() {
        TweetRepository repo = TweetRepository.getInstance();

        repo.save(new Tweet("FHNW HT", "Due to COVID-19, the exams in autmn semester 2020 will be skipped and every student just get a pass."));
        repo.save(new Tweet("FHNW webfr", "Dear students you now should have been informed that the exam would not be taken place."));
        repo.save(new Tweet("Donald Trump", "It is a conspiracy against me! I won the election!!! Make America Great Again!"));

        return repo;
    }
    
}
