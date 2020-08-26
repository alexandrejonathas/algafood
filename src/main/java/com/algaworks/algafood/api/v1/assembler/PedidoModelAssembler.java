package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinksV1;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinksV1 algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity; 	
	
	
	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}

	public PedidoModel toModel(Pedido pedido) {
		var pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);

		if (algaSecurity.podePesquisarPedidos()) {
			pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
		}
		
		if (algaSecurity.podeConsultarRestaurantes()) {
			pedidoModel.getRestaurante()
				.add(algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
		}
		
		if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
			pedidoModel.getCliente()
				.add(algaLinks.linkToUsuario(pedido.getCliente().getId()));
		}
		
		if (algaSecurity.podeConsultarFormasPagamento()) {
			pedidoModel.getFormaPagamento()
				.add(algaLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));
		}
		
		if (algaSecurity.podeConsultarCidades()) {
			pedidoModel.getEnderecoEntrega().getCidade()
				.add(algaLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));
		}
		
		if (algaSecurity.podeGerenciarPedidos(pedido.getCodigo())) {
			if(pedido.podeSerConfirmado()) {
				pedidoModel.add(algaLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
			}
			
			if(pedido.podeSerCancelado()) {
				pedidoModel.add(algaLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));		
			}
			
			if(pedido.podeSerEntregue()) {
				pedidoModel.add(algaLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));		
			}
		}
		
		if (algaSecurity.podeConsultarRestaurantes()) {
			pedidoModel.getItens().forEach(item -> {
				item.add(algaLinks.linkToProduto(pedidoModel.getRestaurante().getId(),
						item.getProdutoId()));
			});
		}
		
		return pedidoModel;
	}

	/*public List<PedidoModel> toCollectionModel(List<Pedido> pedidos) {
		return pedidos.stream().map(pedido -> toModel(pedido)).collect(Collectors.toList());
	}*/

}
