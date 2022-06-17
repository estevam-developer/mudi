package br.com.estevam.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.estevam.mudi.dto.RequisicaoCadastroPedido;
import br.com.estevam.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {

	@Autowired
 	private PedidoRepository pedidoRepository;
	
	@GetMapping("formCadastro")
	public String formulario(RequisicaoCadastroPedido requisicao) {
		return "pedido/formCadastro";
	}
	
	@PostMapping("cadastrar")
	public String cadastrar(@Valid RequisicaoCadastroPedido requisicao, BindingResult result) {
		
		if (result.hasErrors()) {
			return "pedido/formCadastro";
		}
		
		pedidoRepository.save(requisicao.toPedido());
		
		return "redirect:/home";
	}
	
}
