package com.heyfu.practice.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Description:
 * 数据库连接池
 *
 * @author heyefu
 * Create in: 2018-04-13
 * Time: 14:56
 **/
public class ConnectionPool {

    /**
     * 连接池大小
     */
    int poolSize = 10;
    /**
     * 连接池
     */
    LinkedList<Connection> conns = new LinkedList<>();
    /**
     * 连接池对象
     */
    private static ConnectionPool connectionPool;


    /*
      初始化代码块
     */ {
        System.out.println("开始初始化连接池!");
        for (int i = 0; i < poolSize; i++) {
            try {
                conns.add(getConn());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Description:
     * 获取数据库连接,可进行相关操作,用完需要送回
     *
     * @return java.sql.Connection
     * @author heyefu 15:30 2018/4/13
     **/
    public Connection getConnection() {
        while (true) {
            if (conns.isEmpty()) {
                try {
                    synchronized (conns){
                        conns.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
        return conns.removeFirst();
    }


    /**
     * Description:
     * 归还数据库连接
     *
     * @param conn 从连接池中取走的连接
     * @author heyefu 15:32 2018/4/13
     **/
    public void returnConnection(Connection conn) {
        conns.add(conn);
        synchronized (conns){
            conns.notifyAll();
        }
    }

    /**
     * Description:
     * 获取当前连接池的空余连接数
     *
     * @return int
     * @author heyefu 15:34 2018/4/13
     **/
    public int getConnectionSize() {
        return conns.size();
    }

    /**
     * Description:
     * 私有构造函数, 单例模式
     *
     * @author heyefu
     **/
    private ConnectionPool() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Description:
     * 获取数据库连接池
     *
     * @return com.heyfu.practice.io.ConnectionPool
     * @author heyefu 15:03 2018/4/13
     **/
    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
            return connectionPool;
        }
        return connectionPool;
    }

    /**
     * Description:
     * 获取数据库连接
     *
     * @return java.sql.Connection
     * @author heyefu 15:26 2018/4/13
     **/
    private Connection getConn() throws SQLException {
//      数据库url
        String url = "jdbc:mysql://127.0.0.1:3306/how2java?useSSL=true";
//      用户名
        String username = "root";
//      密码
        String password = "admin";
        return DriverManager.getConnection(url, username, password);
    }


}
