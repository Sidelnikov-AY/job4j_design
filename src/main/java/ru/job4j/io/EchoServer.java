package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        final String exit = "Exit";
        final String hello = "Hello";
        final String what = "What";
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    System.out.println(str);
                        if (str.contains(hello)) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write("Hello".getBytes());
                            System.out.println(str);
                        } else if ((str.contains(exit))) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            System.out.println(str);
                            server.close();
                        } else {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write("What".getBytes());
                            System.out.println(str);
                        }
                    out.flush();
                }
            }
        }
    }
}