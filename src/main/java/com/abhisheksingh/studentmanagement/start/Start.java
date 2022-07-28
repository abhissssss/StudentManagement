package com.abhisheksingh.studentmanagement.start;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;


public class Start {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/orclpdb";
    private static final String USERNAME = "hr";
    private static final String PASSWORD = "hr";

//    public static final String CREATE_TABLE_QUERY = """
//            CREATE TABLE IF NOT EXISTS MY_STUDENTS
//            NAME VARCHAR (20) , DEFAULT 'NO NAME'
//            AGE NUMBER(5) , NOT NULL
//            CLASS VARCHAR(20) , NOT NULL
//            DOB DATE , NOT NULL
//            """;
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getAllStudents (final Statement statement) throws SQLException {
        statement.executeQuery("SELECT * FROM SCHOOL_STUDENTS");
    }

    private static void getAllStudentsInGradeRange(final Statement statement,
                                               final String lowerBound,
                                               final String upperBound) throws SQLException {
        statement.executeQuery("SELECT * FROM SCHOOL_STUDENTS WHERE GRADE in  (" + lowerBound + "," + upperBound + ")");
    }

    public static void main(String[] args) throws SQLException {
        final var connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        final var statement = connection.createStatement();
        getAllStudents(statement);
//        getAllStudentsInGradeRange(statement, "XI", "XII");
        final var resultSet = statement.getResultSet();
        while (resultSet.next()) {

                System.out.println(resultSet.getString("Name"));


//            System.out.println();
        }

    }
}
