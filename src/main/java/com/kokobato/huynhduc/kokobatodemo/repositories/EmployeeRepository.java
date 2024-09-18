package com.kokobato.huynhduc.kokobatodemo.repositories;

import com.kokobato.huynhduc.kokobatodemo.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(
            value = """
                    SELECT e
                    from Employee e
                    where e.department.id = :departmentId and e.empStatus like %:locationStatus% and e.empName like %:name%
                    """
    )
    List<Employee> seacrhEmployees(@Param("departmentId") int departmentId, @Param("locationStatus") String locationStatus, @Param("name") String name);
}
