package com.example.demo11.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private BlockingQueue<Connection> free = new LinkedBlockingQueue<>(8);
    private BlockingQueue<Connection> used = new LinkedBlockingQueue<>(8);
    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            Class.forName("com.mysql.cj.jdbc.Driver()");
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
//        catch (ClassNotFoundException e) {
//            throw new RuntimeException(e.getMessage());
//            throw new ExceptionInInitializerError(e.getMessage());
//        }
    }

    private ConnectionPool() {
        String url = "jdbc:mysql://localhost:3306/phonetest";
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "1234");
        properties.put("autoReconnect", "true");
        properties.put("characterEncoding", "UTF-8");
        properties.put("useUnicode", "true");
        properties.put("useSSL", "true");
        properties.put("useJDBCCompliantTimezoneShift", "true");
        properties.put("useLegacyDatetimeCode", "false");
        properties.put("serverTimezone", "UTC");
        properties.put("serverSslCert", "classpath:server.crt");
        for (int i=0; i<8; i++){
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, properties);
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e);
            }
            free.add(connection);
        }
    }

    public static ConnectionPool getInstance() {
        //lock
        instance = new ConnectionPool();
        //unlock
        return instance;
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = free.take();
            used.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void  releaseConnection(Connection connection){
        try {
            used.remove();
            free.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public void destroyPool(){
        for (int i=0; i<8; i++){
            try {
                free.take().close();
            } catch (SQLException  | InterruptedException e) {
                // log e.printStackTrace();
            }
        }
    }

}
