package com.aic.edudemo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

//@Entity 說明class是實體類
//@Table 注解指定了 Entity 所要映射带資料庫表，其中 @Table.name() 用来指定資料表
@Data
@Entity
@Table(name="JOBS")
public class Job {
    @Id
    private String jobId;
    private String jobTitle;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;

}
