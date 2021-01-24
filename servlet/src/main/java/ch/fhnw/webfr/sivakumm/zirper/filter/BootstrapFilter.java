package ch.fhnw.webfr.sivakumm.zirper.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class BootstrapFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, responseWrapper);
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.append(
            "<html><head><title>Zirper on Servlet</title>"
            + "<link href='" + request.getServletContext().getContextPath() + "/public/images/favicon/16x16.png' rel='icon' sizes='16x16' type='image/png'/>"
            + "<link href='" + request.getServletContext().getContextPath() + "/public/images/favicon/32x32.png' rel='icon' sizes='32x32' type='image/png'/>"
            + "<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css' "
            + "integrity='sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO' "
            + "crossorigin='anonymous'></head><body><div class='container'>"
            + decorateH1WithJumbotron(responseWrapper.getContent(), request.getServletContext().getContextPath())
            + "</div></body></html>"
        );
    }

    private String decorateH1WithJumbotron(String html, String contextPath) {
        String result = html.replaceFirst("<h1", "<div class='jumbotron'><h1");
        result = result.replaceFirst(
            "</h1>",
            "<span class='ml-3'><img src='" + contextPath
            + "/public/images/cricket.png' alt='Zirper Logo' style='height: 60px; width: auto;' /></span>"
            + "</h1></div><div class='container'>");
        return result += "</div>";
    }

    private class ResponseWrapper extends HttpServletResponseWrapper {

        private final StringWriter writer;

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
            this.writer = new StringWriter();
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(writer);
        }
        
        public String getContent() {
            return writer.toString();
        }
    }
}
