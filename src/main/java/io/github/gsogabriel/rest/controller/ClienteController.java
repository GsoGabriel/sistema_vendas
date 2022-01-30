package io.github.gsogabriel.rest.controller;

import io.github.gsogabriel.domain.entity.Cliente;
import io.github.gsogabriel.domain.repository.ClientesRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@Api("Api Clientes")
public class ClienteController {

    private ClientesRepository clientesRepository;

    @Autowired
    public ClienteController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente Encontrado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado para o ID informado")
    })
    public Cliente getClienteById(@PathVariable Integer id){
        return clientesRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")
                );
    }
//    @GetMapping("/{id}")
//    @ResponseBody // essa annotation diz que o String é o corpo da minha resposta
//    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id){
//        Optional<Cliente> cliente = clientesRepository.findById(id);
//
//        if(cliente.isPresent()){
////            uma forma de fazer.....
////            ResponseEntity<Cliente> responseEntity =
////                    new ResponseEntity<>(cliente.get(), HttpStatus.OK);
//            return ResponseEntity.ok(cliente.get());
//        }
//
//        return ResponseEntity.notFound().build();
//    }
//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public Cliente save( @RequestBody @Valid Cliente cliente){
        return clientesRepository.save(cliente);
    }
//    @PostMapping
//    @ResponseBody
//    public ResponseEntity save( @RequestBody Cliente cliente){
//        Cliente clienteSalvo = clientesRepository.save(cliente);
//        return ResponseEntity.ok(clienteSalvo);
//    }
//
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        clientesRepository
                .findById(id)
                .map( cliente -> {
                    clientesRepository.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }
//    @DeleteMapping("/{id}")
//    @ResponseBody
//    public ResponseEntity delete(@PathVariable Integer id){
//        Optional<Cliente> cliente = clientesRepository.findById(id);
//
//        if(cliente.isPresent()){
//            clientesRepository.delete(cliente.get());
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,
                                  @RequestBody @Valid Cliente cliente) {
        clientesRepository
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientesRepository.save(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }
//    @PutMapping("/{id}")
//    @ResponseBody
//    public ResponseEntity update( @PathVariable Integer id,
//                                  @RequestBody Cliente cliente) {
//        // método .map(func) do Optional: Ele executa o que ta dentro do map caso ache
//        // um optional na função alterior. Senão, faz outra coisa.
//        return clientesRepository
//                .findById(id)
//                .map( clienteExistente -> {
//                    cliente.setId(clienteExistente.getId());
//                    clientesRepository.save(cliente);
//                    return ResponseEntity.noContent().build();
//                }).orElseGet(()-> ResponseEntity.notFound().build());
//
//    }
//
    @GetMapping
    public List<Cliente> find( Cliente filtro){
        ExampleMatcher exampleMatcher = ExampleMatcher
                                            .matching()
                                            .withIgnoreCase()
                                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, exampleMatcher);
        return clientesRepository.findAll(example);
    }
//    @GetMapping
//    public ResponseEntity find( Cliente filtro){
//        ExampleMatcher exampleMatcher = ExampleMatcher
//                                            .matching()
//                                            .withIgnoreCase()
//                                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
//
//        Example example = Example.of(filtro, exampleMatcher);
//        List<Cliente> clienteList = clientesRepository.findAll(example);
//        return ResponseEntity.ok(clienteList);
//    }

//    @RequestMapping(
//            value = "/hello/{nome}",
//            method = RequestMethod.GET,
//            consumes = {"application/json", "application/xml"}, // aqui eu permito o cliente passar um arquivo json ou xml para ele receber e interpertar
//            produces = {"application/json", "application/xml"}  // aqui eu permito a função produzir um arquivo json ou xml
//    )
//    @ResponseBody // essa annotation diz que o String é o corpo da minha resposta
//    public String helloCliente(@PathVariable("nome") String nomeCliente){
//        return String.format("Hello %s", nomeCliente);
//    }
}
