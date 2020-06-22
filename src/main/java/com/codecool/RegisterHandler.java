package com.codecool;

import com.codecool.dao.StudentsDAO;
import com.codecool.model.Student;
import com.sun.imageio.plugins.common.InputStreamAdapter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class RegisterHandler implements HttpHandler {
    private StudentsDAO studentsDAO;

    public RegisterHandler() {
        this.studentsDAO = new StudentsDAO();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "s";
        String method = exchange.getRequestMethod(); // "POST" or "GET


        if (method.equals("POST")) {
            // retrieve data
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);

//            String data = br.readLine();
            Map<String, String> data = parseFormData(br.readLine());

//            System.out.println(data.toString());

            System.out.println(data.get("name"));
            System.out.println(data.get("surname"));
            System.out.println(data.get("age"));

            Student student = new Student();
            student.setName(data.get("name"))
                    .setSurname(data.get("surname"))
                    .setAge(Integer.parseInt(data.get("age")));

            System.out.println(student.toString());
            studentsDAO.setStudent(student);


            response = "data saved";
        }

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            // We have to decode the value because it's urlencoded. see: https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }
}
