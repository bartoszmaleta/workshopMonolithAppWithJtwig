package com.codecool;

import com.codecool.dao.StudentsDAO;
import com.codecool.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentHandler implements HttpHandler {
    private StudentsDAO studentsDao;

    public StudentHandler() {
        studentsDao = new StudentsDAO();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";

        try {
            List<Student> students = this.studentsDao.getStudents();
            ObjectMapper mapper = new ObjectMapper();
            response = mapper.writeValueAsString(students);

            exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
            exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
            exchange.sendResponseHeaders(200, response.length());
        } catch (Exception e) {
            exchange.sendResponseHeaders(404, response.length());
        }

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private List<Student> getStudents() {
        Student adrian = new Student();
        adrian.setId(1)
                .setName("ADRIAN")
                .setSurname("WI")
                .setEmail("adrian.widlak@codecool.com");

        Student basia = new Student();
        basia.setId(2)
                .setName("BARBARA")
                .setSurname("KOWALSKA")
                .setEmail("basia.kowalska@codecool.com");

        Student jan = new Student();
        jan.setId(3)
                .setName("JAN")
                .setSurname("NOWAK")
                .setEmail("jan.nowak@codecool.com");

        List<Student> students = new ArrayList<>();
        students.add(basia);
        students.add(adrian);
        students.add(jan);

        return students;
    }
}
