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
	public ModelAndView getTask(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@PathVariable String id) {
		model.addAttribute("msg", "Info Task");
		Task task = taskService.get(id);
		model.addAttribute("edit", "/task/" + id + "/edit");
		model.addAttribute("delete", "/task/delete");
		return new ModelAndView("task", "task", task);
	}

	@RequestMapping(value = "/task/{id}/edit", method = RequestMethod.GET)
	public ModelAndView getTaskEdit(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@PathVariable String id) {
		model.addAttribute("msg", "Edit Task");
		model.addAttribute("postUrl", "/task/edit");
		model.addAttribute("submitTitle", "SAVE");

		List<Category> categories = categoryService.getList();
		List<Company> companies = companyService.getList();

		model.addAttribute("categories", categories);
		model.addAttribute("companies", companies);

		Task task = taskService.get(id);
		return new ModelAndView("task-edit", "task", task);
	}

	@RequestMapping(value = "/task/add", method = RequestMethod.GET)
	public ModelAndView getTaskAdd(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("msg", "Add Task");
		model.addAttribute("postUrl", "/task/add");
		model.addAttribute("submitTitle", "ADD");
		
		List<Category> categories = categoryService.getList();
		List<Company> companies = companyService.getList();

		model.addAttribute("categories", categories);
		model.addAttribute("companies", companies);
		
		return new ModelAndView("task-edit", "task", new Task());
	}

	@RequestMapping(value = "/task/add", method = RequestMethod.POST)
	public ModelAndView submit(@Valid @ModelAttribute("task") Task task, BindingResult result, ModelMap model,
			@RequestParam("categorysId") String[] categorysId, @RequestParam("companyId") String companyId) {
		
		List<Category> categories = categoryService.getList(categorysId);
		Company company   = companyService.get(companyId);
		
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

		Task res = taskService.create(task);

		return new ModelAndView("redirect:/task/" + res.getId());
	}
	@RequestMapping(value = "/task/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("task") Task task, BindingResult result, ModelMap model) {
		
		if (result.hasErrors() || !taskService.delete(task)) {
			return new ModelAndView("error");
		}
		
		return new ModelAndView("redirect:/");
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
		taskService.update(task);
		return new ModelAndView("redirect:/task/" + task.getId());
	}

}
