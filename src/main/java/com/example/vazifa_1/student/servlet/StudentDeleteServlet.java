package com.example.vazifa_1.student.servlet;

import com.example.vazifa_1.group.model.Group;
import com.example.vazifa_1.student.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "StudentDeleteServlet",
        urlPatterns = "/student/delete/*"
)
public class StudentDeleteServlet extends HttpServlet {

    private static final String PERSISTENCE_UNIT_NAME = "validation";
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);

            if (student != null) {
                Group group = student.getGroup();
                req.setAttribute("student", student);
                req.setAttribute("groupId", group.getId());
                req.getRequestDispatcher("/views/student/deleteSt.jsp").forward(req, resp);
            }
        } finally {
            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);

            if (student != null) {
                Group group = student.getGroup();
                em.remove(student);

                group.setStudentCount(group.getStudentCount() - 1);
                em.merge(group);

                em.getTransaction().commit();
            }

            resp.sendRedirect("/students");
        } finally {
            em.close();
        }
    }
}



