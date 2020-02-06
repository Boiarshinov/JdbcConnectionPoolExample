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
import java.util.Arrays;

public class DeleteServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(DeleteServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("POST request coming");

        DAO<Nonogram> nonogramDAO = (NonogramDAO) getServletContext().getAttribute("nonogramDAO");

        String[] idsToDelete = req.getParameterValues("nonogramId");
        Arrays.stream(idsToDelete).map(s -> Integer.parseInt(s)).forEach(i -> {
            try {
                nonogramDAO.deleteById(i);
            }catch (DaoException e){
                logger.error("Can't delete nonogram from db", e);
            }
        });

        resp.sendRedirect("/");
    }
}
