package com.codecool.dao;

import com.codecool.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO {

    public List<Student> getStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        Connector connector = new Connector();
        Connection connection = connector.Connect();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM \"People\";");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
//                String email = rs.getString("email");
                int age = rs.getInt("age");

                Student student = new Student();
                student.setId(id)
                        .setName(name)
                        .setSurname(surname)
//                        .setEmail(email);
                        .setAge(age);
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

    public void setStudent(Student student) throws ClassNotFoundException, SQLException {
        Connector connector = new Connector();
        Connection connection = connector.Connect();

        String query = "INSERT INTO \"People\" (name, surname, age) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, student.getName());
        ps.setString(2, student.getSurname());
        ps.setInt(3, student.getAge());

        ps.executeUpdate(query);



    }
}
