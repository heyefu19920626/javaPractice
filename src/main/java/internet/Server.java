package internet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2018-04-14
 * Time: 19:31
 **/
public class Server {


    public static void main(String[] args) throws IOException {

        Server server = new Server();

//        测试Socket接收int数据
//        server.acceptInt();

//        测试Socket接收字符串
//        server.acceptString();

//        测试持续收发
//        server.client();

        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        new Thread(() -> server.sendThread(socket)).start();
        new Thread(() -> server.receiveThread(socket)).start();


    }


    /**
     * Description:
     * 发送线程
     *
     * @param socket Socket
     * @author heyefu 下午9:53 18-4-15
     **/
    public void sendThread(Socket socket) {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(socket.getOutputStream());
            String data;
            Scanner sc = new Scanner(System.in);
            while (true) {
                data = sc.nextLine();
                out.writeUTF(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Description:
     * 接收线程
     *
     * @param socket Socket
     * @author heyefu 下午9:50 18-4-15
     **/
    public void receiveThread(Socket socket) {
        DataInputStream in = null;
        String data;
        try {
            in = new DataInputStream(socket.getInputStream());
            while (true) {
                data = in.readUTF();
                System.out.println("从客户端接收消息:" + data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Description:
     * 服务器也能发送数据,且持续通信
     *
     * @author heyefu 15:22 2018/4/15
     **/
    public void client() {
        try (ServerSocket server = new ServerSocket(8088)) {
            Socket socket = server.accept();
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            String sendData = null;
            String receiveData;
//            怎么判断此次通信结束?
            while (true) {
                sendData = sc.nextLine();
                out.writeUTF(sendData);
                receiveData = input.readUTF();
                if ("over".equals(receiveData)) {
                    break;
                }
                System.out.println("从客户端接收消息: " + receiveData);
            }
            input.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Description:
     * 利用DataOutputStream接收字符串
     *
     * @author heyefu 14:31 2018/4/15
     **/
    public void acceptString() {
//        不知这种通过函数获取的流能否通过try with方式关闭
        try (ServerSocket server = new ServerSocket(8888);
             Socket socket = server.accept();
             InputStream in = socket.getInputStream();
             DataInputStream dataInput = new DataInputStream(in);
        ) {
            String data = dataInput.readUTF();
            System.out.printf("接收的字符串为:%s %n", data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Description:
     * 利用Socket接收int数据
     *
     * @author heyefu 14:26 2018/4/15
     **/
    public void acceptInt() {
        try (ServerSocket server = new ServerSocket(8888);
             Socket socket = server.accept();
             InputStream in = socket.getInputStream()
        ) {
            int msg = in.read();
            System.out.printf("接收的数字为:%d %n", msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

