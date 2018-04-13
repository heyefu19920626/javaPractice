package com.heyfu.test;

import com.mysql.jdbc.PreparedStatement;
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


}
