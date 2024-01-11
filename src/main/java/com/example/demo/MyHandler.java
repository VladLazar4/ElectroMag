package com.example.demo;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class MyHandler implements HttpHandler {

    private final WebServer.ServerManager serverManager;
    private final ServerObserver observer;

    public MyHandler(WebServer.ServerManager serverManager, ServerObserver observer) {
        this.serverManager = serverManager;
        this.observer = observer;
    }


    @Override
    public void handle(HttpExchange t) {
        String root = "D:\\Vlad\\Teme\\IS\\demo\\src\\main\\resources\\static";

        try {
            String uri = t.getRequestURI().toString();

            if (uri.endsWith("/")) {
                uri += "index.html";
            }

            String filePath = root + uri;

            File file = new File(filePath);

            if (file.exists() && !file.isDirectory()) {
                byte[] fileBytes = Files.readAllBytes(file.toPath());

                String extension = uri.substring(uri.lastIndexOf("."));
                String contentType = "text/html";

                switch (extension) {
                    case ".css":
                        contentType = "text/css";
                        break;
                }

                if (uri.equals("/api/data")) {
                    String dataFromDatabase = DBConnect.getInstance().fetchDataFromDatabase();
                    t.getResponseHeaders().add("Content-Type", contentType);
                    t.sendResponseHeaders(200, dataFromDatabase.length());
                    try (OutputStream os = t.getResponseBody()) {
                        os.write(dataFromDatabase.getBytes());
                    }
                } else {
                    t.getResponseHeaders().add("Content-Type", contentType);
                    t.sendResponseHeaders(200, fileBytes.length);
                    try (OutputStream os = t.getResponseBody()) {
                        os.write(fileBytes);
                    }
                }
            } else {
                String response = "404 Not Found";
                t.sendResponseHeaders(404, response.length());
                try (OutputStream os = t.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}