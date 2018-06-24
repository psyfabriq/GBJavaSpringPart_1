package ru.podstavkov.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class MenuService {
	public List<Map<String,String>> getListNavigate() {		
		List<Map<String,String>> list = new ArrayList<>();
		list.add(getItem("Задачи","/"));
		list.add(getItem("Компании","/list-company"));
		list.add(getItem("Категории","/list-category"));
		
	
		return list;
	}
	
	private Map<String,String> getItem(String name, String url){
		Map<String,String> map = new HashMap<>();
		map.put("name", name);
		map.put("url", url);
		return map;
	}
}
