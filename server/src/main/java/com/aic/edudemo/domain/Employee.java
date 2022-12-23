package com.aic.edudemo.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

import lombok.Data;

@Data //建立和覆寫 getter, setter, toString, hashCode, equals 等方法
@Entity //告知框架註冊此類別為 Entity
@Table(name = "EMPLOYEES") //用以對應資料庫表單名稱
public class Employee{
    //SEQUENCE 根據底層資料庫的序列來生成主鍵，條件是資料庫支持序列
    //JPA四種用法:TABLE,SEQUENCE,IDENTITY,AUTO. TABLE:使用一個特定資料庫表格保存主鍵，IDENTITY:主鍵由資料庫自動產生，AUTO:主鍵由程式控制
    //GenericGenerator:自定義主鍵生成策略，strategy:指定具體生成器類名
    //allocationSize:每次主鍵值增加的大小，設置為1，紀錄自動加1，默認為50
    //
    @Id //對應資料庫表單 primary key
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="EMPLOYEES_SEQ")
    @SequenceGenerator(allocationSize = 1,name="EMPLOYEES_SEQ")
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private String jobId;
    private BigDecimal salary;
    private BigDecimal commissionPct;
    private Integer managerId;
    private Integer departmentId;


}