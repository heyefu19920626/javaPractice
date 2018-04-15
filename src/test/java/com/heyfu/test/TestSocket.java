package com.heyfu.test;

import org.junit.Test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2018-04-14
 * Time: 19:59
 **/
public class TestSocket {


    /**
     * Description:
     * 测试利用DataOutputStream发送字符串
     *
     * @author heyefu 14:42 2018/4/15
     **/
    @Test
    public void testSendString() {
        try (Socket socket = new Socket("127.0.0.1", 8888);
             OutputStream out = socket.getOutputStream();
             DataOutputStream dataOut = new DataOutputStream(out)
        ) {
            dataOut.writeUTF("测试利用DataOutputStream及Socket发送字符串");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Description:
     * 测试利用OutputStream发送数字
     *
     * @author heyefu 14:41 2018/4/15
     **/
    @Test
    public void testClientInt() {
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
