package com.codecool.dao;

import java.sql.*;

public class Connector {
    java.sql.Connection connection;

    private String user = "tilcavmrsuhbzj"; //TODO please provide your user password and connection string
    private String password = "37e3925b366710ece9a679ad72d401e74bc6bb4ed1239676aaffef00ed27fc52";
    private static final String CONNECTION_STRING = "jdbc:postgresql://ec2-54-246-85-151.eu-west-1.compute.amazonaws.com:5432/dcmgt3tfcp4n6o";


    public Connector() throws ClassNotFoundException {
        this.connection = Connect();
    }

    public Connection Connect() throws ClassNotFoundException {
        connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(CONNECTION_STRING, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
