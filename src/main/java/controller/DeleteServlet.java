package controller;

import db.DBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteServlet", value = "/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            int productDeleted = DBManager.deleteProduct(id);
            if (productDeleted > 0) {
                response.sendRedirect("/");
            } else {
                response.sendRedirect("/error.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
