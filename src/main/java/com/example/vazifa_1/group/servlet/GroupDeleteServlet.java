package com.example.vazifa_1.group.servlet;

import com.example.vazifa_1.group.model.Group;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "GroupDeleteServlet",
        urlPatterns = "/group/delete/*"
)
public class GroupDeleteServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("validation");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Group group = entityManager.find(Group.class, id);

        if (group != null) {
            req.setAttribute("group", group);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/group/delete.jsp");
            dispatcher.forward(req, resp);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Group group = entityManager.find(Group.class, id);

        if (group != null) {
            entityManager.remove(group);
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        resp.sendRedirect("/");
    }

    @Override
    public void destroy() {
        entityManagerFactory.close();
    }
}
