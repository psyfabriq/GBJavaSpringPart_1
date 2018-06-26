package ru.podstavkov.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

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
public class TaskController extends AbstractController {

	@RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
	public String getTask(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@PathVariable String id) {
		model.addAttribute("msg", "Info Task");
		Task task = aplicationService.getTask(id);
		model.addAttribute("name", task.getName());
		model.addAttribute("published_date", task.getPublishedDate());
		model.addAttribute("end_date", task.getEndDate());
		model.addAttribute("active", task.isActive());
		model.addAttribute("owner", task.getOwner());
		model.addAttribute("categories", task.getCategory());
		model.addAttribute("edit", "/task/" + id + "/edit");
		model.addAttribute("delete", "/task/" + id + "/delete");
		return "task";
	}

	@RequestMapping(value = "/task/{id}/edit", method = RequestMethod.GET)
	public ModelAndView getTaskEdit(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@PathVariable String id) {
		model.addAttribute("msg", "Edit Task");
		model.addAttribute("postUrl", "/task/edit");
		model.addAttribute("submitTitle", "SAVE");

		List<Category> categories = aplicationService.listCategory();
		List<Company> companies = aplicationService.listCompany();

		model.addAttribute("categories", categories);
		model.addAttribute("companies", companies);

		Task task = aplicationService.getTask(id);
		return new ModelAndView("task-edit", "task", task);
	}

	@RequestMapping(value = "/task/add", method = RequestMethod.GET)
	public ModelAndView getTaskAdd(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("msg", "Add Task");
		model.addAttribute("postUrl", "/task/add");
		model.addAttribute("submitTitle", "ADD");
		
		List<Category> categories = aplicationService.listCategory();
		List<Company> companies = aplicationService.listCompany();

		model.addAttribute("categories", categories);
		model.addAttribute("companies", companies);
		
		return new ModelAndView("task-edit", "task", new Task());
	}

	@RequestMapping(value = "/task/add", method = RequestMethod.POST)
	public ModelAndView submit(@Valid @ModelAttribute("task") Task task, BindingResult result, ModelMap model,
			@RequestParam("categoryId") String[] categorysId, @RequestParam("companyId") String companyId) {
		
		List<Category> categories = (List<Category>)aplicationService.getlistCategoryByIDs(categorysId);
		Company company   = aplicationService.getCompany(companyId);
		
		if (result.hasErrors() || categories.isEmpty() || company == null) {
			return new ModelAndView("error");
		}
		
		
		try {
			task.setCategory(categories);
			task.setOwner(company);
			task.rebuild();
		} catch (NoSuchAlgorithmException | BuilderExeption e) {
			return new ModelAndView("error");
		}

		Task res = aplicationService.createTask(task);

		return new ModelAndView("redirect:/task/" + res.getId());
	}

	@RequestMapping(value = "/task/edit", method = RequestMethod.POST)
	public ModelAndView edit(@Valid @ModelAttribute("task") Task task, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		try {
			task.rebuild();
		} catch (NoSuchAlgorithmException | BuilderExeption e) {
			return new ModelAndView("error");
		}
		aplicationService.updateTask(task);
		return new ModelAndView("redirect:/task/" + task.getId());
	}

}
