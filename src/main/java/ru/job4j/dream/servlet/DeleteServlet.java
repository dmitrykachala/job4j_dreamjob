package ru.job4j.dream.servlet;

import org.apache.commons.io.FileUtils;
import ru.job4j.dream.PropertyManufacture;
import ru.job4j.dream.store.DbStore;
import ru.job4j.dream.store.MemStore;
import ru.job4j.dream.store.Store;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        DbStore.instOf().deleteCan(id);

        try {
            FileUtils.deleteQuietly(new File(PropertyManufacture.getDir() + id + ".jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
