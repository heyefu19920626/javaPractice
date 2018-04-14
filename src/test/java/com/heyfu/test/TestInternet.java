package com.heyfu.test;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2018-04-14
 * Time: 18:16
 **/
public class TestInternet {


    @Test
    public void testIp() {
        InetAddress host = null;
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ip = host != null ? host.getHostAddress() : "获取主机出错";
        System.out.printf("本机IP地址是:%s %n", ip);
    }

    @Test
    public void testPing() throws IOException {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("ping www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        使用字节流
        BufferedInputStream input = new BufferedInputStream(p.getInputStream());
        byte[] content = new byte[1000];
        int len = 0;
        while ((len = input.read(content, 0, 1000)) > -1) {
            System.out.print(new String(content, 0, len, "gbk"));
        }
//        使用字符流
        p = Runtime.getRuntime().exec("ping www.douyu.com");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(p.getInputStream(), "gbk"));
        String line;
        while ((line = buffer.readLine()) != null) {
            System.out.println(line);
        }

    }


}
