package com.kokobato.huynhduc.kokobatodemo.repositories;

import com.kokobato.huynhduc.kokobatodemo.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    List<Department> findAllByDpmParent(int parentId);
}
