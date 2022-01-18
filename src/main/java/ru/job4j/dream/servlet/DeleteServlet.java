package ru.job4j.dream.servlet;

import org.apache.commons.io.FileUtils;
import ru.job4j.dream.PropertyManufacture;
import ru.job4j.dream.store.DbStore;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DeleteServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        DbStore.instOf().remove(DbStore.instOf().findCanById(id));

        try {
            FileUtils.deleteQuietly(new File(PropertyManufacture.getDir() + id + ".jpg"));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception: ", e);
        }
    }
}
