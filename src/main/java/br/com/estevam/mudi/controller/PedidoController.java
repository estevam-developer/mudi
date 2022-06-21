package br.com.estevam.mudi.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.estevam.mudi.dto.RequisicaoCadastroPedido;
import br.com.estevam.mudi.model.Pedido;
import br.com.estevam.mudi.model.StatusPedido;
import br.com.estevam.mudi.model.Usuario;
import br.com.estevam.mudi.repository.PedidoRepository;
import br.com.estevam.mudi.repository.UsuarioRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {

	@Autowired
 	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("formCadastro")
	public String formulario(RequisicaoCadastroPedido requisicao) {
		return "pedido/formCadastro";
	}
	
	@PostMapping("cadastrar")
	public String cadastrar(@Valid RequisicaoCadastroPedido requisicao, BindingResult result) {
		
		if (result.hasErrors()) {
			return "pedido/formCadastro";
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Usuario usuario = usuarioRepository.getReferenceById(username);
		
		Pedido pedido = requisicao.toPedido();
		pedido.setUsuario(usuario);
		
		pedidoRepository.save(pedido);
		
		return "redirect:/pedido/listaPedidos";
	}
	
	@GetMapping("listar")
	public ModelAndView home(Principal principal) {
		
		ModelAndView modelAndView = new ModelAndView("pedido/listaPedidos");
		modelAndView.addObject("pedidos", pedidoRepository.findByUsuarioUsername(principal.getName()));
		
		return modelAndView;
	}
	
	@GetMapping("listar/{status}")
	public ModelAndView porStatus(@PathVariable("status") String status, Principal principal) {
		
		Iterable<Pedido> pedidos = pedidoRepository.findByStatusAndUsuarioUsername(StatusPedido.valueOf(status.toUpperCase()), principal.getName());
		
		ModelAndView modelAndView = new ModelAndView("pedido/listaPedidos");
		modelAndView.addObject("pedidos", pedidos);
		modelAndView.addObject("status", status);
		return modelAndView;
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/pedido/listaPedidos";
	}

}
