package com.api;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class Response {

    private HttpExchange httpExchange;
    private String data;

    private Response() {}

    public Response(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        this.data = "";
    }

    public void setContent(String data) {
        this.data = data;
    }

    public void appendContent(String data) {
        this.data += data;
    }

    public void clearContent() {
        this.data = "";
    }

    public void setAttribute(String attributeName,String attributeValue) {
        httpExchange.setAttribute(attributeName,attributeValue);
    }

    public void send() throws IOException {
        httpExchange.sendResponseHeaders(200, data.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(data.getBytes());
        os.close();
    }
}
