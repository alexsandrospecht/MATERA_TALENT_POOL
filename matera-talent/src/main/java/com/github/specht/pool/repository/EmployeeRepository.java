package com.github.specht.pool.repository;

import com.github.specht.pool.enums.Status;
import com.github.specht.pool.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, String> {

    Page<Employee> findAllByStatus(Status status, Pageable pageable);

    Optional<Employee> findByIdAndStatus(String id, Status status);

}
