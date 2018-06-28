package ru.podstavkov.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.Company;
import ru.podstavkov.entity.Task;
import ru.podstavkov.service.CategoryService;
import ru.podstavkov.service.CompanyService;
import ru.podstavkov.service.TaskService;
import ru.podstavkov.utils.AppUtil;

@RestController
@RequestMapping("/api/")
public class APIControllerImpl implements APIController {
	
    private static final HttpHeaders head = new HttpHeaders();
    private Map<String, Object> map;

    @Autowired 
    protected TaskService  taskService;
    
    @Autowired 
    protected CompanyService  companyService;
    
    @Autowired 
    protected CategoryService  categoryService;
    

	public APIControllerImpl() {
		super();
        head.add("Content-type",MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
	}

	@Override
	public ResponseEntity<String> addCategory(@RequestBody String json, HttpServletResponse response) {
		map = AppUtil.getValues(json);
		return null;
	}

	@Override
	public ResponseEntity<String> addCompany(@RequestBody String json, HttpServletResponse response) {
		map = AppUtil.getValues(json);
		return null;
	}

	@Override
	public ResponseEntity<String> addTask(@RequestBody String json, HttpServletResponse response) {
		map = AppUtil.getValues(json);
		return null;
	}

	@Override
	public ResponseEntity<String> removeCategory(@RequestBody String json, HttpServletResponse response) {
		map = AppUtil.getValues(json);
		return null;
	}

	@Override
	public ResponseEntity<String> removeCompany(@RequestBody String json, HttpServletResponse response) {
		map = AppUtil.getValues(json);
		return null;
	}

	@Override
	public ResponseEntity<String> removeTask(@RequestBody String json, HttpServletResponse response) {
		map = AppUtil.getValues(json);
		return null;
	}

	@Override
	public ResponseEntity<String> getCategory(@RequestBody String json, HttpServletResponse response) {
		map = AppUtil.getValues(json);
		return null;
	}

	@Override
	public ResponseEntity<String> getCompany(@RequestBody String json, HttpServletResponse response) {
		map = AppUtil.getValues(json);
		return null;
	}

	@Override
	public ResponseEntity<String> getTask(@RequestBody String json, HttpServletResponse response) {
		map = AppUtil.getValues(json);
		return null;
	}

	@Override
	public ResponseEntity<List<Category>> getListCategory(@RequestBody String json, HttpServletResponse response) {
		map = AppUtil.getValues(json);
		return new ResponseEntity<List<Category>>(categoryService.getList(map), head, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Company>> getListCompany(@RequestBody String json, HttpServletResponse response) {
		map = AppUtil.getValues(json);
		return new ResponseEntity<List<Company>>(companyService.getList(map), head, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Task>> getListTask(@RequestBody String json, HttpServletResponse response) {
		map = AppUtil.getValues(json);
 		return new ResponseEntity<List<Task>>(taskService.getList(map), head, HttpStatus.OK);

	}

}
