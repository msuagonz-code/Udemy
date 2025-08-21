package org.sam.curso.springboot.app.springboot_crud.repositories;

import java.util.Optional;

import org.sam.curso.springboot.app.springboot_crud.entities.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepository extends CrudRepository<Role, Long>{

    Optional<Role> findByName(String name);

}
