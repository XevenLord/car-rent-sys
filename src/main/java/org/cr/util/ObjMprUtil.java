package org.cr.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjMprUtil<T> {

    public static void writeMapIntoTextFile(String path, HashMap<String, Object> map) {
        if (path == null || map == null) {
            System.err.println("Invalid input parameters. Path or map is null.");
            return;
        }

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(path))) {
            // iterate map entries
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // put key and value separated by a colon
                bf.write(entry.getKey() + ":" + entry.getValue());
                // new line
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            System.err.println("Error writing map to file: " + e.getMessage());
        }
    }

}
