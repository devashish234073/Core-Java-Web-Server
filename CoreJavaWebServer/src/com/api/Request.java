package com.api;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Request {
    private HashMap<String,Object> requestAttributes;
    private Map headers;
    private String method;
    private HttpExchange httpExchange;
    private URI uri;

    private Request() {}

    public Request(HttpExchange httpExchange) throws IOException {
        this.httpExchange      = httpExchange;
        this.uri               = httpExchange.getRequestURI();
        this.requestAttributes = new HashMap<String,Object>();
        this.method            = httpExchange.getRequestMethod();
        this.headers           = httpExchange.getRequestHeaders();

        if(method.equals("POST")) {
            InputStream is = httpExchange.getRequestBody();
            int readVal = 0;
            String rawAttributes = "";
            readVal = is.read();
            while(readVal > -1) {
                rawAttributes += (char) readVal;
                readVal = is.read();
            }
            retrieveAttributes(rawAttributes);
        } else if(method.equals("GET")) {
            String[] arr = this.uri.toString().split("[?]");
            if(arr.length == 2) {
                retrieveAttributes(arr[1]);
            }
        }
    }

    private void retrieveAttributes(String rawAttributes) {
        if(!rawAttributes.equals("")) {
            String[] arr = rawAttributes.split("&");
            for(int i=0;i<arr.length;i++) {
                String[] kvpair = arr[i].split("=");
                if(kvpair.length == 2) {
                    requestAttributes.put(kvpair[0],kvpair[1]);
                }
            }
        }
    }

    public Iterator getHeaderKeys() {
        return headers.keySet().iterator();
    }

    public List getHeader(String key) {
        return (List) headers.get(key);
    }

    public Iterator getAttributesName() {
        return requestAttributes.keySet().iterator();
    }

    public Object getAttribute(String attributeName) {
        return requestAttributes.get(attributeName);
    }

    public URI getURI() {
        return this.uri;
    }

}

