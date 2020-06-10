package com.codecool.dao;

import com.codecool.model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO {

    public List<Student> getStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        Connector connector = new Connector();
        Connection connection = connector.Connect();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.students;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");

                Student student = new Student();
                student.setId(id)
                        .setName(name)
                        .setSurname(surname)
                        .setEmail(email);
                students.add(student);
            }
            rs.close();
            statement.close();
            connection.close();

            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new Exception("Data not found");
    }
}
