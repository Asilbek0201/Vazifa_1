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
import java.util.Set;

@WebServlet(
        name = "StudentUpdateServlet",
        value = "/student/update/*"
)
public class StudentUpdateServlet extends HttpServlet {

    private static final String PERSISTENCE_UNIT_NAME = "validation";
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);

            if (student != null) {
                req.setAttribute("student", student);
                req.getRequestDispatcher("/views/student/updateSt.jsp").forward(req, resp);
            }
        } finally {
            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        String fullName = req.getParameter("fullName");
        int age = Integer.parseInt(req.getParameter("age"));
        int groupId = Integer.parseInt(req.getParameter("groupId"));

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Group group = em.find(Group.class, groupId);
            if (group == null) {
                resp.getWriter().println("Error! Group does not exist.");
                return;
            }

            Student existingStudent = em.find(Student.class, id);

            if (existingStudent != null) {
                if (existingStudent.getGroup().getId() != groupId) {
                    Group previousGroup = existingStudent.getGroup();
                    previousGroup.setStudentCount(previousGroup.getStudentCount() - 1);

                    group.setStudentCount(group.getStudentCount() + 1);

                    existingStudent.setFullName(fullName);
                    existingStudent.setAge(age);
                    existingStudent.setGroup(group);

                    Set<ConstraintViolation<Student>> violations = validator.validate(existingStudent);
                    if (!violations.isEmpty()) {
                        resp.getWriter().println("Error! Invalid input.");
                        return;
                    }

                    em.merge(previousGroup);
                    em.merge(group);
                    em.merge(existingStudent);
                } else {
                    existingStudent.setFullName(fullName);
                    existingStudent.setAge(age);

                    Set<ConstraintViolation<Student>> violations = validator.validate(existingStudent);
                    if (!violations.isEmpty()) {
                        resp.getWriter().println("Error! Invalid input.");
                        return;
                    }

                    em.merge(existingStudent);
                }

                em.getTransaction().commit();
            }

            resp.sendRedirect("/students");
        } finally {
            em.close();
        }
    }
}



