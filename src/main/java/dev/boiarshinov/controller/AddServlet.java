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

public class AddServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(AddServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("POST request coming");

        String name = req.getParameter("name");
        byte width = Byte.parseByte(req.getParameter("width"));
        byte height = Byte.parseByte(req.getParameter("height"));
        Nonogram nonogram = new Nonogram(name, width, height);

        DAO<Nonogram> nonogramDAO = (NonogramDAO) getServletContext().getAttribute("nonogramDAO");

        try {
            nonogramDAO.add(nonogram);
        } catch (DaoException e){
            logger.error("Can't add nonogram to DB");
            throw new ServletException(e);
        }

        resp.sendRedirect("/");
    }
}
