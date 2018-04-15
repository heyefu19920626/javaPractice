package com.heyfu.test;

import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2018-04-14
 * Time: 19:59
 **/
public class TestSocket {


    public static void main(String[] args) {
        TestSocket test = new TestSocket();

//        test.testScanner();

        test.testServer();

    }


    /**
     * Description:
     * 可以作为服务端接收数据,并持续通信
     *
     * @author heyefu 15:28 2018/4/15
     **/
    public void testServer() {

        try {
            Socket socket = new Socket("127.0.0.1", 8088);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);
            String sendData;
            String receiveData;
            while (true ){
                sendData = sc.nextLine();
                out.writeUTF(sendData);
                if ("quit".equals(sendData)){
                    break;
                }
                receiveData = in.readUTF();
                System.out.println("从服务器接收消息: " + receiveData);
            }
            out.writeUTF("over");
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * Description:
     * 测试利用Scanner发送消息:空格正常了 正常了
     *
     * @author heyefu 15:09 2018/4/15
     **/
    public void testScanner() {
        try (Socket socket = new Socket("127.0.0.1", 8888)) {
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
//            next()输入中有空格好像不能记录?
            String data = sc.nextLine();
            output.writeUTF(data);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


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
