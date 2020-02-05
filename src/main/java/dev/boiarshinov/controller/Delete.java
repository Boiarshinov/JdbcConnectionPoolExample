package dev.boiarshinov.controller;

import dev.boiarshinov.dao.DataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class Delete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBase dataBase = (DataBase) getServletContext().getAttribute("database");

        String[] idsToDelete = req.getParameterValues("nonogramId");
        Arrays.stream(idsToDelete).map(s -> Integer.parseInt(s)).forEach(dataBase::deleteNonogramById);

        resp.sendRedirect("/");
    }
}
