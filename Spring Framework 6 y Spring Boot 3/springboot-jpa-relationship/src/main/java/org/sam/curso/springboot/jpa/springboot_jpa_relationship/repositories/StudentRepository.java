package org.sam.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import java.util.Optional;

import org.sam.curso.springboot.jpa.springboot_jpa_relationship.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long>{

    @Query("Select s from Student s left join fetch s.courses where s.id=?1")
    Optional<Student> findOneWithCourses(Long id);

}
