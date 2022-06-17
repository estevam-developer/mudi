package br.com.estevam.mudi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.estevam.mudi.model.StatusPedido;
import br.com.estevam.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public ModelAndView home() {
		
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("pedidos", pedidoRepository.findAll());
		
		return modelAndView;
	}
	
	@GetMapping("/{status}")
	public ModelAndView porStatus(@PathVariable("status") String status) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("pedidos", pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase())));
		modelAndView.addObject("status", status);
		return modelAndView;
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
	
}
