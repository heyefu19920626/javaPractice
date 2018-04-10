package com.heyfu.practice.io;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2018-04-08
 * Time: 15:25
 **/
public class Hero {


    public static void main(String[] args) throws IOException, JSchException {

        JSch jSch = new JSch();
        String host = "172.16.1.155";
        String username = "root";
        String password = "Cvicse123";

        Session session = jSch.getSession(username, host);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(password);
        session.connect();

        Channel channel = session.openChannel("shell");
        channel.setInputStream(System.in);
        channel.setOutputStream(System.out);
        channel.connect();
//        channel.disconnect();
//        session.disconnect();


    }


}
