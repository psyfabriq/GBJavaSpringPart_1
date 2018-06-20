package ru.podstavkov.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MenuService {
	public List<String> getListNavigate() {		
		List<String> list = new ArrayList<>();
		list.add("<a class=\"nav-link\" href=\"./\">Задачи</a>");
		list.add("<a class=\"nav-link\" href=\"./list-company\">Компании</a>");
		list.add("<a class=\"nav-link\" href=\"./list-category\">категории</a>");
		return list;
	}
}
