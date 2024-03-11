package first;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="MyFirstServlet",value = "/first/api/*")
public class MyFirstServlet extends HttpServlet {

    Logger logger;
    @Override
    public void destroy() {
        logger.info("servlet got destroyed");
        //super.destroy();
    }

    @Override
    public void init() throws ServletException {
        logger = LoggerFactory.getLogger(MyFirstServlet.class);
        logger.info("initialized");
        //super.init();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path == null || path.equals("/")) {
            logger.info("This is do get");

        } else if (path.contains("lumpSum")) {
            logger.info("Received " + req.getParameter("lumpSum"));
        }
      //  super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("This is do Post");
        //super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("This is do Put");

        //super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("This is do Delete");
       // super.doDelete(req, resp);
    }
}
