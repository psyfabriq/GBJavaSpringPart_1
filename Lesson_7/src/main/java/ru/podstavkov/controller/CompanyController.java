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
import ru.podstavkov.entity.exeption.BuilderExeption;

@Controller
public class CompanyController extends AbstractController {

	@RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
	public ModelAndView getCompany(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@PathVariable String id) {
		model.addAttribute("msg", "Info Company");
		Company company = aplicationService.getCompany(id);
		model.addAttribute("edit", "/company/" + id + "/edit");
		model.addAttribute("delete", "/company/delete");
		return  new ModelAndView("company", "company", company);
	}

	@RequestMapping(value = "/company/{id}/edit", method = RequestMethod.GET)
	public ModelAndView getCompanyEdit(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@PathVariable String id) {
		model.addAttribute("msg", "Edit Company");
		model.addAttribute("postUrl", "/company/edit");
		model.addAttribute("submitTitle", "SAVE");
		Company c = aplicationService.getCompany(id);
		return new ModelAndView("company-edit", "company", c);
	}

	@RequestMapping(value = "/company/add", method = RequestMethod.GET)
	public ModelAndView getCompanyAdd(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("msg", "Add Company");
		model.addAttribute("postUrl", "/company/add");
		model.addAttribute("submitTitle", "ADD");
		return new ModelAndView("company-edit", "company", new Company());
	}

	@RequestMapping(value = "/company/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("company") Company company, BindingResult result, ModelMap model) {

		if (result.hasErrors() || !aplicationService.deleteCompany(company.getId())) {
			return new ModelAndView("error");
		}

		return new ModelAndView("redirect:/list-company");
	}

	@RequestMapping(value = "/company/add", method = RequestMethod.POST)
	public ModelAndView submit(@Valid @ModelAttribute("company") Company company, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		try {
			company.rebuild();
		} catch (NoSuchAlgorithmException | BuilderExeption e) {
			return new ModelAndView("error");
		}

		Company res = aplicationService.createCompany(company);

		return new ModelAndView("redirect:/company/" + res.getId());
	}

	@RequestMapping(value = "/company/edit", method = RequestMethod.POST)
	public ModelAndView edit(@Valid @ModelAttribute("company") Company company, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		try {
			company.rebuild();
		} catch (NoSuchAlgorithmException | BuilderExeption e) {
			return new ModelAndView("error");
		}
		aplicationService.updateCompany(company);
		return new ModelAndView("redirect:/company/" + company.getId());
	}

}
