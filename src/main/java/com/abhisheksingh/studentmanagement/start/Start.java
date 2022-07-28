package com.abhisheksingh.studentmanagement.start;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;


public class Start {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/orclpdb";
    private static final String USERNAME = "hr";
    private static final String PASSWORD = "hr";

    public static final String CREATE_TABLE_QUERY = """
            CREATE TABLE IF NOT EXISTS MY_STUDENTS 
            NAME VARCHAR (20) , DEFAULT 'NO NAME'
            AGE NUMBER(5) , NOT NULL
            CLASS VARCHAR(20) , NOT NULL
            DOB DATE , NOT NULL
            """;
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getAllEmployees(final Statement statement) throws SQLException {
        statement.execute("SELECT * FROM EMPLOYEES");
    }

    private static void getAllEmployeesInRange(final Statement statement,
                                               final int lowerBound,
                                               final int upperBound) throws SQLException {
        statement.execute("SELECT * FROM EMPLOYEES WHERE salary in  (" + lowerBound + "," + upperBound + ")");
    }

    public static void main(String[] args) throws SQLException {
        final var connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        final var statement = connection.createStatement();
        getAllEmployees(statement);
        getAllEmployeesInRange(statement, 2000, 6000);
        final var resultSet = statement.getResultSet();
        while (resultSet.next()) {
            for (int i = 1; i <= resultSet.getFetchSize(); i++) {
                System.out.print(resultSet.getString(i) + " ");
            }

            System.out.println();
        }

//        try {
//            Class.forName("com.abhisheksingh.studentmanagement.start.Xyz").getDeclaredConstructor().newInstance();
//        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException |
//                 NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
    }
}
