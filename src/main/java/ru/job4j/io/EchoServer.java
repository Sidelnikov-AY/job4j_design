package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    public static void main(String[] args) {
        final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
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
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (str != null && !str.isEmpty()) {
                        if (str.contains(hello)) {
                            out.write("Hello".getBytes());
                            System.out.println(str);
                        } else if ((str.contains(exit))) {
                            System.out.println(str);
                            server.close();
                        } else {
                            out.write("What".getBytes());
                            System.out.println(str);
                        }
                    }
                    out.flush();
                }

            }
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}