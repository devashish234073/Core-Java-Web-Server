# Core-Java-Web-Server
This is a having basic functionalities similar to java's servlet api. Since it creates server of its own it doesn't require an additional server.

entire api consists of the files in the com.api package (shown below):
![image](https://user-images.githubusercontent.com/20777854/39905081-28f1b962-54f9-11e8-948f-135e6508f154.png)

Server can be created and configured with url mappings in just 5 steps shown below:
![image](https://user-images.githubusercontent.com/20777854/39905214-cad6de56-54f9-11e8-9dbc-8e37e954d0c2.png)

Here:
#1.mapGETHandler servers a GET request for url  pattern "/home" and points to the "html/home.html" file.
#2.mapHandler serves the "/login" url pattern. And it points to LoginHandler class(where response is created) that extends HttpHandler class.
#3.mapGETHandler serves the POST request for "/submitFeedback" url pattern.

These handlers are able to get the form data from a GET or POST request. And for doing that the variable can be enclosed within "${" and "}".
Example : to retrieve the data from textbox with name attribute as "uname" , the login.html can use ${uname}


