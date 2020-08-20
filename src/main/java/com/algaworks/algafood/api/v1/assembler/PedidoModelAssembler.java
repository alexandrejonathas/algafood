package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinksV1;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinksV1 algaLinksHelper;
	
	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}

	public PedidoModel toModel(Pedido pedido) {
		var pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);

		pedidoModel.add(algaLinksHelper.linkToPedidos("pedidos"));
		
		pedidoModel.getRestaurante()
				.add(algaLinksHelper.linkToRestaurante(pedido.getRestaurante().getId()));

		pedidoModel.getCliente()
				.add(algaLinksHelper.linkToUsuario(pedido.getCliente().getId()));

		pedidoModel.getFormaPagamento()
				.add(algaLinksHelper.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

		pedidoModel.getEnderecoEntrega().getCidade()
				.add(algaLinksHelper.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

		if(pedido.podeSerConfirmado()) {
			pedidoModel.add(algaLinksHelper.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
		}
		
		if(pedido.podeSerCancelado()) {
			pedidoModel.add(algaLinksHelper.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));		
		}
		
		if(pedido.podeSerEntregue()) {
			pedidoModel.add(algaLinksHelper.linkToEntregaPedido(pedido.getCodigo(), "entregar"));		
		}
		
		pedidoModel.getItens().forEach(item -> {
			item.add(algaLinksHelper.linkToProduto(pedidoModel.getRestaurante().getId(),
					item.getProdutoId()));
		});

		return pedidoModel;
	}

	/*public List<PedidoModel> toCollectionModel(List<Pedido> pedidos) {
		return pedidos.stream().map(pedido -> toModel(pedido)).collect(Collectors.toList());
	}*/

}
