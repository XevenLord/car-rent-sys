package org.cr.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjMprUtil<T> {

    public String dictToJson(Map<String, T> map) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // serialized dictionary is list of maps
        List<Map<String, String>> jsonDictionary = new ArrayList<>();
        for (Map.Entry<String, T> mapEntry : map.entrySet()) {
            // each map entry becomes its own map instance with two entries
            Map<String, String> jsonEntry = new HashMap<>();
            // write person key as "person" with json string represetation of person object
            jsonEntry.put("person", mapper.writeValueAsString(mapEntry.getKey()));
            // write pets value as "pets" key with json string represetation of pets list
            jsonEntry.put("pets", mapper.writeValueAsString(mapEntry.getValue()));
            jsonDictionary.add(jsonEntry);
        }
        return mapper.writeValueAsString(jsonDictionary);
    }

    public Map<String, T> jsonToDict(String json) throws IOException
    {
        Map<String, T> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        // read json String into list of maps
        List<Map<String, String>> jsonDictionary =
                mapper.readValue(json, new TypeReference<List<Map<String, Object>>>(){});
        // each list item is a map with two entries
        for (Map<String, String> jsonEntry : jsonDictionary) {
            map.put(
                    mapper.readValue(jsonEntry.get("person"), Person.class),
                    mapper.readValue(jsonEntry.get("pets"), new TypeReference<List<Pet>>(){}));
        }
        return map;
    }

}
