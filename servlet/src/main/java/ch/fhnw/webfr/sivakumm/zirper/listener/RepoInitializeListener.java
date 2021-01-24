package ch.fhnw.webfr.sivakumm.zirper.listener;

import ch.fhnw.webfr.sivakumm.zirper.persistence.ZirpRepository;
import ch.fhnw.webfr.sivakumm.zirper.util.ZirpInitializer;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class RepoInitializeListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String mode = sce.getServletContext().getInitParameter("mode");

        ZirpRepository repo;
        if (mode.equals("test")) {
            repo = new ZirpInitializer().initRepoWithTestData();
        } else {
            repo = ZirpRepository.getInstance();
        }

        sce.getServletContext().setAttribute("repo", repo);
    }
    
}
