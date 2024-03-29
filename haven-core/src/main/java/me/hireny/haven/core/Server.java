package me.hireny.haven.core;

import me.hireny.haven.core.http.request.Request;
import me.hireny.haven.core.http.response.Response;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: Server
 * @Author: hireny
 * @Date: Create in 2019/11/09 21:58
 * @Description: TODO
 */
public class Server {
    private final static int SERVER_PORT = 9090;

    public static final String WEB_ROOT = System.getProperty("user.dir")
            + File.separator + "webroot";

    private static final String SHUTDOWN_COMMAND = "/QUIT";

    public static void main(String[] args) {
        Server server = new Server();
        server.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        try {
            System.out.println(WEB_ROOT);
            serverSocket = new ServerSocket(SERVER_PORT);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        while(true) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                // 创建Request对象并解析
                Request request = new Request(input);
                request.parse();
                // 检查是否是关闭服务命令
                if (request.getUri().equals(SHUTDOWN_COMMAND)) {
                    break;
                }

                // 创建 Response 对象
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                // 关闭 socket 对象
                socket.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
