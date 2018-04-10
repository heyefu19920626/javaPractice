package com.heyfu.test;

import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2018-04-08
 * Time: 17:10
 **/
public class TestSsh {




    @Test
    public void Test() throws IOException, JSchException {
        String host = "172.16.1.155";
        int port = 22;
        String user = "root";
        String password = "Cvicse123";
        String command = "cd /opt";
        String res = exeCommand(host,port,user,password,command);

        System.out.println(res);
    }

    public static void main(String[] args) throws IOException, JSchException {
        String host = "172.16.1.155";
        int port = 22;
        String user = "root";
        String password = "Cvicse123";
        String command = "cd /opt";


        int i = System.in.read();
        System.out.println(i);




//        String res = TestSsh.exeCommand(host,port,user,password,command);
    }


    public String exeCommand(String host, int port, String user, String password, String command) throws JSchException, IOException {

        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
        session.setConfig("StrictHostKeyChecking", "no");
        //    java.util.Properties config = new java.util.Properties();
        //   config.put("StrictHostKeyChecking", "no");

        session.setPassword(password);
        session.connect();

        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        InputStream in = channelExec.getInputStream();
        channelExec.setCommand(command);
        channelExec.setErrStream(System.err);
//        channelExec.connect();
        channelExec.setCommand("pwd");
        channelExec.connect();
        String out = IOUtils.toString(in, "UTF-8");
        System.out.println("out1 = " + out);
        channelExec.setCommand("ls");
        channelExec.setErrStream(System.err);
        channelExec.connect();
        out = IOUtils.toString(in, "UTF-8");
        System.out.println("out2: = " + out);

        channelExec.disconnect();
        session.disconnect();

        return out;
    }




}
