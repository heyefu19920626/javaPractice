package com.heyfu.test;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2018-04-14
 * Time: 19:59
 **/
public class TestSocket {


    @Test
    public void testClient(){

        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            OutputStream out = socket.getOutputStream();
            out.write(100);
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
