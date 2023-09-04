package com.cmenchu.ethreads.threadsexample.domain.repository;

import com.cmenchu.ethreads.threadsexample.domain.bo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
