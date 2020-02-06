package dev.boiarshinov.controller;

import dev.boiarshinov.dao.DAO;
import dev.boiarshinov.dao.DaoException;
import dev.boiarshinov.dao.NonogramDAO;
import dev.boiarshinov.model.Nonogram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("GET request coming");
        try {
            DAO<Nonogram> nonogramDAO = (NonogramDAO) getServletContext().getAttribute("nonogramDAO");
            List<Nonogram> nonograms = nonogramDAO.getAll();
            req.getSession().setAttribute("nonograms", nonograms);
            req.getRequestDispatcher("/main.jsp").forward(req, resp);
        } catch (DaoException e){
            logger.error("gettting from DB error", e);
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
