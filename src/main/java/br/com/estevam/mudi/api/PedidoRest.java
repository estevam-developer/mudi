package br.com.estevam.mudi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estevam.mudi.model.Pedido;
import br.com.estevam.mudi.model.StatusPedido;
import br.com.estevam.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("api/pedidos")
public class PedidoRest {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping("aguardando")
	public Iterable<Pedido> pedidosAguardando() {
		return pedidoRepository.findByStatus(StatusPedido.AGUARDANDO);
	}
	
}
