package com.company.jdbcdemo;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private final String dbName;
    private final String user;
    private final String password;
    private Connection connection;

    public Database(String dbName, String user, String password) {
        this.dbName = dbName;
        this.user = user;
        this.password = password;
    }

    private void connect() {
        connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbName, user, password);
            if (connection != null) {
                System.out.println("Connection established.");
            }
            else {
                System.out.println("Connection failed.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void close(Connection connection, Statement statement, ResultSet rs) {
        try {
            if(connection != null) connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            if(statement != null) statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            if(rs != null) rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Student> getAllData(String tableName) {
        List<Student> students = new ArrayList<>();
        connect();
        Statement statement = null;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT * FROM %s ORDER BY id;", tableName);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()) {
                students.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close(connection, statement, rs);
        }
        return students;
    }

    public void insertRow(String tableName, Student student) {
        connect();
        Statement statement = null;
        try {
            String query;
            if (student.getId() == -1) {
                query = String.format("INSERT INTO %s(first_name, last_name, email) VALUES ('%s', '%s', '%s');",
                        tableName, student.getFirstName(), student.getLastName(), student.getEmail());
            }
            else {
                query = String.format("INSERT INTO %s(id, first_name, last_name, email) VALUES (%d, '%s', '%s', '%s');",
                        tableName, student.getId(), student.getFirstName(), student.getLastName(), student.getEmail());
            }
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close(connection, statement, null);
        }
    }

    public Student getRow(String tableName, int id) {
        Student student = null;
        connect();
        Statement statement = null;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT * FROM %s WHERE id = %d", tableName, id);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            System.out.println(rs);
            while (rs.next()) {
                student = new Student(id, rs.getString(2), rs.getString(3), rs.getString(4));
            }
            System.out.println("Row loaded.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close(connection, statement, rs);
        }
        return student;
    }

    public void updateRow(String tableName, Student student) {
        connect();
        Statement statement = null;
        try {
            String query = String.format("UPDATE %s SET first_name = '%s', last_name = '%s', email = '%s' WHERE id = %d",
                    tableName, student.getFirstName(), student.getLastName(), student.getEmail(), student.getId());
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row updated.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close(connection, statement, null);
        }
    }

    public void deleteRow(String tableName, int id) {
        connect();
        Statement statement = null;
        try {
            String query = String.format("DELETE FROM %s WHERE id = %d", tableName, id);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row deleted.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close(connection, statement, null);
        }
    }
}
