package com.spring.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.example.model.Wine;
import com.spring.example.model.WineType;
import com.spring.example.repository.WineInterface;

@Controller
@RequestMapping("/wine")
public class WineController {
	
	@Autowired
	private WineInterface wineInterface;
	
	@GetMapping("/new")
	public ModelAndView newWine(Wine wine) {
		ModelAndView modelAndView = new ModelAndView("wine/wine-register");
		
		modelAndView.addObject(wine);
		
		modelAndView.addObject("types", WineType.values());
		
		return modelAndView;
	}
	
	@GetMapping("/{id}")
	public ModelAndView update(@PathVariable Long id) {
		return newWine(wineInterface.findOne(id));
	}
	
	@PostMapping("/new")
	public ModelAndView save(@Valid Wine wine, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return newWine(wine);
		}
		
		wineInterface.save(wine);
		
		attributes.addFlashAttribute("message", "Vinho salvo com Sucesso!");
		
		return new ModelAndView("redirect:/wine/new");
	}
	
	@GetMapping
	public ModelAndView listOfWine() {
		ModelAndView modelAndView = new ModelAndView("wine/wine-list");
		
		modelAndView.addObject("wineList", wineInterface.findAll());
		
		return modelAndView;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		wineInterface.delete(id);
		attributes.addFlashAttribute("message", "Vinho removido com sucesso.");
		
		return "redirect:/wine";
	}
}
