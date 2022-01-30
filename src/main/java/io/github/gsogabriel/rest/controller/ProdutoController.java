package io.github.gsogabriel.rest.controller;

import io.github.gsogabriel.domain.entity.Cliente;
import io.github.gsogabriel.domain.entity.Produto;
import io.github.gsogabriel.domain.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutosRepository produtosRepository;

    @Autowired
    public ProdutoController(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    @GetMapping
    public List<Produto> getAllProdutos(Produto filtro){
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, exampleMatcher);
        return produtosRepository.findAll(example);
    }

    @GetMapping("/{id}")
    public Produto getProduto(@PathVariable Integer id){
        return produtosRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado")
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto createProduto(@RequestBody @Valid Produto produto){
        return produtosRepository.save(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduto(@PathVariable Integer id){
        produtosRepository
                .findById(id)
                .map( produto -> {
                    produtosRepository.delete(produto);
                    return produto;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @PutMapping("/{id}")
    public Produto updateProduto(@PathVariable Integer id, @RequestBody @Valid Produto produto){
        return produtosRepository
                .findById(id)
                .map( produtoEncontrado -> {
                    produto.setId(produtoEncontrado.getId());
                    produtosRepository.save(produto);
                    return produtoEncontrado;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

}
