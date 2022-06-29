package br.com.estevam.mudi.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estevam.mudi.model.Pedido;
import br.com.estevam.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	Iterable<Pedido> findByUsuarioUsername(String usuario);
	
	Iterable<Pedido> findByStatusAndUsuarioUsername(StatusPedido pedido, String usuario);
	
	@Cacheable("pedidos")
	Iterable<Pedido> findByStatus(StatusPedido statusPedido);

}
