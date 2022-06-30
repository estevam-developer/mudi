package br.com.estevam.mudi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.estevam.mudi.model.Oferta;
import br.com.estevam.mudi.model.Pedido;

public class RequisicaoCadastroOferta {
	
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	

	private Long pedidoId;

	@Pattern(regexp = "^\\d+(\\.\\d{2})?$")
	@NotNull
	private String valor;

	@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}$")
	@NotNull
	private String dataEntrega;

	private String comentario;
	

	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Oferta toOferta(Pedido pedido) {
		Oferta oferta = new Oferta();
		
		oferta.setValor(new BigDecimal(this.valor));
		oferta.setDataEntrega(LocalDate.parse(this.dataEntrega, DATE_FORMAT));
		oferta.setComentario(this.comentario);
		oferta.setPedido(pedido);
				
		return oferta;
	}

}
