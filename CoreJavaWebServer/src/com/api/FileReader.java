package com.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

public class FileReader {

    public static String readAsString(String relativeFilePath,Request req) throws IOException {
        String ret = readAsString(relativeFilePath);
        Iterator itr = req.getAttributesName();
        while(itr.hasNext()) {
            String key = (String) itr.next();
            String val = (String) req.getAttribute(key);
            ret = ret.replace("${"+key+"}",val);
        }
        return ret;
    }

    private static String readAsString(String relativeFilePath) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.FileReader(relativeFilePath));
        String ret = "";
        for(String line;(line = br.readLine()) != null;) {
            ret += line;
        }
        return ret;
    }
}
