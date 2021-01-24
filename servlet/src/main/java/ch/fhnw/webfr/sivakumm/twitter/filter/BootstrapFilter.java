package ch.fhnw.webfr.sivakumm.twitter.filter;

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
        writer.append("<html><head><title>Twitter on Servlet</title>"
            + "<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css' "
            + "integrity='sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO' "
            + "crossorigin='anonymous'></head><body><div class='container'>" + responseWrapper.getContent() + "</div></body></html>");
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
