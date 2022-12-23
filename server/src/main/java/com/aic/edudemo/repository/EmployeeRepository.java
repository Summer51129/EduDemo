package com.aic.edudemo.repository;

import com.aic.edudemo.domain.Employee;
import com.aic.edudemo.web.vm.EmployeeRequestVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//@Repository 告知框架註冊此 interface 為 Repository 元件，此介面繼承 JpaRepository
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    /*
    @Query("Select e From Employee e"
            + " Where Upper(e.lastName) Like Upper(:#{#selector.lastName})"
            + " And Upper(e.firstName) Like Upper(:#{#selector.firstName})"
            + " And ( e.email = :#{#selector.email} OR :#{#selector.email} Is Null )"
            + " And ( e.phoneNumber = :#{#selector.phoneNumber} OR :#{#selector.phoneNumber} Is Null )"
            + " And ( e.salary Between :#{#selector.salaryFrom} And :#{#selector.salaryTo} )"
            + " And ( e.hireDate Between :#{#selector.hireDateFrom} And :#{#selector.hireDateTo} )"
            + " And ( e.jobId = :#{#selector.jobId} OR :#{#selector.jobId} is Null )"
            + " And ( e.departmentId IN :#{#selector.departmentIds.size() != 0 ? #selector.departmentIds : null} OR :#{#selector.departmentIds.size() != 0 ? #selector.departmentIds.size() : null} is Null)"
    )
    Page<Employee> findEmployees(Pageable pageable, @Param("selector") EmployeeRequestVM employeeRequestVM);
    */

    @Query("Select e From Employee e"
            +" Where Upper(e.lastName) Like Upper(:#{#selector.lastName})"
            +" And Upper(e.firstName) Like Upper(:#{#selector.firstName})"
            +" And ( e.email = :#{#selector.email} OR :#{#selector.email} Is Null )"
            +" And ( e.phoneNumber = :#{#selector.phoneNumber} OR :#{#selector.phoneNumber} Is Null )"
            +" And ( e.salary Between :#{#selector.salaryFrom} And :#{#selector.salaryTo} )"
            +" And ( e.hireDate Between :#{#selector.hireDateFrom} And :#{#selector.hireDateTo} )"
            +" And ( e.jobId = :#{#selector.jobId} OR :#{#selector.jobId} is Null )"
            +" And ( e.departmentId IN :#{#selector.departmentIds.size() != 0 ? #selector.departmentIds : null} OR :#{#selector.departmentIds.size() != 0 ? #selector.departmentIds.size() : null} is Null)"
    )
    Page<Employee> findEmployees(Pageable pageable, @Param("selector") EmployeeRequestVM employeeRequestVM);
    // 請撰寫以上部分.....
}



