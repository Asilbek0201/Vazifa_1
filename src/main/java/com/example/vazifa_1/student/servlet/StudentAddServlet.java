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
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

@WebServlet(
        name = "StudentAddServlet",
        value = "/student/add"
)
public class StudentAddServlet extends HttpServlet {

    private static final String PERSISTENCE_UNIT_NAME = "validation";
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/student/createSt.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        int age = Integer.parseInt(req.getParameter("age"));
        int groupId = Integer.parseInt(req.getParameter("groupId"));

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Group group = em.find(Group.class, groupId);
            if (group == null) {
                resp.getWriter().println("Xatolik! Bunday guruh mavjud emas");
                return;
            }

            Student newStudent = Student.builder()
                    .fullName(fullName)
                    .age(age)
                    .group(group)
                    .createdAt(LocalDateTime.now())
                    .createdBy(req.getSession().getId())
                    .build();

            Set<ConstraintViolation<Student>> violations = validator.validate(newStudent);
            if (!violations.isEmpty()) {
                resp.getWriter().println("Error! Invalid input.");
                return;
            }

            group.setStudentCount(group.getStudentCount() + 1);

            em.persist(newStudent);
            em.merge(group);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

        resp.sendRedirect("/students");
    }
}










