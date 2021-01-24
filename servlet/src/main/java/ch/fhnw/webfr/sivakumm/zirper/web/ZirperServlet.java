package ch.fhnw.webfr.sivakumm.zirper.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ch.fhnw.webfr.sivakumm.zirper.domain.Zirp;
import ch.fhnw.webfr.sivakumm.zirper.persistence.ZirpRepository;
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
        zirpRepository = (ZirpRepository) config.getServletContext().getAttribute("repo");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchGetRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchPostRequest(req, resp);
    }

    private void dispatchGetRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getRequestURI().matches(req.getContextPath() + "/web" + "/\\d+/?$")) {
            showDetailedTweetPage(req, resp);
        } else if (req.getRequestURI().matches(req.getContextPath() + "/web/?")) {
            showOverviewPage(req, resp);
        } else {
            showNotFoundPage(req, resp);
        }
    }

    private void dispatchPostRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getRequestURI().matches(req.getContextPath() + "/web/zirps/?")) {
            addNewZirp(req, resp);
        } else {
            showNotFoundPage(req, resp);
        }
    }

    private void showOverviewPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Zirp> zirps = zirpRepository.findAll();

        PrintWriter writer = resp.getWriter();
        writer.append("<h1>Zirper</h1>");
        writer.append(
            "<form action='" + req.getContextPath() + "/web/zirps' method='post'><div class='row'>"
            + "<div class='col'><input name='username' type='text' placeholder='Username' /></div>"
            + "<div class='col'><input name='zirp' type='text' placeholder='Share your thoughts...' /></div>"
            + "<div class='col'><button class='btn btn-primary' type='submit'>Share</button></div>"
            + "</div></form><hr />"
        );
        for (Zirp zirp : zirps) {
            writer.append("<div class='row'>");
            writer.append("<div class='col'>" + formatDate(zirp.getDate()) + "</div>");
            writer.append("<div class='col'><b>" + zirp.getUsername() + "</b></div>");

            writer.append("<div class='col'><i>" + zirp.getZirp().substring(0, Math.min(zirp.getZirp().length(), 20)));
            if (zirp.getZirp().length() >= 20) {
                writer.append("...&nbsp;<a href='" + req.getContextPath() + "/web/" + zirp.getId() + "'>read more</a>");
            }
            writer.append("</i></div></div>");
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
        writer.append("<p>The page you were looking for does not exist.</p><p><a href='" + req.getContextPath()
                + "/web'>Go back to Zirper</a></p>");
    }

    private void addNewZirp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator())).split("&");

        String username = "";
        String zirp = "";

        for (String entry : body) {
            String[] keyValue = entry.split("=");
            if (keyValue.length == 2 && keyValue[0].equals("username") && keyValue[1] != null && keyValue[1].length() > 0) {
                username = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8.toString());
            }
            if (keyValue.length == 2 && keyValue[0].equals("zirp") && keyValue[1] != null && keyValue[1].length() > 0) {
                zirp = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8.toString());
            }
        }

        if (username.length() > 0 && zirp.length() > 0) {
            zirpRepository.save(new Zirp(username, zirp));
        }
        resp.sendRedirect(req.getContextPath() + "/web");
    }

    private String formatDate(Date date) {
        DateFormat formatter = DateFormat.getDateTimeInstance();
        return formatter.format(date);
    }
}
