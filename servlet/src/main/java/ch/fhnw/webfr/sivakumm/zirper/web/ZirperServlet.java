package ch.fhnw.webfr.sivakumm.zirper.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import ch.fhnw.webfr.sivakumm.zirper.domain.Zirp;
import ch.fhnw.webfr.sivakumm.zirper.persistence.ZirpRepository;
import ch.fhnw.webfr.sivakumm.zirper.util.ZirpInitializer;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ZirperServlet extends HttpServlet {

    private ZirpRepository zirpRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        zirpRepository = new ZirpInitializer().initRepoWithTestData();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req, resp);
    }

    private void dispatchRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getRequestURI().matches(req.getContextPath() + "/web" + "/\\d+$")) {
            showDetailedTweetPage(req, resp);
        } else if (req.getRequestURI().matches(req.getContextPath() + "/web")) {
            showOverviewPage(req, resp);
        } else {
            showNotFoundPage(req, resp);
        }
    }

    private void showOverviewPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Zirp> zirps = zirpRepository.findAll();

        PrintWriter writer = resp.getWriter();
        writer.append("<h1>Zirper</h1>");
        for (Zirp zirp : zirps) {
            writer.append("<div>[" + zirp.getDate().toString() + "]&nbsp;<b>" + zirp.getUsername() + ":</b>&nbsp;<i>" + zirp.getZirp().substring(0, 20));
            if (zirp.getZirp().length() >= 20) {
                writer.append("...&nbsp;<a href='" + req.getContextPath() + "/web/" + zirp.getId() + "'>read more</a>");
            }
            writer.append("</i></div>");
        }
    }

    private void showDetailedTweetPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] pathElements = req.getRequestURI().split("/");
        Optional<Zirp> zirp = zirpRepository.findById(Integer.parseInt(pathElements[pathElements.length - 1]));

        if (zirp.isEmpty()) {
            showNotFoundPage(req, resp);
        } else {
            PrintWriter writer = resp.getWriter();
            writer.append("<h1>Zirp</h1>");
            writer.append("<h3>" + zirp.get().getUsername() + " @ " + zirp.get().getDate() + "</h3>");
            writer.append("<p>" + zirp.get().getZirp() + "</p>");
            writer.append("<br /><a href='" + req.getContextPath() + "/web'>Back to all Zirps</a>");
        }
    }

    private void showNotFoundPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.append("<h1>Page not found</h1>");
        writer.append("<p>The page you were looking for does not exist.</p><p><a href='" + req.getContextPath() + "/web'>Go back to Zirpers</a></p>");
    }
}
