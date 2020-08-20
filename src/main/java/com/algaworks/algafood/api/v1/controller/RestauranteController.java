package com.algaworks.algafood.api.v1.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.assembler.RestauranteApenasNomeModelAssembler;
import com.algaworks.algafood.api.v1.assembler.RestauranteBasicoModelAssembler;
import com.algaworks.algafood.api.v1.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.api.v1.model.input.RestauranteInput;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteControllerOpenApi;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/v1/restaurantes")
public class RestauranteController implements RestauranteControllerOpenApi {

	@Autowired
	private RestauranteRepository restauranteRep;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	//@Autowired
	//private SmartValidator validator;

	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;	
	
	@Autowired
	private RestauranteBasicoModelAssembler restauranteBasicoModelAssembler;

	@Autowired
	private RestauranteApenasNomeModelAssembler restauranteApenasNomeModelAssembler;	
	
	@Autowired
	private RestauranteInputDisassembler restauranteInputDisassembler;

	@GetMapping
	@Override
	public CollectionModel<RestauranteBasicoModel> listar() {
		return restauranteBasicoModelAssembler.toCollectionModel(restauranteRep.findAll());
	}
	
	@GetMapping(params = "projecao=apenas-nome")
	@Override
	public CollectionModel<RestauranteApenasNomeModel> listarApenasNomes() {
		return restauranteApenasNomeModelAssembler
                .toCollectionModel(restauranteRep.findAll());
	}	
	
	@GetMapping("/{restauranteId}")
	@Override
	public ResponseEntity<RestauranteModel> buscar(@PathVariable Long restauranteId) {
		Optional<Restaurante> restaurante = restauranteRep.findById(restauranteId);
		
		if(restaurante.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(restauranteModelAssembler.toModel(restaurante.get()));
	}
	
	@PostMapping
	@Override
	public ResponseEntity<?> criar(@RequestBody @Valid RestauranteInput restauranteInput){
		try {
			Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
			
			restaurante = cadastroRestaurante.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restauranteModelAssembler.toModel(restaurante));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	@Override
	public RestauranteModel atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput){
		try {
			
			//Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
			
			Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
			
			//BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
			restauranteInputDisassembler.copyToDomainObject(restauranteInput, restaurante);
			
			return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@DeleteMapping("/{restauranteId}")
	@Override
	public ResponseEntity<?> deletar(@PathVariable Long restauranteId) {
		try {
			cadastroRestaurante.deletar(restauranteId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	@Override
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.ativar(restauranteId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override
	public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.inativar(restauranteId);
		return ResponseEntity.noContent().build();
	}	
	
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override
	public ResponseEntity<Void> ativarLista(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.ativar(restauranteIds);
			return ResponseEntity.noContent().build();
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}		
	}
	
	@PutMapping("/inativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override
	public ResponseEntity<Void> inativarLista(@RequestBody List<Long> restauranteIds) {
		try {			
			cadastroRestaurante.inativar(restauranteIds);
			return ResponseEntity.noContent().build();
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}	
	
	@PutMapping("/{restauranteId}/abrir")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override
	public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
		cadastroRestaurante.abrir(restauranteId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{restauranteId}/fechar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override
	public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
		cadastroRestaurante.fechar(restauranteId);
		return ResponseEntity.noContent().build();
	}	
	
	
	/*@PatchMapping("/{restauranteId}")
	public Restaurante atualizarParcial(@PathVariable Long restauranteId, 
			@RequestBody Map<String, Object> campos, HttpServletRequest request){
		
		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		merge(campos, restauranteAtual, request);
		valida(restauranteAtual);
		return atualizar(restauranteId, restauranteAtual);
		
	}
	
	private void valida(Restaurante restaurante) {
		BeanPropertyBindingResult bindResult = new BeanPropertyBindingResult(restaurante, "restaurante");
		validator.validate(restaurante, bindResult);
		if(bindResult.hasErrors()) {
			throw new ValidacaoException(bindResult);
		}
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			//Configurar o objectMapper para falhar na desserialização
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			
			Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
			
			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				field.setAccessible(true);
				
				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
				
				ReflectionUtils.setField(field, restauranteDestino, novoValor);
				
			});						
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletServerHttpRequest);			
		}
	}*/
	
	
	
}
