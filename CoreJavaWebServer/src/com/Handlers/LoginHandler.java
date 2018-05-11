package com.Handlers;

import com.api.FileReader;
import com.api.Request;
import com.api.Response;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.users.Users;

import java.io.IOException;

public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Request req = new Request(httpExchange);
        Response res = new Response(httpExchange);
        res.setAttribute("Content-Type","text/html");
        //authentication logic
        if(Users.isValidUser((String)req.getAttribute("uname"),(String) req.getAttribute("passw"))) {
            res.appendContent(FileReader.readAsString("html/loginsuccess.html",req));
        } else {
            res.appendContent(FileReader.readAsString("html/home.html",req));
            res.appendContent(FileReader.readAsString("html/loginfailed.html",req));
        }
        res.send();
    }
}