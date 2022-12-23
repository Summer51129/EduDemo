package com.aic.edudemo.controller;

import com.aic.edudemo.domain.Employee;
import com.aic.edudemo.service.EmployeeService;
import com.aic.edudemo.web.vm.EmployeeRequestVM;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

//@AllArgsConstructor 在類別上，Lombok 會建立參數為所有 field 的建構子
//@RestController等於@Controller+@ResponseBody，主要是為了使http請求返回json或xml數據
//@RequestMapping Web請求映射，請求地址
//@GetMapping 直接從URL獲得數據
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class EmployeeController{
    private final EmployeeService employeeService;

    @GetMapping("/employees/all")
    public Object findEmployees(Pageable pageable){
        return this.employeeService.findAll(pageable);
    }

    @GetMapping("/employees")
    public Object findEmployees(Pageable pageable, @ModelAttribute EmployeeRequestVM employee){
        return this.employeeService.findEmployees(pageable,employee);
    }
    @PostMapping("/employee/save")
    public Object saveEmployee(@RequestBody Employee employee){
      return this.employeeService.saveEmployee(employee);
    }


    @PostMapping("/employee/delete")
    public void deleteEmployee(@RequestBody Employee employee){
        this.employeeService.delete(employee);
    }

}
