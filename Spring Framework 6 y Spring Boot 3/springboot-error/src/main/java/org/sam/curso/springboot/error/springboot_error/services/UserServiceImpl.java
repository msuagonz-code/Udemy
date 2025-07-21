package org.sam.curso.springboot.error.springboot_error.services;

import java.util.List;
import java.util.Optional;

import org.sam.curso.springboot.error.springboot_error.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private List<User> users;

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        //User user = null;
        //for (User u : users) {
        //    if(u.getId().equals(id)){
        //        user = u;
        //        break;
        //    }
        //}
        //return Optional.ofNullable(user);

        return users.stream().filter( u -> u.getId().equals(id) ).findFirst();
    }

}
