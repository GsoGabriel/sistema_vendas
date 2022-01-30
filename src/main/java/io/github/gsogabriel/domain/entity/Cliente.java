package io.github.gsogabriel.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
// quando usamos jpa, não é necessario mais usar o arquivo data.sql
@Entity
// @Table só é necessário se a tabela tiver nome diferente do nome da classe
@Table(name = "cliente")
public class Cliente {

    @Id // propriedade da JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY) // a geração do h2DB usa o tipo AUTO
    @Column(name = "id") // mesma coisa da Table
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @JsonIgnore // diz para o parser (transformador de objeto em json ou o contrário) que ele deve ignorar totalmente essa propriedade
    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos;
}
