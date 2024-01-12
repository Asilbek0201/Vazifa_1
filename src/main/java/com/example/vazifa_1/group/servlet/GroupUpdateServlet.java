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
        name = "GroupUpdateServlet",
        urlPatterns = "/group/update/*"
)
public class GroupUpdateServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("validation");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));

        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Group group = entityManager.find(Group.class, id);
            entityManager.close();

            req.setAttribute("group", group);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/group/update.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        String name = req.getParameter("name");

        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Group group = entityManager.find(Group.class, id);
            group.setName(name);

            entityManager.getTransaction().commit();
            entityManager.close();

            resp.sendRedirect("/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        entityManagerFactory.close();
    }
}
