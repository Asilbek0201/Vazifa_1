package com.example.vazifa_1.student.servlet;

import com.example.vazifa_1.student.model.Student;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "StudentHomeServlet",
        value = "/students"
)
public class StudentHomeServlet extends HttpServlet {

    private static final String PERSISTENCE_UNIT_NAME = "validation";
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = getStudentsFromDatabase();

        req.setAttribute("students", students);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/student/homeSt.jsp");
        dispatcher.forward(req, resp);
    }

    private List<Student> getStudentsFromDatabase() {
        List<Student> students = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            students = em.createQuery("SELECT s FROM Student s ORDER BY s.id", Student.class).getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return students;
    }
}




