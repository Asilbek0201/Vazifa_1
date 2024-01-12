package com.example.vazifa_1.group.servlet;

import com.example.vazifa_1.group.model.Group;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

@WebServlet(
        name = "GroupAddServlet",
        value = "/group/add"
)
public class GroupAddServlet extends HttpServlet {
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("validation");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/group/create.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        try {
            HttpSession session = req.getSession();
            String createdBy = session.getId();

            Group group = Group.builder()
                    .name(name)
                    .studentCount(0)
                    .createdAt(LocalDateTime.now())
                    .createdBy(createdBy)
                    .build();

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Set<String> strings = ValidationUtils.validate(group);
            if (strings.size() == 0) {
                entityManager.getTransaction().begin();
                entityManager.persist(group);
                entityManager.getTransaction().commit();
            } else {
                for (String errorMessage : strings) {
                    System.out.println(errorMessage);
                }
            }
            resp.sendRedirect("/");
        } catch (RollbackException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}

