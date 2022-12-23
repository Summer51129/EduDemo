package com.aic.edudemo.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="DEPARTMENTS")
public class Department {
    @Id
    private Integer departmentId;
    private String departmentName;
    private Integer managerId;
    private Integer locationId;
}
