package io.github.gsogabriel;

import io.github.gsogabriel.domain.entity.Cliente;
import io.github.gsogabriel.domain.entity.Pedido;
import io.github.gsogabriel.domain.repository.ClientesRepository;
import io.github.gsogabriel.domain.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner init(@Autowired ClientesRepository clientesRepository){
//        return args -> {
//            Cliente c = new Cliente(null, "Fulano");
//            clientesRepository.save(c);

//
//            System.out.println("Salvando Clientes");
//            Cliente gabriel = new Cliente("Gabriel");
//            clientesRepository.save(gabriel);
//
//            Pedido p = new Pedido();
//            p.setCliente(gabriel);
//            p.setDataPedido(LocalDate.now());
//            p.setTotal(BigDecimal.valueOf(100));
//            pedidosRepository.save(p);
//
//            Cliente cliente = clientesRepository.findClienteFetchPedidos(gabriel.getId());
//            System.out.println(cliente);
//            System.out.println(cliente.getPedidos());
//
//
//            List<Cliente> result = clientesRepository.encontrarTodos("%Gab%");

//            result.forEach(System.out::println);
//            System.out.println("Existe Douglas no repositório? " + clientesRepository.existsByNome("Douglas"));
//
//            List<Cliente> todosOsClientes = clientesRepository.findAll();
//            todosOsClientes.forEach(System.out::println);
//
//            System.out.println("Atualizando clientes");
//            todosOsClientes.forEach(c -> {
//                c.setNome(c.getNome() + " atualizado");
//                clientesRepository.save(c);
//            });
//
//            todosOsClientes = clientesRepository.findAll();
//            todosOsClientes.forEach(System.out::println);
//
//            System.out.println("Buscando clientes");
//            clientesRepository.findByNomeLike("%Ga%").forEach(System.out::println);
//
//            System.out.println("Deletando clientes");
//            clientesRepository.findAll().forEach(c -> {
//                clientesRepository.delete(c);
//            });
//
//            todosOsClientes = clientesRepository.findAll();
//            if(todosOsClientes.isEmpty()){
//                System.out.println("Nenhum cliente encontrado.");
//            }else {
//                todosOsClientes.forEach(System.out::println);
//            }
//
//        };
//    }
}
