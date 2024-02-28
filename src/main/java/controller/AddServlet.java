package controller;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Laptop;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("/market/add.jsp").forward(request, response);
//        response.sendRedirect("/market/add.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String model = request.getParameter("model");
        int price = Integer.parseInt(request.getParameter("price"));
        int count = Integer.parseInt(request.getParameter("count"));

        try {
            DBManager.addProduct(new Laptop(null, model, price, count));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/");
    }
}
