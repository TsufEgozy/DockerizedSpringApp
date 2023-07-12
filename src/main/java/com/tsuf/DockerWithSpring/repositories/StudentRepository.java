package com.tsuf.DockerWithSpring.repositories;

import com.tsuf.DockerWithSpring.beans.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

import javax.persistence.Id;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsById(Long id);
    void deleteById(Long id);
}
