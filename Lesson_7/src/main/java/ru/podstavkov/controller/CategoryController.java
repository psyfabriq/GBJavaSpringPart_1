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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ru.podstavkov.entity.Category;
import ru.podstavkov.entity.Company;
import ru.podstavkov.entity.Task;
import ru.podstavkov.entity.exeption.BuilderExeption;

@Controller
public class CategoryController extends AbstractController {

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public ModelAndView getCategory(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@PathVariable String id) {
		model.addAttribute("msg", "Info Category");
		Category category = aplicationService.getCategory(id);
		model.addAttribute("edit", "/category/" + id + "/edit");
		model.addAttribute("delete", "/category/delete");
		return  new ModelAndView("category", "category", category);
	}

	@RequestMapping(value = "/category/{id}/edit", method = RequestMethod.GET)
	public ModelAndView getCategoryEdit(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@PathVariable String id) {
		model.addAttribute("msg", "Edit Category");
		model.addAttribute("postUrl", "/category/edit");
		model.addAttribute("submitTitle", "SAVE");
		Category category = aplicationService.getCategory(id);
		return new ModelAndView("category-edit", "category", category);
	}

	@RequestMapping(value = "/category/add", method = RequestMethod.GET)
	public ModelAndView getCategoryAdd(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("msg", "Add Category");
		model.addAttribute("postUrl", "/category/add");
		model.addAttribute("submitTitle", "ADD");
		return new ModelAndView("category-edit", "category", new Category());
	}

	@RequestMapping(value = "/category/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("category") Category category, BindingResult result, ModelMap model) {

		if (result.hasErrors() || !aplicationService.deleteCategory(category.getId())) {
			return new ModelAndView("error");
		}

		return new ModelAndView("redirect:/list-category");
	}

	@RequestMapping(value = "/category/add", method = RequestMethod.POST)
	public ModelAndView submit(@Valid @ModelAttribute("category") Category category, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		try {
			category.rebuild();
		} catch (NoSuchAlgorithmException | BuilderExeption e) {
			return new ModelAndView("error");
		}

		Category res = aplicationService.createCategory(category);

		return new ModelAndView("redirect:/category/" + res.getId());
	}

	@RequestMapping(value = "/category/edit", method = RequestMethod.POST)
	public ModelAndView edit(@Valid @ModelAttribute("category") Category category, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		try {
			category.rebuild();
		} catch (NoSuchAlgorithmException | BuilderExeption e) {
			return new ModelAndView("error");
		}
		aplicationService.updateCategory(category);
		return new ModelAndView("redirect:/category/" + category.getId());
	}
}
