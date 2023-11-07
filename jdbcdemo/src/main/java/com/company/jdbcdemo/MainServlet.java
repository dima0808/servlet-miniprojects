package com.company.jdbcdemo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MainServlet", value = "/MainServlet")

public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Database db;

    @Override
    public void init() throws ServletException {
        super.init();
        db = new Database("web_student_tracker", "postgres", "root");
    }

    public void showStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> studentList = db.getAllData("students");
        request.setAttribute("students", studentList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
        dispatcher.forward(request, response);
    }

    public void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        if (firstName == null || lastName == null || email == null) {
            System.out.println("Row not inserted (null values).");
            showStudents(request, response);
            return;
        }
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            System.out.println("Row not inserted (blank values).");
            showStudents(request, response);
            return;
        }
        Student student = new Student(firstName, lastName, email);
        db.insertRow("students", student);
        showStudents(request, response);
    }

    public void loadStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = db.getRow("students", id);
        request.setAttribute("student", student);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        Student student = new Student(id, firstName, lastName, email);
        db.updateRow("students", student);
        showStudents(request, response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        db.deleteRow("students", id);
        showStudents(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String command = request.getParameter("command");

        if (command == null) command = "SHOW";

        switch (command) {
            case "SHOW":
                showStudents(request, response);
                break;
            case "ADD":
                addStudent(request, response);
                break;
            case "LOAD":
                loadStudent(request, response);
            case "UPDATE":
                updateStudent(request, response);
            case "DELETE":
                deleteStudent(request, response);
        }
    }
}