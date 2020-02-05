package dev.boiarshinov.controller;

import dev.boiarshinov.dao.NonogramDAO;
import dev.boiarshinov.model.Nonogram;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NonogramDAO nonogramDAO = (NonogramDAO) getServletContext().getAttribute("nonogramDAO");
        List<Nonogram> nonograms = nonogramDAO.getAll();
        req.getSession().setAttribute("nonograms", nonograms);

        for (Nonogram nonogram : (List<Nonogram>) req.getSession().getAttribute("nonograms")){
            System.out.println(nonogram);
        }

        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
