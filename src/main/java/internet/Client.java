package internet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2018-04-14
 * Time: 19:35
 **/
public class Client {


    public static void main(String[] args) {

        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 8888);
            System.out.println(socket);
            OutputStream out = socket.getOutputStream();
            out.write(100);
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
