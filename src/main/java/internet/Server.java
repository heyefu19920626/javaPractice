package internet;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2018-04-14
 * Time: 19:31
 **/
public class Server {


    public static void main(String[] args) {

        Server server = new Server();

//        测试Socket接收int数据
//        server.acceptInt();

//        测试Socket接收字符串
        server.acceptString();


    }


    /**
     * Description:
     * 利用DataOutputStream接收字符串
     *
     * @author heyefu 14:31 2018/4/15
     **/
    public void acceptString() {
        try (ServerSocket server = new ServerSocket(8888);
             Socket socket = server.accept();
             InputStream in = socket.getInputStream();
             DataInputStream dataInput = new DataInputStream(in)
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

