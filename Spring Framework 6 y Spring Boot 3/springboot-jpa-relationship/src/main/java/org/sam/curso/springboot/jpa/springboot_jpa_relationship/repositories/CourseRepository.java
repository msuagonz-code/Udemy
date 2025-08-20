package org.sam.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import java.util.Optional;

import org.sam.curso.springboot.jpa.springboot_jpa_relationship.entities.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long>{

    @Query("Select c from Course c left join fetch c.students where c.id=?1")
    Optional<Course> findOneWithStudents(Long id);

}
