package ru.podstavkov.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ResourcesService {
	
	private final String res = "./resources/";

	public List<String> getListCSS() {
		List<String> list = new ArrayList<>();	
		list.add(res+"lib/bootstrap/css/bootstrap.min.css");
		list.add(res+"css/style.css");
		return list;
	}

	public List<String> getListJS() {
		List<String> list = new ArrayList<>();
		list.add(res+"lib/jquery.min.js");
		list.add(res+"lib/bootstrap/js/bootstrap.min.js");
		list.add(res+"lib/angular/angular.min.js");
		list.add(res+"lib/angular/ng-infinite-scroll.min.js");
		list.add(res+"js/app.js");
		return list;
	}

}
