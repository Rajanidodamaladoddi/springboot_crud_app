package net.hcl.javaguide.springboot.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.hcl.javaguide.springboot.web.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}