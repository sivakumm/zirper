package ch.fhnw.webfr.sivakumm.twitter.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

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
        dispatchRequest(req, resp);
    }

    private void dispatchRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getRequestURI().matches(req.getContextPath() + "/\\d")) {
            showDetailedTweetPage(req, resp);
        } else {
            showOverviewPage(req, resp);
        }
    }

    private void showOverviewPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Tweet> tweets = tweetRepository.findAll();

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append("<h1>Twitter</h1><br />");
        for (Tweet tweet : tweets) {
            writer.append("<div>[" + tweet.getDate().toString() + "]&nbsp;<b>" + tweet.getUsername() + ":</b>&nbsp;<i>" + tweet.getTweet().substring(0, 20));
            if (tweet.getTweet().length() >= 20) {
                writer.append("...&nbsp;<a href='" + req.getContextPath() + "/" + tweet.getId() + "'>read more</a>");
            }
            writer.append("</i></div>");
        }
    }

    private void showDetailedTweetPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] pathElements = req.getRequestURI().split("/");
        Optional<Tweet> tweet = tweetRepository.findById(Integer.parseInt(pathElements[pathElements.length - 1]));

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        if (tweet.isEmpty()) {
            writer.append("<h1>Tweet not found</h1>");
        } else {
            writer.append("<h3>" + tweet.get().getUsername() + " @ " + tweet.get().getDate() + "</h3>");
            writer.append("<p>" + tweet.get().getTweet() + "</p>");
            writer.append("<br /><a href='" + req.getContextPath() + "'>Back to all Tweets</a>");
        }
    }
}
