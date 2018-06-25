package ru.podstavkov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.podstavkov.service.MenuService;
import ru.podstavkov.service.ResourcesService;

@Controller
public class MainController extends AbstractController{
	
	@Autowired
	ResourcesService resourcesService;
	
	@Autowired
	MenuService menuService;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listTask(Model model) {
      model.addAttribute("msg", "List of Task");	
      model.addAttribute("categories",aplicationService.listCategory());
      model.addAttribute("companes",aplicationService.listCompany());
      return "list-task";
    }
    
    @RequestMapping(value = "/list-company", method = RequestMethod.GET)
    public String listCompany(Model model) {
      model.addAttribute("msg", "List of Company");	
      return "list-company";
    }
    
    @RequestMapping(value = "/list-category", method = RequestMethod.GET)
    public String listCategory(Model model) {
      model.addAttribute("msg", "List of Category");	
      return "list-category";
    }
    

}
