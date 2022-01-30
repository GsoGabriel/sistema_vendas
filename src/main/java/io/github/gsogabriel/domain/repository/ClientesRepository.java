package io.github.gsogabriel.domain.repository;

import io.github.gsogabriel.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//@Repository // Registra a classe como repositório. Uma classe que acessa o Banco de Dados.
public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

//    @Query( value = " select * from cliente c where c.nome like '%:nome' ", nativeQuery = true)
//    List<Cliente> encontrarPorNome(@Param("nome") String nome);

//    @Query("delete from Cliente c where c.nome =:nome")
//    @Modifying
//    void deleteByNome(@Param("nome") String nome);

    boolean existsByNome(String nome);

//    @Query("select c from cliente c left join fetch c.pedidos where c.id = :id")
//    Cliente findClienteFetchPedidos(@Param("id") Integer id);

//    List<Cliente> findByNomeLike(String nome);

//    @Query(value = " select * from cliente c where c.nome like ':nome' ", nativeQuery = true)
//    List<Cliente> encontrarTodos(@Param("nome") String nome);

//    private static String SELECT_ALL = "SELECT * FROM CLIENTE";
//    private static String INSERT = "insert into cliente (nome) values (?)";
//    private static String UPDATE = "UPDATE cliente SET nome = ? WHERE id = ? ";
//    private static String DELETE = "DELETE FROM cliente WHERE id = ?";
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;

//    @Autowired
//    private EntityManager entityManager;
//
//    @Transactional
//    public Cliente salvar(Cliente cliente){
//        // update serve para fazer scripts SQL de inserção, atualização e deleção
////        jdbcTemplate.update( INSERT, new Object[]{cliente.getNome()} );
//        entityManager.persist(cliente);
//        return cliente;
//    }
//
////    public Cliente atualizar(Cliente cliente){
////        jdbcTemplate.update( UPDATE, new Object[]{
////                cliente.getNome(), cliente.getId()});
////        return cliente;
////    }
//
//    @Transactional
//    public Cliente atualizar(Cliente cliente){
//        entityManager.merge(cliente);
//        return cliente;
//    }
//
////    public void deletar(Cliente cliente){
////        this.deletar(cliente.getId());
////    }
////
////    public void deletar(Integer id){
////        jdbcTemplate.update(DELETE, new Object[]{id});
////    }
//
//    @Transactional
//    public void deletar(Cliente cliente){
//        if(!entityManager.contains(cliente)){
//            cliente = entityManager.merge(cliente);
//        }
//        entityManager.remove(cliente);
//    }
//
//    @Transactional
//    public void deletar(Integer id){
//        Cliente cliente = entityManager.find(Cliente.class, id);
//        this.deletar(cliente);
//    }
//
////    public List<Cliente> obterPorNome(String nome){
////        return jdbcTemplate.query(
////                SELECT_ALL.concat(" WHERE nome LIKE ?"),
////                new Object[]{"%" + nome + "%"},
////                obterClienteMapper());
////    }
//
//    @Transactional
//    public List<Cliente> obterPorNome(String nome){
//        String jpql = " select c from Cliente c where c.nome like :nome"; // :nome é o parametro nome
//        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
//        query.setParameter("nome", "%"+ nome +"%");
//        return query.getResultList();
//    }
//
////    public List<Cliente> getAll(){
////
////        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
////    }
//
//    @Transactional
//    public List<Cliente> getAll(){
//        return entityManager
//                .createQuery("from Cliente", Cliente.class)
//                .getResultList();
//    }
//
//    // RowMapper mapeia o resultado de um BD para uma classe.
////    private RowMapper<Cliente> obterClienteMapper() {
////        return new RowMapper<Cliente>() {
////            @Override
////            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
////                Integer id = rs.getInt("id");
////                String nome = rs.getString("nome");
////                return new Cliente(id, nome);
////            }
////        };
////    }

}
