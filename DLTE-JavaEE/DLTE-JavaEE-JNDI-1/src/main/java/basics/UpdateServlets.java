package basics;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "/update")
public class UpdateServlets extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:/OracleDS");
            Connection connection = dataSource.getConnection();
            String ParamUsername = req.getParameter("username");
            String ParamEmail = req.getParameter("email");
            if (ParamEmail!= null && ParamUsername != null) {
                String email = req.getParameter(ParamEmail);
                String query = "Update user_info set email=? where username=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, ParamUsername);
                int result = preparedStatement.executeUpdate();
                if (result != 0)
                    resp.getWriter().println("User Information updated");
                else
                    resp.getWriter().println("Updation of User Info failed");

            }
            connection.close();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
}
