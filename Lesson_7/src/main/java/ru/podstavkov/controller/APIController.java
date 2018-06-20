package ru.podstavkov.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.Company;
import ru.podstavkov.entity.Task;

public interface APIController {
	
    @RequestMapping(value = "/add-category", method = RequestMethod.POST,headers="Accept=*/*",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> addCategory(@RequestBody String json, HttpServletResponse response);
    
    @RequestMapping(value = "/add-company", method = RequestMethod.POST,headers="Accept=*/*",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> addCompany(@RequestBody String json, HttpServletResponse response);
    
    @RequestMapping(value = "/add-task", method = RequestMethod.POST,headers="Accept=*/*",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> addTask(@RequestBody String json, HttpServletResponse response);
    
    @RequestMapping(value = "/rm-category", method = RequestMethod.POST,headers="Accept=*/*",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> removeCategory(@RequestBody String json, HttpServletResponse response);
    
    @RequestMapping(value = "/rm-company", method = RequestMethod.POST,headers="Accept=*/*",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> removeCompany(@RequestBody String json, HttpServletResponse response);
    
    @RequestMapping(value = "/rm-task", method = RequestMethod.POST,headers="Accept=*/*",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> removeTask(@RequestBody String json, HttpServletResponse response);
    
    @RequestMapping(value = "/get-category", method = RequestMethod.POST,headers="Accept=*/*",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getCategory(@RequestBody String json, HttpServletResponse response);
    
    @RequestMapping(value = "/get-company", method = RequestMethod.POST,headers="Accept=*/*",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getCompany(@RequestBody String json, HttpServletResponse response);
    
    @RequestMapping(value = "/get-task", method = RequestMethod.POST,headers="Accept=*/*",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getTask(@RequestBody String json, HttpServletResponse response);
    
    @RequestMapping(value = "/get-list-category", method = RequestMethod.POST,headers="Accept=*/*",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Category>> getListCategory(@RequestBody String json, HttpServletResponse response);
    
    @RequestMapping(value = "/get-list-company", method = RequestMethod.POST,headers="Accept=*/*",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Company>> getListCompany(@RequestBody String json, HttpServletResponse response);
    
    @RequestMapping(value = "/get-list-task", method = RequestMethod.POST,headers="Accept=*/*",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Task>> getListTask(@RequestBody String json, HttpServletResponse response);

}
