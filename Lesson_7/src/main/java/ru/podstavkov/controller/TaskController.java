package ru.podstavkov.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TaskController extends AbstractController {
	
    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
    public String getTask(HttpServletRequest request, HttpServletResponse response,ModelMap model, @PathVariable String id) {
      model.addAttribute("msg", "Info Task");	
      return "task";
    }
    
    @RequestMapping(value = "/task/{id}/edit", method = RequestMethod.GET)
    public String getTaskEdit(HttpServletRequest request, HttpServletResponse response,ModelMap model, @PathVariable String id) {
      model.addAttribute("msg", "Edit Task");	
      return "task-edit";
    }
    
    @RequestMapping(value = "/task/add", method = RequestMethod.GET)
    public String getTaskAdd(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
      model.addAttribute("msg", "Add Task");	
      return "task-edit";
    }
}
