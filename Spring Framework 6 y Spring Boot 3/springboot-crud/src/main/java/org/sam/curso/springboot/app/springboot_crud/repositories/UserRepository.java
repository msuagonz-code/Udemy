package org.sam.curso.springboot.app.springboot_crud.repositories;

import org.sam.curso.springboot.app.springboot_crud.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

}
