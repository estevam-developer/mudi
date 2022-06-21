package br.com.estevam.mudi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.estevam.mudi.model.StatusPedido;
import br.com.estevam.mudi.repository.PedidoRepository;

@Controller
public class HomeController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping("home")
	public ModelAndView home() {

		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("pedidos", pedidoRepository.findByStatus(StatusPedido.ENTREGUE));

		return modelAndView;
	}

}
