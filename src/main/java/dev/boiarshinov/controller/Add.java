package dev.boiarshinov.controller;

import dev.boiarshinov.dao.NonogramDAO;
import dev.boiarshinov.model.Nonogram;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Add extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        byte width = Byte.parseByte(req.getParameter("width"));
        byte height = Byte.parseByte(req.getParameter("height"));
        Nonogram nonogram = new Nonogram(name, width, height);

        NonogramDAO nonogramDAO = (NonogramDAO) getServletContext().getAttribute("nonogramDAO");
        nonogramDAO.add(nonogram);

        resp.sendRedirect("/");
    }
}
