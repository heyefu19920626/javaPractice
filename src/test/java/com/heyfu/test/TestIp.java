package com.heyfu.test;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2018-04-14
 * Time: 18:16
 **/
public class TestIp {


    public static void main(String[] args) {

        TestIp test = new TestIp();
        String myIp = test.getIp();
        String ip = myIp.substring(0, myIp.lastIndexOf("."));
        String tempIp;
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS, new LinkedTransferQueue<>());
        for (int i = 0; i < 255; i++) {
            tempIp = ip + "." + i;
            pool.execute(new Runnable() {
                String tempIp;
                @Override
                public void run() {
                    if (test.isAlive(tempIp)) {
                        System.out.printf("有效IP:%s %n", tempIp);
                    }
                }
                public Runnable getTempIp(String ip) {
                    this.tempIp = ip;
                    return this;
                }
            }.getTempIp(tempIp));
            pool.shutdown();
        }
    }


    /**
     * Description:
     * 判断目标地址是否可访问
     *
     * @param ip ip地址或域名
     * @return java.lang.Boolean
     * @author heyefu 19:11 2018/4/14
     **/
    public Boolean isAlive(String ip) {

        Process process = null;
        BufferedReader buffer = null;
        StringBuilder content = new StringBuilder();
        String line;
        try {
            process = Runtime.getRuntime().exec("ping " + ip);
            buffer = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
            while ((line = buffer.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (content.toString().contains("无法访问")) {
            return false;
        }
        return true;
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

    public String getIp() {
        InetAddress host = null;
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return host != null ? host.getHostAddress() : "获取主机出错";
    }


}
