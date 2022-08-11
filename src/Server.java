import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// Выбрал Blocking потому что проще
public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(23444);

        while (true) {
            try (Socket socket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {

                out.println("Привет, введи число для вычисления");

                String line;
                while (true) {
                    line = in.readLine();
                    if ("end".equals(line)) {
                        out.println("До скорого");
                        break;
                    }
                    int num = Integer.parseInt(line);
                    out.println("Результат: " + fib(num));
                }

            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    private static int fib(int n) {
        if (n <= 1)
            return n;
        return fib(n-1) + fib(n-2);
    }
}
