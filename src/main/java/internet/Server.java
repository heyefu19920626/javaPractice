package internet;

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
        ServerSocket server = null;
        Socket s = null;
        try {
            server = new ServerSocket(8888);
            System.out.println("正在监听8888端口");
            s = server.accept();
            InputStream in = s.getInputStream();
            int msg = in.read();
            System.out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
