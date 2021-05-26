package com.sinosoft.demospringboot2highgo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DemoJavaConnHighGo {

    public static void main(String[] args) {
        String driver = "com.highgo.jdbc.Driver";
        String url = "jdbc:highgo://127.0.0.1:5866/highgo";
        try {
            Class.forName(driver);
            System.out.println("success find class");
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            int pageSize = 3;
            int pageIndex = 1;

            try {

                conn = DriverManager.getConnection(url, "highgo", "highgo2020");
                System.out.println("success connect");


                String sql = "SELECT id, name FROM emp ORDER BY id LIMIT ? OFFSET ?";//分页通过limit x offset y的方法实现
                System.out.println("sql:" + sql);
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, pageSize);//每页显示的数据条数
                stmt.setInt(2, (pageIndex - 1) * pageSize);//第2页第一条数据的前一条数据的id
                rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + "," + rs.getString(2));
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                ex.printStackTrace(System.out);
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace(System.out);
        }

    }
}
