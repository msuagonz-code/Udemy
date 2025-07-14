package org.sam.springboot.springmvc.app.repositories;

import org.sam.springboot.springmvc.app.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
