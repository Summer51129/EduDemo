package com.aic.edudemo.web.vm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import lombok.Data;

//@JsonSetter(nulls=Nulls.SKIP) 若該屬性為 null 則跳過轉換，因此不會覆寫預設值
//@DateTimeFormate(iso=ISO.DATE) 表示接受 yyyy-MM-dd 的日期格式

@Data
public class EmployeeRequestVM {
    @JsonSetter(nulls = Nulls.SKIP)
    private String firstName = "%";
    @JsonSetter(nulls = Nulls.SKIP)
    private String lastName = "%";
    private String email;
    private String phoneNumber;
    @DateTimeFormat(iso = ISO.DATE)
    @JsonSetter(nulls = Nulls.SKIP)
    private LocalDate hireDateFrom = LocalDate.parse("0001-01-01");
    @DateTimeFormat(iso = ISO.DATE)
    @JsonSetter(nulls = Nulls.SKIP)
    private LocalDate hireDateTo = LocalDate.parse("9999-12-31");
    private String jobId;
    @JsonSetter(nulls = Nulls.SKIP)
    private BigDecimal salaryFrom = BigDecimal.valueOf(0);
    @JsonSetter(nulls = Nulls.SKIP)
    private BigDecimal salaryTo = BigDecimal.valueOf(99999999.99);
    private List<Integer> departmentIds = new ArrayList<>();
}
