package com.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Server {

    HttpServer server;

    public Server(int PORT) throws IOException{
        server = HttpServer.create(new InetSocketAddress(PORT),0);
    }

    public void mapHandler(String urlPattern, HttpHandler httpHandler) {
        server.createContext(urlPattern, httpHandler);
    }

    public void mapGETHandler(String urlPattern, String relativeFilePath){
        mapGETHandler(urlPattern,relativeFilePath,"html");
    }

    public void mapGETHandler(String urlPattern, String relativeFilePath, String type) {
        server.createContext(urlPattern, new HttpHandler(){
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                if(httpExchange.getRequestMethod().equals("GET")) {
                    handlerInner(httpExchange, relativeFilePath, type);
                } else {
                    httpExchange.sendResponseHeaders(405, 0);
                    OutputStream os = httpExchange.getResponseBody();
                    os.write("".getBytes());
                    os.close();
                }
            }
        });
    }

    public void mapPOSTHandler(String urlPattern, String relativeFilePath){
        mapPOSTHandler(urlPattern,relativeFilePath,"html");
    }

    public void mapPOSTHandler(String urlPattern, String relativeFilePath, String type) {
        server.createContext(urlPattern, new HttpHandler(){
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                if(httpExchange.getRequestMethod().equals("POST")) {
                    handlerInner(httpExchange, relativeFilePath, type);
                } else {
                    httpExchange.sendResponseHeaders(405, 0);
                    OutputStream os = httpExchange.getResponseBody();
                    os.write("".getBytes());
                    os.close();
                }
            }
        });
    }

    private void handlerInner(HttpExchange httpExchange, String relativeFilePath, String type) throws IOException {
        Request req  = new Request(httpExchange);
        Response res = new Response(httpExchange);
        if(type.equalsIgnoreCase("json")) {
            res.setAttribute("Content-Type","application/json");
        } else if(type.equalsIgnoreCase("xml")) {
            res.setAttribute("Content-Type","application/xml");
        } else {
            res.setAttribute("Content-Type","text/html");
        }
        res.appendContent(FileReader.readAsString(relativeFilePath,req));
        res.send();
    }

    public void listen() {
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
