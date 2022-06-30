package br.com.estevam.mudi.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estevam.mudi.dto.RequisicaoCadastroOferta;
import br.com.estevam.mudi.model.Oferta;
import br.com.estevam.mudi.model.Pedido;
import br.com.estevam.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("api/ofertas")
public class OfertasRest {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@PostMapping()
	public Oferta cadastrar(@RequestBody @Valid RequisicaoCadastroOferta requisicao) {
		
		Optional<Pedido> pedido = pedidoRepository.findById(requisicao.getPedidoId());
		
		if (pedido.isEmpty()) {
			return null;
		}
		
		Oferta oferta = requisicao.toOferta(pedido.get());
		
		pedidoRepository.save(pedido.get());
		
		return oferta;
	}

}
