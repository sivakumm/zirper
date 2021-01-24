package ch.fhnw.webfr.sivakumm.twitter.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ch.fhnw.webfr.sivakumm.twitter.domain.Tweet;
import ch.fhnw.webfr.sivakumm.twitter.persistence.TweetRepository;
import ch.fhnw.webfr.sivakumm.twitter.util.TweetInitializer;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class TwitterServlet extends HttpServlet {

    private TweetRepository tweetRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        tweetRepository = new TweetInitializer().initRepoWithTestData();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tweet> tweets = tweetRepository.findAll();

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append("<html><head><title>Twitter</title></head><body><h1>Twitter</h1><br />");
        for (Tweet tweet : tweets) {
            writer.append("<div>[" + tweet.getDate().toString() + "]&nbsp;<b>" + tweet.getUsername() + ":</b>&nbsp;<i>" + tweet.getTweet().substring(0, 20));
            if (tweet.getTweet().length() >= 20) {
                writer.append("...&nbsp;<a href='" + req.getContextPath() + "/" + tweet.getId() + "'>read more</a>");
            }
            writer.append("</i></div>");
        }
        writer.append("</body></html>");
    }
}
