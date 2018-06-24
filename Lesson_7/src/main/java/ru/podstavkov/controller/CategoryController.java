package ru.podstavkov.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.Company;
import ru.podstavkov.entity.exeption.BuilderExeption;

@Controller
public class CategoryController extends AbstractController {
	
    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public String getCategory(HttpServletRequest request, HttpServletResponse response,ModelMap model, @PathVariable String id) {
      model.addAttribute("msg", "Info Category");	
      return "category";
    }
    
    @RequestMapping(value = "/category/{id}/edit", method = RequestMethod.GET)
    public String getCategoryEdit(HttpServletRequest request, HttpServletResponse response,ModelMap model, @PathVariable String id) {
      model.addAttribute("msg", "Edit Category");	
      return "category-edit";
    }
    
    @RequestMapping(value = "/category/add", method = RequestMethod.GET)
    public String getCategoryAdd(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
      model.addAttribute("msg", "Add Category");	
      return "category-edit";
    }
    
    @RequestMapping(value = "/category/add", method = RequestMethod.POST)
	public ModelAndView submit(@Valid @ModelAttribute("category") Category category, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		try {
			category.rebuild();
		} catch (NoSuchAlgorithmException | BuilderExeption e) {
			return new ModelAndView("error");
		}
	
		Category res = aplicationService.createCategory(category);

		return new ModelAndView("redirect:/company/"+res.getId());
	}
}
