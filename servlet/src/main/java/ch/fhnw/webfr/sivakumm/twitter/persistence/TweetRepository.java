package ch.fhnw.webfr.sivakumm.twitter.persistence;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ch.fhnw.webfr.sivakumm.twitter.domain.Tweet;

public class TweetRepository {
    
    private final List<Tweet> tweets = new ArrayList<>();
    private static SoftReference<TweetRepository> instance = null;

    private TweetRepository() { }

    public static synchronized TweetRepository getInstance() {
        TweetRepository repo = instance == null ? null : instance.get();
        if (repo == null) {
            repo = new TweetRepository();
            instance = new SoftReference<TweetRepository>(repo);
        }
        return repo;
    }

    public synchronized Tweet save(Tweet tweet) {
        tweet.setId(tweets.size());
        tweets.add(tweet);
        return tweet;
    }

    public List<Tweet> findAll() {
        return tweets;
    }

    public Optional<Tweet> findById(int id) {
        return tweets.stream().filter(tweet -> tweet.getId() == id).findFirst();
    }

    public void clear() {
        tweets.clear();
    }
}
