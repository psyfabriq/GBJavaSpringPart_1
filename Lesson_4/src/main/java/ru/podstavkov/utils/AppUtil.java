package ru.podstavkov.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppUtil {

	private static ObjectMapper mapper = new ObjectMapper();
	private static StringBuilder error = new StringBuilder();

	public static Map<String, Object> getValues(String json, Boolean log) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = mapper.readValue(new ByteArrayInputStream(json.getBytes("UTF-8")),
					new TypeReference<Map<String, Object>>() {
					});
		} catch (JsonParseException e) {
			System.out.println(e);
		} catch (JsonMappingException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return map;
	}

	public static Map<String, Object> getValues(String json) {
		return getValues(json, true);
	}

	public static void getTypeOF(Object value) {
		if (value instanceof Integer) {
			System.out.println("This is an Integer");
		} else if (value instanceof String) {
			System.out.println("This is a String");
		} else if (value instanceof Float) {
			System.out.println("This is a Float");
		} else if (value instanceof Map) {
			System.out.println("This is a Map");
		} else if (value instanceof String[]) {
			System.out.println("This is a ArrayString");
		} else if (value instanceof Object[]) {
			System.out.println("This is a ArrayObject");
		} else if (value instanceof List) {
			System.out.println("This is a List");
		}
	}

	public static boolean checkHasAllVariables(Map<String, Object> inspect, String... variables) {
		boolean res = true;
		for (String v : variables) {
			if (!inspect.containsKey(v)) {
				res = false;
				break;
			}
		}
		return res;
	}

	public static String hashString(String s) throws NoSuchAlgorithmException {
		byte[] hash = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			hash = md.digest(s.getBytes());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < hash.length; ++i) {
			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1) {
				sb.append(0);
				sb.append(hex.charAt(hex.length() - 1));
			} else {
				sb.append(hex.substring(hex.length() - 2));
			}
		}
		return sb.toString();
	}
}
