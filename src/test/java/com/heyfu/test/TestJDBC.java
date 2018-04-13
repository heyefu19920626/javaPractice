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
     * JDBC 事务
     * 要么都成功,要么都失败
     *
     * @author heyefu 14:27 2018/4/13
     **/
    @Test
    public void testAffair() {
        Connection conn = getConn();

        try (Statement s = conn.createStatement()) {
//            关闭自动提交
            conn.setAutoCommit(false);
            s.execute("INSERT INTO product_ VALUES(7, '测试事务1', 99.9, 2)");
            s.execute("INSERT INTO product_ VALUES(8, '测试事务1', 99.9, 2)");
//            手动提交
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (!conn.isClosed()) {
                    conn.rollback();
                    System.out.println("插入失败,回滚数据库");

                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
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
     * 获取数据库的元数据并输出
     *
     * @author heyefu 14:07 2018/4/13
     **/
    @Test
    public void getMetaData() {
        Connection conn = getConn();

        try {
            DatabaseMetaData data = conn.getMetaData();
            System.out.println("数据库产品名称:" + data.getDatabaseProductName());
            System.out.println("数据库版本:" + data.getDatabaseProductVersion());
            System.out.println("数据库和表分隔符:" + data.getCatalogSeparator());
            System.out.println("驱动版本:" + data.getDriverVersion());
            System.out.println("驱动名称:" + data.getDriverName());
            System.out.println("可用的数据库列表:");
            ResultSet r = data.getCatalogs();
            while (r.next()) {
                System.out.println("数据库名称:" + r.getString(1));
            }
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
