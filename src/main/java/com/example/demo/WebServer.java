package com.example.demo;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WebServer {

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: java myJava.WebServer <number_of_servers>");
            System.exit(1);
        }

        int numServers;
        if (args.length == 0) {
            numServers = 1;
        } else {
            numServers = Integer.parseInt(args[0]);
        }
        for (int i = 0; i < numServers; i++) {
            int port = findAvailablePort();
            startServer(port, new ServerObserverImpl("Observer " + (port - 7999)));
        }
    }

    private static void startServer(int port, ServerObserverImpl observer) throws IOException {
        String contextPath = "/";

        ServerManager serverManager = new ServerManager();

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        MyHandler myHandler = new MyHandler(serverManager, observer);

        server.createContext(contextPath, myHandler);
        server.setExecutor(null);

        System.out.println("Server started at http://localhost:" + port + contextPath);

        serverManager.addObserver(observer);
        serverManager.notifyServerStart(port);

        Thread serverThread = new Thread(() -> {
            server.start();
        });

        serverThread.start();
    }

    private static int findAvailablePort() {
        int port = 8000;
        while (!isPortAvailable(port)) {
            port++;
        }
        return port;
    }

    private static boolean isPortAvailable(int port) {
        try {
            new java.net.Socket("localhost", port).close();
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    //Observer pattern
    static class ServerManager {
        private final List<ServerObserver> observers = new ArrayList<>();

        private ServerManager() {
        }

        public void addObserver(ServerObserver observer) {
            observers.add(observer);
        }

        public void notifyServerStart(int port) {
            for (ServerObserver observer : observers) {
                observer.update(port);
            }
        }
    }
}
