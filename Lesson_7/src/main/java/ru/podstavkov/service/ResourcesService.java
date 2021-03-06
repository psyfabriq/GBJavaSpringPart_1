package ru.podstavkov.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ResourcesService {
	
	private final String res = "/resources/";

	public List<String> getListCSS() {
		List<String> list = new ArrayList<>();	
		list.add(res+"lib/bootstrap/css/bootstrap.min.css");
		list.add(res+"lib/material/css/mdb.min.css");
		list.add(res+"lib/fontawesome/css/all.css");
		list.add(res+"css/style.css");
		return list;
	}

	public List<String> getListJS() {
		List<String> list = new ArrayList<>();
		list.add(res+"lib/material/js/jquery-3.3.1.min.js");
		list.add(res+"lib/bootstrap/js/bootstrap.min.js");
		list.add(res+"lib/bootstrap/js/BsMultiSelect.js");
		list.add(res+"lib/fontawesome/js/all.js");
		list.add(res+"lib/angular/angular.min.js");
		list.add(res+"lib/angular/ng-infinite-scroll.min.js");
		list.add(res+"js/app.js");
		return list;
	}

}
