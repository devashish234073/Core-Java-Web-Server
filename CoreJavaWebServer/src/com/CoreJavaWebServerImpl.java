package com;

import com.Handlers.LoginHandler;
import com.api.Server;
import com.users.Users;

import java.io.IOException;

public class CoreJavaWebServerImpl {

    public static void main(String[] args) {
        Users.init();
        try {
            int PORT = evaluatePort(args);
            Server server = new Server(PORT);
            server.mapGETHandler("/home","html/home.html");
            server.mapHandler("/login",new LoginHandler());
            server.mapPOSTHandler("/submitFeedback","html/feedbacksuccess.html");
            server.listen();
            System.out.println("started");
        } catch(IOException ioe) {
            System.out.println(ioe.toString());
        }
    }

    private static int evaluatePort(String[] args) {
        int PORT = 8888;
        if(args.length > 0) {
            try {
                int i = Integer.parseInt(args[0]);
                PORT = i;
            } catch(Exception e) {

            }
        }
        return PORT;
    }
}
