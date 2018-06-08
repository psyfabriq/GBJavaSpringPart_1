package ru.podstavkov.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();
	private static StringBuilder error  = new StringBuilder();
	
	public static Map<String, Object> getValues(String json,Boolean log){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = mapper.readValue(new ByteArrayInputStream(json.getBytes("UTF-8")), new TypeReference<Map<String, Object>>(){});
        } catch (JsonParseException e) {
        	System.out.println(e); 
        } catch (JsonMappingException e) {
        	System.out.println(e); 
        } catch (IOException e) {
        	System.out.println(e);        } 
        return map;
    }
	
    public static Map<String, Object> getValues(String json){
        return getValues(json,true);
    }
    
    public static boolean checkHasAllVariables(Map<String, Object> inspect , String ...variables) {
    	boolean res = true;
    	for (String v : variables) {
    		if(!inspect.containsKey(v)) {
    			res = false;
    			break;
    		}
		}
    	return res;
    }
}
