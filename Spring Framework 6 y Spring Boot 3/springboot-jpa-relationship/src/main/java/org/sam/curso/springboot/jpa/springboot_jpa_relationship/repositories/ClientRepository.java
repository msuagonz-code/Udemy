package org.sam.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import java.util.Optional;

import org.sam.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long>{

    @Query("select c from Client c left join fetch c.addresses where c.id=?1")
    Optional<Client> findOneWithAddresses(Long id);

    @Query("select c from Client c left join fetch c.invoices where c.id=?1")
    Optional<Client> findOneWithInvoices(Long id);

    @Query("select c from Client c left join fetch c.invoices left join fetch c.addresses left join fetch c.clientDetails where c.id=?1")
    Optional<Client> findOne(Long id);

}
