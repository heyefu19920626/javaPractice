package com.heyfu.test;

import org.junit.Test;

import java.sql.*;

/**
 * Description:
 * JDBC CRUD(create, retrieve, update, delete)
 *
 * @author heyefu
 * Create in: 2018-04-13
 * Time: 11:33
 **/
public class TestJDBC {


    public static void main(String[] args) {
        list(0, 2);
        list(1, 2);
        list(2, 2);

    }


    /**
     * Description:
     * 分页查询
     *
     * @param start 开始页数
     * @param count 每页显示总数
     * @author heyefu 13:12 2018/4/13
     **/
    public static void list(int start, int count) {

        Connection conn = getConn();

        try (PreparedStatement s = conn.prepareStatement("SELECT * FROM  product_ limit ?, ?")) {
            s.setInt(1, start * count);
            s.setInt(2, count);
            ResultSet result = s.executeQuery();
            while (result.next()) {
                System.out.printf("%s %s %s %s%n", result.getString(1), result.getString(2), result.getString(3), result.getString(4));
            }
            result.last();
            System.out.printf("总共有%d条记录!", result.getRow());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Description:
     * 测试基本JDBC查询,使用Statement
     *
     * @author heyefu 11:57 2018/4/13
     **/
    @Test
    public void testRetrieve() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java", "root", "admin");
             Statement ps = conn.createStatement()
        ) {
            ResultSet result = ps.executeQuery("SELECT * FROM product_");
            while (result.next()) {
                System.out.printf("%s %s %s %s%n", result.getString(1), result.getString(2), result.getString(3), result.getString(4));
            }
            result.last();
            int rownum = result.getRow();
            System.out.printf("共有%d条数据%n", rownum);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/how2java", "root", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
