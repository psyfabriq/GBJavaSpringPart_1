package ru.podstavkov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import ru.podstavkov.service.CategoryService;
import ru.podstavkov.service.CompanyService;
import ru.podstavkov.service.MenuService;
import ru.podstavkov.service.ResourcesService;
import ru.podstavkov.service.TaskService;

public class AbstractController {
	@Autowired
	protected ResourcesService resourcesService;
	
	@Autowired
	protected MenuService menuService;
	
    @Autowired 
    protected TaskService  taskService;
    
    @Autowired 
    protected CompanyService  companyService;
    
    @Autowired 
    protected CategoryService  categoryService;
	
    @ModelAttribute
    public void addAttributes(Model model) {
    	model.addAttribute("projectname", "Podstavkov project");
        model.addAttribute("css", resourcesService.getListCSS());
        model.addAttribute("js",  resourcesService.getListJS());
        model.addAttribute("navigatemenu", menuService.getListNavigate());
    }
}
